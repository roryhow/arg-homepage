(ns homepage.handler
  (:require [compojure.core :refer [GET POST wrap-routes defroutes routes rfn]]
            [compojure.route :refer [resources]]
            [clj-http.client :as client]
            [environ.core :refer [env]]
            [ring.util.response :refer [resource-response content-type response]]
            [ring.middleware.reload :refer [wrap-reload]]
            [homepage.bot :refer [send-message]]
            [homepage.middleware :refer [wrap-front-middleware wrap-api-middleware]]))

(def send-front (content-type (resource-response "index.html" {:root "public"}) "text/html; charset=utf-8"))

(defroutes static-resource-routes
  ;; re-frame application
  (GET "/" [] send-front)
  (GET "/about" [] send-front)
  (GET "/contact" [] send-front)

  (resources "/"))

(defroutes api-routes

  (POST "/send-message" req
        (let [h (:headers req)
              b (:body req)
              ip(:remote-addr req)
              recaptcha-token (get h "g-recaptcha-response")]
          (client/post "https://www.google.com/recaptcha/api/siteverify"
                       {:async? true
                        :form-params {:secret (:recaptcha-secret env)
                                      :response recaptcha-token
                                      :remoteip ip }
                        :as :json}
                       (fn [{{success? :success} :body}]
                         (send-message b)
                         (response "alrighty then"))
                       (fn [exception]
                         (println "execption message is: " (.getMessage exception))
                         (response "things went bad")))
          (response "I guess the request is nonblocking"))))

(def handler
  (routes
   (wrap-routes api-routes wrap-api-middleware)
   (wrap-routes static-resource-routes wrap-front-middleware)))
