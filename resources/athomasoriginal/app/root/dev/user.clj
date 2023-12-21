(ns user
  "Entry point to your dev environment.")


(defn into-dev
  "Move into the development space.  The dev namespace is a cool hangout.  Start
  a server, play with your app...live like Jay."
  []
  (println "[USER] Loading dev environment, please wait...")
  (doto 'dev require in-ns)
  :inside-dev)


(defn fix!
  "Attempt to recover from system errors.

  Example of errors:
    - namespace syntax errors (from undefined vars, missing aliases etc)
    - missing namespaces
    - class exceptions

  Returns the keyword `:inside-dev` when successful"
  []
  (require 'dev :reload-all)
  (into-dev))
