(ns homepage.server
  (:require [homepage.handler :refer [handler]]
            [config.core :refer [env]]
            [ring.adapter.jetty :refer [run-jetty]])
  (:gen-class))

(defn -main [& args]
  (let [port (or (env :port) 3000)]
    (print (str "running on port: " port))
    (run-jetty handler {:port port :join? false})))
