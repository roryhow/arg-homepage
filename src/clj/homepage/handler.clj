(ns homepage.handler
  (:require [compojure.core :refer [GET POST defroutes rfn]]
            [compojure.route :refer [resources]]
            [ring.util.response :refer [resource-response content-type]]
            [ring.middleware.reload :refer [wrap-reload]]
            [homepage.bot :refer [send-message]]
            [homepage.middleware :refer [wrap-middleware]]))

(defroutes routes
  ;; re-frame application
  (GET "/" [] (content-type (resource-response "index.html" {:root "public"}) "text/html; charset=utf-8"))
  (GET "/about" [] (content-type (resource-response "index.html" {:root "public"}) "text/html; charset=utf-8"))
  (GET "/contact" [] (content-type (resource-response "index.html" {:root "public"}) "text/html; charset=utf-8"))

  ;; API
  (POST "/send-message" req (send-message req))

  ;; static resources
  (resources "/"))

(def handler (wrap-middleware #'routes))
