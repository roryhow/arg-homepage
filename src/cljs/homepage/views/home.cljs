(ns homepage.views.home
  (:require [soda-ash.core :as sa]
            [re-frame.core :as re-frame]
            [reagent.core :as r]
            [homepage.subs :as subs]
            [accountant.core :refer [navigate!]]))

(defn self-image []
  (let [revealed? (r/atom false)]
    (fn []
      (js/setTimeout #(reset! revealed? true) 1000)
      [sa/Reveal {:animated "rotate" :instant false :active @revealed?}
       [sa/RevealContent {:hidden true}
        [sa/Image {:src "/assets/me.jpeg" :size "medium" :circular true }]]
       [sa/RevealContent {:visible true}
        [sa/Image {:src "/assets/square-image.png" :size "medium" :circular true }]]])))

;; home
(defn home-panel []
[sa/Container {:className "home-panel"}
 [:h1 "Hi there! I'm Rory."]
 [self-image]])
