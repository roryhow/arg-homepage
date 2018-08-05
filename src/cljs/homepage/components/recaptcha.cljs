(ns homepage.components.recaptcha
  (:require [reagent.core :as r]
            [soda-ash.core :as sa]
            [re-frame.core :refer [dispatch subscribe]]))

(defonce has-loaded (r/atom false))
(defonce is-mounted (atom false))


(defn ^:export data_expired_callback [] (dispatch [:homepage.events/set-recaptcha-expired]))
(defn- render-recaptcha []
  (data_expired_callback) ;; clear recaptcha results
  (.render js/grecaptcha "g-recaptcha"
           (clj->js :sitekey "6LeMcWUUAAAAAOSsfkGq0YQ1aiwEPkrpy_B77jhP")))
(defn ^:export onload []
  (reset! has-loaded true)
  ;; don't render if component isn't mounted yet
  (when @is-mounted render-recaptcha))
(defn ^:export data_callback [x] (dispatch [:homepage.events/set-recaptcha-response x]))

(defn recaptcha []
  (let [api-loaded? @has-loaded]
    (r/create-class
     {:display-name "recaptcha"
      :component-did-mount #(when api-loaded? (render-recaptcha) (reset! is-mounted true))
      :reagent-render
      (fn [] [sa/FormField {:style {:margin-bottom 0 }}
             [:div.g-recaptcha {:data-sitekey "6LeMcWUUAAAAAOSsfkGq0YQ1aiwEPkrpy_B77jhP"
                                :data-callback "onDataCallback"
                                :data-expired-callback "onExpiredCallback"
                                :id "g-recaptcha"}]])})))
