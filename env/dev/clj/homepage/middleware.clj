(ns homepage.middleware
  (:require [ring.middleware.defaults :refer [site-defaults wrap-defaults]]
            [ring.util.response :refer [response status]]
            [environ.core :refer [env]]
            [prone.middleware :refer [wrap-exceptions]]
            [ring.middleware.json :refer [wrap-json-body wrap-json-response]]
            [ring.middleware.reload :refer [wrap-reload]]))

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
  (-> handler
      ;; (wrap-defaults (assoc-in site-defaults [:security :anti-forgery] false))
      (wrap-defaults (assoc site-defaults :security false))
      wrap-exceptions
      wrap-reload))

(defn wrap-api-middleware [handler]
  (-> handler
      wrap-auth
      wrap-json-body
      wrap-json-response
      wrap-exceptions
      wrap-reload))
