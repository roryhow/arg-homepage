(ns homepage.components.recaptcha
  (:require [reagent.core :as r]
            [soda-ash.core :as sa]
            [re-frame.core :refer [dispatch subscribe]]))

(defonce has-loaded (r/atom false))

(defn ^:export onload [] (reset! has-loaded true))

(defn ^:export data_callback [x] (dispatch [:homepage.events/set-recaptcha-response x]))
(defn ^:export data_expired_callback [] (dispatch [:homepage.events/set-recaptcha-expired]))

(defn- render-recaptcha []
  (data_expired_callback) ;; clear recaptcha results & trigger button
  (.render js/grecaptcha "g-recaptcha" (clj->js :sitekey "6LeMcWUUAAAAAOSsfkGq0YQ1aiwEPkrpy_B77jhP")))

(defn recaptcha []
  (let [api-loaded? @has-loaded
        ;; subscribe to recaptcha for rerender on captcha state change
        _ (subscribe [:homepage.subs/recaptcha-token])]

    (r/create-class
     {:display-name "recaptcha"
      :component-did-mount #(when api-loaded? (render-recaptcha))
      :reagent-render (fn [] [sa/FormField {:style {:margin-bottom 0 }}
                             [:div.g-recaptcha {:data-sitekey "6LeMcWUUAAAAAOSsfkGq0YQ1aiwEPkrpy_B77jhP"
                                                :data-callback "onDataCallback"
                                                :data-expired-callback "onExpiredCallback"
                                                :id "g-recaptcha"}]])})))
