(ns homepage.views.home
  (:require [soda-ash.core :as sa]
            [re-frame.core :as re-frame]
            [homepage.subs :as subs]
            [accountant.core :refer [navigate!]]))

;; home
(defn home-panel []
  (let [name (re-frame/subscribe [::subs/name])]
    [:div
     [:h1 (str "Hello from " @name ". This is the Home Page.")]
     [:div
      [:a {:onClick #(navigate! "/about")}
       "go to About Page"]]
     ]))
