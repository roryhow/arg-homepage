(ns homepage.css
  (:require [garden.def :refer [defstyles]]
            [homepage.styles.nav :refer [nav]]
            [homepage.styles.site :refer [site]]))

(defstyles screen [nav site])
