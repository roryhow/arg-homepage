(ns homepage.index
  (:require [hiccup.page :refer [include-js include-css html5]]))


(defn head []
  [:head
   [:meta {:charset "utf-8"}]
   [:meta {:name "viewport"
           :content "width=device-width, initial-scale=1"}]
   (include-css "https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.2.13/semantic.min.css")])

(defn index []
(html5
 (head)
 [:body
  [:div#app]
  (include-js "/js/app.js")]))
