(ns src.extra.components)


(defn h2
  [props & children]
  (into [:h2 props] children))
