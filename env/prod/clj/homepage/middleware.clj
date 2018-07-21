(ns homepage.middleware
  (:require [ring.middleware.defaults :refer [site-defaults wrap-defaults]]
            [ring.middleware.json :refer [wrap-json-body wrap-json-response]]))

(defn wrap-front-middleware [handler]
  (wrap-defaults handler site-defaults))

(defn wrap-api-middleware [handler]
  (-> handler
      wrap-json-body
      wrap-json-response))
