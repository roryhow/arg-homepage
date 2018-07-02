(ns homepage.styles.general
  (:require [homepage.styles.colors :refer [colors]]))

(def base-font-size 1.125)

(def font-family ["Helvetica Neue" "Verdana" "Helvetica" "Arial" "sans-serif"])

(def headers
  [[:h1 :h2 :h3 {:color (:font-color colors)}]
   [:h1 { :font-size (str (+ base-font-size 1.375) "em")}]
   [:h2 { :font-size (str (+ base-font-size 0.875) "em")}]
   [:h3 { :font-size (str (+ base-font-size 0.125) "em")}]])
