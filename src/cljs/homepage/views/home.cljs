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
      [sa/Reveal {:animated "move" :instant false :active @revealed?}
       [sa/RevealContent {:hidden true}
        [sa/Image {:src "/assets/me.jpeg" :size "medium" }]]
       [sa/RevealContent {:visible true}
        [sa/Image {:src "/assets/square-image.png" :size "medium"  }]]])))

;; home
(defn home-panel []
  [:div {:className "home-panel"}
   [:h1 "Hi there! I'm Rory."]
   [self-image]])
