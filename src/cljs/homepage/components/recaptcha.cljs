(ns homepage.components.recaptcha
  (:require [reagent.core :as r]
            [soda-ash.core :as sa]
            [re-frame.core :refer [dispatch]]))

(defonce has-loaded (r/atom false))

(defn ^:export onload [] (reset! has-loaded true))

(defn ^:export data_callback [x] (dispatch [:homepage.events/set-recaptcha-response x]))
(defn ^:export data_expired_callback [] (dispatch [:homepage.events/set-recaptcha-expired]))

(defn- render-recaptcha []
  (.render js/grecaptcha "g-recaptcha" (clj->js :sitekey "6LeMcWUUAAAAAOSsfkGq0YQ1aiwEPkrpy_B77jhP")))

(defn recaptcha []
  (let [api-loaded? @has-loaded]
    (r/create-class
     {:display-name "recaptcha"
      :component-did-mount #(if api-loaded? (render-recaptcha))
      :reagent-render (fn [] [sa/FormField
                             [:div.g-recaptcha {:data-sitekey "6LeMcWUUAAAAAOSsfkGq0YQ1aiwEPkrpy_B77jhP"
                                                :data-callback "onDataCallback"
                                                :data-expired-callback "onExpiredCallback"
                                                :id "g-recaptcha"}]])})))
