(ns homepage.env
  (:require [environ.core :refer [env]]))

(defmacro api-key [] (:api-key env))
(defmacro recaptcha-site-key [] (:recaptcha-site-key env))
