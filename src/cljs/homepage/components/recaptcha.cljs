(ns homepage.components.recaptcha
  (:require [reagent.core :as r]
            [soda-ash.core :as sa]))

(defonce has-loaded (r/atom false))

(defn ^:export onload [] (reset! has-loaded true))

;; these aren't being called
(defn ^:export data-callback [] (print "data callback!"))
(defn ^:export data-expired-callback [] (print "data expired callback!"))

(defn- render-recaptcha []
  (.render js/grecaptcha "g-recaptcha" (clj->js :sitekey "6LeMcWUUAAAAAOSsfkGq0YQ1aiwEPkrpy_B77jhP")))

(defn recaptcha []
  (let [api-loaded? @has-loaded]
    (r/create-class
     {:display-name "recaptcha"
      :component-did-mount #(if api-loaded? (render-recaptcha) (print "didn't render recaptcha"))
      :reagent-render (fn [] [sa/FormField
                             [:div.g-recaptcha {:data-sitekey "6LeMcWUUAAAAAOSsfkGq0YQ1aiwEPkrpy_B77jhP"
                                                :data-callback "homepage.components.recaptcha.data_callback"
                                                :data-expired-callback "homepage.components.recaptcha.data_expired_callback"
                                                :id "g-recaptcha"}]])})))
