(ns homepage.styles.nav
  (:require [homepage.styles.colors :refer [colors]]))

(def nav [:.navigation
          {:margin "0 auto"
           :color (:font-color colors)
           :padding "10px 0px"
           :text-align "center"}
          ])
