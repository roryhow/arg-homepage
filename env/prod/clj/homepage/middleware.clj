(ns homepage.middleware
  (:require [ring.middleware.defaults :refer [site-defaults wrap-defaults]]
            [ring.middleware.json :refer [wrap-json-body wrap-json-response]]))

(defn- authorized? [request]
  (let [api-key (get (:headers request) "api-key")
        env-api-key (env :api-key)]
    (= api-key env-api-key)))

(defn wrap-auth [handler]
  (fn [request]
    (if (authorized? request)
      (handler request)
      (-> (response "Access Denied")
          (status 403)))))

(defn wrap-front-middleware [handler]
  (wrap-defaults handler site-defaults))

(defn wrap-api-middleware [handler]
  (-> handler
      wrap-auth
      wrap-json-body
      wrap-json-response))
