(ns homepage.middleware
  (:require [environ.core :refer [env]]
            [ring.middleware.gzip :refer [wrap-gzip]]
            [ring.util.response :refer [response status]]
            [ring.middleware.defaults :refer [site-defaults api-defaults wrap-defaults]]
            [ring.middleware.json :refer [wrap-json-body wrap-json-response]]))

(defn- authorized? [request]
  (let [api-key (get (:headers request) "api-key")
        env-api-key (env :api-key)]
    (= api-key env-api-key)))

(defn wrap-auth [handler]
  (fn [request]
    (if (authorized? request)
      (handler request)
      (status (response "Access Denied") 403))))

(defn get-token
  "gets the ring session cookie from request and returns nil if not found"
  [req]
  (-> req
      (get-in [:headers "cookie"] "")
      (clojure.string/split #"=")
      (nth 1 nil)))

(defn wrap-front-middleware [handler]
  (-> handler
      (wrap-defaults site-defaults)
      (wrap-gzip)))

(defn wrap-api-middleware [handler]
  (-> handler
      (wrap-defaults api-defaults)
      wrap-auth
      wrap-json-body
      wrap-json-response))
