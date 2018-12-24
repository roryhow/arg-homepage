(ns homepage.views.home
  (:require ;; [soda-ash.core :as sa]
   [re-frame.core :as re-frame]
   [reagent.core :as r]
   [homepage.subs :as subs]
   [homepage.views.about :refer [about-panel]]
   [homepage.views.contact :refer [contact-panel]]
   [accountant.core :refer [navigate!]]))

(defn self-image []
  [:figure {:className "image is-128x128" :style {:margin "auto"}}
   [:img {:src "/assets/me.jpeg" :className "is-rounded"}]])


;; hero
(defn hero []
  [:section {:className "hero is-small is-dark is-bold"}
   [:div {:className "hero-body"}
    [:div {:className "container columns is-desktop"}
     [:div {:className "column"}
      [self-image]]
     [:div {:className "column is-full"}
      [:h1 {:className "title"} "Hi! My name is Rory."]
      [:h2 {:className "subtitle"} "Thanks for coming here. Feel free to look through some of the things I've made and leave a message for me."]]]]])

;; home
(defn home-panel []
  [:div
   [hero]
   [about-panel]
   [contact-panel]
   ])
