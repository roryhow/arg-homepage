(ns homepage.middleware
  (:require [ring.middleware.defaults :refer [site-defaults wrap-defaults]]
            [prone.middleware :refer [wrap-exceptions]]
            [ring.middlware.json :refer [wrap-json-body wrap-json-response]]
            [ring.middleware.reload :refer [wrap-reload]]))

(defn wrap-middleware [handler]
  (-> handler
      ;; (wrap-defaults (assoc-in site-defaults [:security :anti-forgery] false))
      (wrap-defaults (assoc site-defaults :security false))
      wrap-exceptions
      wrap-reload))

(defn wrap-api-middleware [handler]
  (-> handler
      wrap-json-body
      wrap-json-response
      wrap-exceptions
      wrap-reload))
