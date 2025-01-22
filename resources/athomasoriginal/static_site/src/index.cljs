(ns src.index
  (:require [src.extra.components :as components]))


(defn front-matter
  []
  #js{:layout    "html-base"
      :title     "home"
      :permalink "index.html"})


(defn page
  [data]
  [:div
    [:h1 "Welcome!"]
    [components/h2 "Example of a component"]])
