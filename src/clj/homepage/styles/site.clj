(ns homepage.styles.site
  (:require [homepage.styles.colors :refer [colors]]
            [homepage.styles.general :refer [font-family base-font-size]]))


(def body [:body { :background-color (:background-color colors)
                  :margin "10px 10px"}])

(def grid-container
  [:.grid-container { :color (:font-color colors)
                     :display "grid"
                     :font-family font-family
                     :margin "0 auto"
                     :padding-top "30px"
                     :-webkit-font-smoothing "antialiased"
                     :font-size base-font-size
                     :line-height "1.5em" }])


(def site [body grid-container])
