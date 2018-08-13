(ns homepage.components.recaptcha
  (:require [reagent.core :as r]
            [reagent.ratom :as ra]
            [soda-ash.core :as sa]
            [re-frame.core :refer [dispatch subscribe]]))

(def state (ra/atom {:has-loaded false :is-mounted false}))

;; when the recaptcha response is no longer valid
(defn ^:export data_expired_callback [] (dispatch [:homepage.events/set-recaptcha-expired]))

;; render the recaptcha
(defn- render-recaptcha []
  (dispatch [:homepage.events/set-recaptcha-expired])
  (.render js/grecaptcha "g-recaptcha"
           (clj->js :sitekey "6LeMcWUUAAAAAOSsfkGq0YQ1aiwEPkrpy_B77jhP")))

(defn ^:export onload [] (swap! state assoc :has-loaded true))

(defn ^:export data_callback [x] (dispatch [:homepage.events/set-recaptcha-response x]))

(defn recaptcha []
  (r/create-class
   {:display-name "recaptcha"
    :component-did-update #(when (and (:is-mounted @state) (:has-loaded @state))
                             (render-recaptcha))
    :component-did-mount  #(swap! state assoc :is-mounted true)
    :component-will-unmount #(swap! state assoc :is-mounted false)
    :reagent-render
    (fn [] (let [_ @state] ;; subscribe to rerenders
            [sa/FormField {:style {:margin-bottom 0 }}
             [:div.g-recaptcha {:data-sitekey "6LeMcWUUAAAAAOSsfkGq0YQ1aiwEPkrpy_B77jhP"
                                :data-callback "onDataCallback"
                                :data-expired-callback "onExpiredCallback"
                                :id "g-recaptcha"}]]))}))
