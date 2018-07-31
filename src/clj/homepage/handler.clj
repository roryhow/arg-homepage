(ns homepage.handler
  (:require [compojure.core :refer [GET POST defroutes routes rfn]]
            [compojure.route :refer [resources]]
            [ring.util.response :refer [resource-response content-type]]
            [ring.middleware.reload :refer [wrap-reload]]
            [homepage.bot :refer [send-message]]
            [homepage.middleware :refer [wrap-front-middleware wrap-api-middleware]]))

(defn- send-front []
  (content-type (resource-response "index.html" {:root "public"}) "text/html; charset=utf-8"))

(defroutes front-and-resource-routes
  ;; re-frame application
  (GET "/" [] (send-front))
  (GET "/about" [] (send-front))
  (GET "/contact" [] (send-front))

  ;; static resources
  (resources "/"))

(defroutes api-routes
  (POST "/send-message" [:as {h :headers b :body}]
        (let [recaptcha-token (get h "g-recaptcha-response")]
          (do
            (send-message b)))))

(def handler
  (routes (wrap-front-middleware #'front-and-resource-routes)
          (wrap-api-middleware #'api-routes)))
