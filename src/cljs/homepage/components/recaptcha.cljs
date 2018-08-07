(ns homepage.components.recaptcha
  (:require [reagent.core :as r]
            [soda-ash.core :as sa]
            [re-frame.core :refer [dispatch subscribe]]))

(def state (r/atom {:has-loaded false :is-mounted false}))

;; when the recaptcha response is no longer valid
(defn ^:export data_expired_callback [] (dispatch [:homepage.events/set-recaptcha-expired]))

;; render the recaptcha
(defn- render-recaptcha []
  (dispatch [:homepage.events/set-recaptcha-expired])
  (.render js/grecaptcha "g-recaptcha"
           (clj->js :sitekey "6LeMcWUUAAAAAOSsfkGq0YQ1aiwEPkrpy_B77jhP")))

(defn ^:export onload []
  (swap! state assoc :has-loaded true)
  (print "loaded!")
  ;; don't render if component isn't mounted yet
  (when (:is-mounted @state) render-recaptcha))

(defn ^:export data_callback [x] (dispatch [:homepage.events/set-recaptcha-response x]))

(defn recaptcha []
  (let [api-loaded? (:has-loaded @state)]
    (r/create-class
     {:display-name "recaptcha"
      :component-did-mount
      #(do
         (render-recaptcha)
         (print "mounted!")
         (swap! state assoc :is-mounted true))
      :reagent-render
      (fn [] [sa/FormField {:style {:margin-bottom 0 }}
             [:div.g-recaptcha {:data-sitekey "6LeMcWUUAAAAAOSsfkGq0YQ1aiwEPkrpy_B77jhP"
                                :data-callback "onDataCallback"
                                :data-expired-callback "onExpiredCallback"
                                :id "g-recaptcha"}]])})))
