(ns homepage.handler
  (:require [compojure.core :refer [GET defroutes rfn]]
            [compojure.route :refer [resources]]
            [ring.util.response :refer [resource-response]]
            [ring.middleware.reload :refer [wrap-reload]]
            [homepage.index :refer [index]]))

(defroutes routes
  (GET "/" [] (index))
  (rfn [] (index))
  (resources "/"))

(def dev-handler (-> #'routes wrap-reload))

(def handler routes)
