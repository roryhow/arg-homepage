(ns homepage.handler
  (:require [compojure.core :refer [GET POST defroutes rfn]]
            [compojure.route :refer [resources]]
            [ring.util.response :refer [resource-response]]
            [ring.middleware.reload :refer [wrap-reload]]
            [homepage.bot :refer [send-message]]))

(defroutes routes
  ;; re-frame application
  (GET "/" [] (resource-response "index.html" {:root "public"}))

  ;; API
  (POST "/send-message" req (send-message req))

  ;; send the site as a fallback for everything
  (rfn [] (resource-response "index.html" {:root "public"}))

  ;; static resources
  (resources "/"))

(def dev-handler (-> #'routes wrap-reload))

(def handler routes)
