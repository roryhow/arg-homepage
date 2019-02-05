(ns homepage.handler
  (:require [compojure.core :refer [GET POST wrap-routes defroutes routes rfn]]
            [compojure.route :refer [resources]]
            [clj-http.client :as client]
            [environ.core :refer [env]]
            [ring.util.response :refer [resource-response content-type response]]
            [ring.middleware.reload :refer [wrap-reload]]
            [homepage.bot :refer [send-message]]
            [homepage.middleware :refer [wrap-front-middleware wrap-api-middleware]]))

(defn send-front [] (content-type (resource-response "index.html" {:root "public"}) "text/html; charset=utf-8"))

(defn what-is-my-ip [request respond raise]
  (respond {:status 200
            :headers {"Content-Type" "text/plain"}
            :body (:remote-addr request)}))

(defroutes static-resource-routes
  ;; re-frame application
  (GET "/" [] (send-front))
  (GET "/about" [] (send-front))
  (GET "/contact" [] (send-front))

  (resources "/"))

(defroutes api-routes
  (POST "/send-message" req
        (let [h (:headers req)
              b (:body req)
              ip(:remote-addr req)
              recaptcha-token (get h "g-recaptcha-response")
              {{success? :success} :body}
              (client/post "https://www.google.com/recaptcha/api/siteverify"
                           {:form-params {:secret (:recaptcha-secret env)
                                          :response recaptcha-token
                                          :remoteip ip }
                            :as :json})]
          (if success?
            (do
              (send-message b)
              {:status 200
               :headers {"Content-Type" "application/json"}
               :body {:success true
                      :message "Successfully authenticated"}})
            {:status 403
             :headers {"Content-Type" "application/json"}
             :body {:success false
                    :message "Failed recaptcha authentication"}})))

  (GET "/my-ip" [] what-is-my-ip))

(def handler
  (routes
   (wrap-routes api-routes wrap-api-middleware)
   (wrap-routes static-resource-routes wrap-front-middleware)))
