(ns run-clj
  (:require
    [edamame.core       :as e]
    [clojure.edn        :as edn]
    [clojure.string     :as string]
    [sci.core           :as sci]
    [nbb.core           :as nbb]
    [promesa.core       :as p]
    [reagent.dom.server :as rds]))

;; parse ns deps
;; ----------------------------------------------------------------------------

(defn ns-decl?
  "Returns true if form is a (ns ...) declaration."
  [form]
  (and (list? form) (= 'ns (first form))))


(defn- prefix-spec?
  "Returns true if form represents a libspec prefix list like
  (prefix name1 name1) or [com.example.prefix [name1 :as name1]]"
  [form]
  (and (sequential? form)  ; should be a list, but often is not
       (symbol? (first form))
       (not-any? keyword? form)
       (< 1 (count form))))  ; not a bare vector like [foo]


(defn- option-spec?
  "Returns true if form represents a libspec vector containing optional
  keyword arguments like [namespace :as alias] or
  [namespace :refer (x y)] or just [namespace]"
  [form]
  (and (sequential? form)  ; should be a vector, but often is not
       (or (symbol? (first form))
           (string? (first form)))
       (or (keyword? (second form))  ; vector like [foo :as f]
           (= 1 (count form)))))  ; bare vector like [foo]


(defn- deps-from-libspec
  [prefix form]
  (cond (prefix-spec? form)
        (mapcat (fn [f] (deps-from-libspec
                           (symbol (str (when prefix (str prefix "."))
                                        (first form)))
                           f))
                (rest form))

        (option-spec? form)
        (when-not (= :as-alias (second form))
          (deps-from-libspec prefix (first form)))

        (symbol? form)
        (list (symbol (str (when prefix (str prefix ".")) form)))

        (keyword? form)
        nil

        :else
        nil))


(def ^:private ns-clause-head-names
  "Set of symbol/keyword names which can appear as the head of a
  clause in the ns form."
  #{"use" "require" "require-macros"})


(def ^:private ns-clause-heads
  "Set of all symbols and keywords which can appear at the head of a
  dependency clause in the ns form."
  (set (mapcat #(list (keyword %) (symbol %)) ns-clause-head-names)))


(defn- deps-from-ns-form
  [form]
  ;; should be list but sometimes is not
  (when (and (sequential? form)
             (contains? ns-clause-heads (first form)))
    (mapcat #(deps-from-libspec nil %) (rest form))))


(defn deps-from-ns-decl
  [decl]
  (set (mapcat deps-from-ns-form decl)))


(defn read-ns-decl
  "Given a string of clojure code, find ns decl."
  [s]
  (let [reader (sci/reader s)]
    (loop []
      (let [next-form (e/parse-next reader)]
        (cond
          (ns-decl? next-form) next-form
          (= ::eof next-form)  nil
          :else                (recur))))))


(defn ns-name->file-path
  [sym]
  (str "./" (string/replace (str sym) "." "/") ".cljs"))


(defn file-path->ns-name
  [file-path extension]
  (-> file-path
      (string/replace #"^\./" "")  ; Remove "./" from the start
      (string/replace #"/" ".")    ; Replace all "/" with "."
      (string/replace #"\.cljs$" "") ; Remove ".cljs" from the end
      (string/replace #"_" "-")))


;; compile
;; ----------------------------------------------------------------------------

(defn reload-dependencies
  "Given a coll of `file-paths`, load each.

  This exists temporarily because `:reload` isn't currently supported in `nbb`."
  [file-paths]
  (p/let [file-strs (.. js/Promise (all (map #(nbb/slurp %) file-paths)))
          _         (.. js/Promise (all (map #(nbb/load-string %) file-strs)))]))


(defn compile-file
  "Given a `file-name`, find and compile the last form.  Return JS hash-map.

  ## Args

  * file-name - string? a required file-path e.g. `./src/test.cljs`
  * opts      - map? an optional options hash-map

  ## Opts

  :data - JS Object? Eleventy data cascade.

  ## Return

  The return shape is a JS object with the following properties:

  :htmlString - string? a string of HTML
  :deps       - array?  an array of strings.  Each string is the file-path to
                        a dep of the given `file-name`."
  ([file-name]
   (compile-file file-name nil))
  ([file-name opts]
   (p/let [result-str (nbb/slurp file-name)
           ns-decl    (read-ns-decl result-str)
           deps       (deps-from-ns-decl ns-decl)
           _          (reload-dependencies (map ns-name->file-path deps))
           result     (nbb/load-string result-str)
           hiccup     (if (clojure.string/starts-with? (str result) "#'")
                        [result {:data (.. opts -data)}]
                        result)]
     #js{:htmlString (rds/render-to-static-markup hiccup)
         :deps       (clj->js (map ns-name->file-path deps))})))


(defn read-frontmatter
  "Given an `file-path` (string?), return a JS object."
  [file-path]
  (p/let [result-str  (nbb/slurp file-path)
          result      (nbb.core/load-string result-str)
          namespace   (file-path->ns-name file-path)
          frontmatter (resolve (symbol (str namespace "/front-matter")))]
     (frontmatter)))


(defn read-edn
  "Given a `file-path` string, return a JS object."
  [file-path]
  (p/let [result-str  (nbb/slurp file-path)]
    (-> (edn/read-string result-str)
        (clj->js))))

#js {:compileFile     compile-file
     :readEdn         read-edn
     :readFrontmatter read-frontmatter}
