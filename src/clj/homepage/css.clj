(ns homepage.css
  (:require [garden.def :refer [defstyles]]
            [homepage.styles.nav :refer [nav]]
            [homepage.styles.site :refer [site]]
            [homepage.styles.home :refer [home]]
            ))

(defstyles screen [site nav home])
