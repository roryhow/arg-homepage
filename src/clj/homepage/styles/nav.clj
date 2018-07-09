(ns homepage.styles.nav
  (:require [homepage.styles.colors :refer [colors]]))

(def nav [:.navigation
          {:display "flex"
           :justify-content "center"
           :margin-bottom "30px"
           :color (:font-color colors)
           :padding "0px 0px"
           :text-align "center"}
          ])
