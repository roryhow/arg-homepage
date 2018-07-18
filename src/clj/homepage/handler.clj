(ns homepage.handler
  (:require [compojure.core :refer [GET POST defroutes rfn]]
            [compojure.route :refer [resources]]
            [ring.util.response :refer [resource-response]]
            [ring.middleware.reload :refer [wrap-reload]]
            [homepage.bot :refer [send-message]]
            [homepage.middleware :refer [wrap-middleware]]))

(defroutes routes
  ;; re-frame application
  (GET "/" [] (resource-response "index.html" {:root "public"}))

  ;; API
  (POST "/send-message" req (send-message req))

  ;; static resources
  (resources "/")

  ;; send the site as a fallback for everything
  (rfn [] (resource-response "index.html" {:root "public"})))

(def handler (wrap-middleware #'routes))
