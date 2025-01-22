(ns run-clj
  (:require
    [clojure.edn        :as edn]
    [clojure.string     :as string]
    [nbb.core           :as nbb]
    [promesa.core       :as p]
    [reagent.dom.server :as rds]))

;; parse ns deps
;; ----------------------------------------------------------------------------

(defn file-path->ns-name
  [file-path extension]
  (-> file-path
      (string/replace #"^\./" "")  ; Remove "./" from the start
      (string/replace #"/" ".")    ; Replace all "/" with "."
      (string/replace #"\.cljs$" "") ; Remove ".cljs" from the end
      (string/replace #"_" "-")))


;; compile
;; ----------------------------------------------------------------------------


(defn compile-file
  "Given a `file-name`, find and compile the last form.  Return JS hash-map.

  ## Args

  * file-name - string? a required file-path e.g. `./src/test.cljs`
  * opts      - map? an optional options hash-map

  ## Opts

  :data - JS Object? Eleventy data cascade.

  ## Return

  The return shape is a JS object with the following properties:

  :htmlString - string? a string of HTML."
  ([file-name]
   (compile-file file-name nil))
  ([file-name opts]
   (p/let [result-str (nbb/slurp file-name)
           result     (nbb/load-string result-str)
           hiccup     (if (clojure.string/starts-with? (str result) "#'")
                        [result {:data (.. opts -data)}]
                        result)]
     #js{:htmlString (rds/render-to-static-markup hiccup)})))


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
