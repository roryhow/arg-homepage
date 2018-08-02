(ns homepage.handler-test
  (:require [clojure.test :refer [deftest is]]
            [homepage.handler :refer [handler]]
            [ring.mock.request :as mock]))

(deftest send-message-test
  (is (= (handler (-> (mock/request :post "/send-message")
                      (mock/header :api-key "foo")
                      (mock/json-body {:firstname "Rory"
                                       :lastname "How"
                                       :email "rhow93@gmail.com"
                                       :message "Hello there, Rory!"})))
         {:status 200 })))
