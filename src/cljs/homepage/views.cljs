(ns homepage.views
  (:require
   [re-frame.core :as re-frame]
   [homepage.subs :as subs]
   [homepage.components.nav :refer [nav-bar]]
   [homepage.views.home :refer [home-panel]]
   [homepage.views.about :refer [about-panel]]
   [homepage.views.contact :refer [contact-panel]]
   [accountant.core :refer [navigate!]]
   ))

;; Present the corresponding panel from the active-panel sub
(defn- panels [panel-name]
  (case panel-name
    :home-panel [home-panel]
    :about-panel [about-panel]
    :contact-panel [contact-panel]
    [:div]))

;; Wrap the panel in a nav
(defn show-panel [panel-name]
  [:div
   [nav-bar panel-name]
   [panels panel-name]
   ])

(defn main-panel []
  (let [active-panel (re-frame/subscribe [::subs/active-panel])]
    [show-panel @active-panel]))
