(ns homepage.views.home
  (:require
   ;;[cljss.core :refer [defstyles;]]
   [re-frame.core :as re-frame]
   [reagent.core :as r]
   [homepage.subs :as subs]
   [re-frame.core :refer [subscribe dispatch]]
   [homepage.views.about :refer [about-panel]]
   [homepage.views.contact :refer [contact-panel]]
   [accountant.core :refer [navigate!]]))

(defn self-image []
  [:figure {:className "image is-128x128" :style {:margin "auto"}}
   [:img {:src "/assets/me.jpeg" :className "is-rounded"}]])


;; hero
(defn hero []
  [:section {:className "hero is-small is-dark is-bold"}
   [:div {:className "container hero-body"}
    [:div {:className "container columns is-desktop"}
     [:div {:className "column"}
      [self-image]]
     [:div {:className "column is-full"}
      [:h1 {:className "title"} "Hi! My name is Rory."]
      [:h2 {:className "subtitle"} "Thanks for coming here. Feel free to look through some of the things I've made and leave a message for me."]]]]])

;; (defstyles banner [] {:background-color "green"})

(defn- thanks-banner []
  [:section {:className "hero is-small is-success is-bold"
             :style {:margin-top "30px"}}
   [:div {:className "container hero-body"}
    [:div {:className "container"}
     [:h1 {:className "title"} "Thanks for the message!"]
     [:h2 {:className "subtitle"} "I'll get back to you soon."]]]])

;; home
(defn home-panel []
  (let [form-submitted? (subscribe [:homepage.subs/form-submitted?])]
    [:div
     [hero]
     [about-panel]
     (if @form-submitted? [thanks-banner] [contact-panel])]))
