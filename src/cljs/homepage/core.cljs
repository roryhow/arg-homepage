(ns homepage.core
  (:require
   [reagent.core :as reagent]
   [re-frame.core :as re-frame]
   [homepage.events :as events]
   [homepage.routes :as routes]
   [homepage.views :as views]
   [homepage.config :as config]
   [secretary.core :as secretary]
   [accountant.core :as accountant]
   ))


(defn dev-setup []
  (when config/debug?
    (enable-console-print!)
    (println "dev mode")))

(defn mount-root []
  (re-frame/clear-subscription-cache!)
  (reagent/render [views/main-panel]
                  (.getElementById js/document "app")))

(defn configure-navigation []
  (accountant/configure-navigation!
   {:nav-handler
    (fn [path]
      (secretary/dispatch! path))
    :path-exists?
    (fn [path]
      (secretary/locate-route path))})
  (accountant/dispatch-current!))

(defn ^:export init []
  (configure-navigation)
  (routes/app-routes)
  (re-frame/dispatch-sync [::events/initialize-db])
  (dev-setup)
  (mount-root))
