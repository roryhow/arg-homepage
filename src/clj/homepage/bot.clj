(ns homepage.bot
  (:require [morse.api :as t]
            [environ.core :refer [env]]))

(def ^:private token (:telegram-token env))
(def ^:private chat-id (:chat-id env))

(defn- construct-message [name email message]
  (str "Name: " name ":\n"
       "Email: " email "\n"
       "Message: " message))

(defn send-message
  "Receives a map of body properties, sends data to telegram"
  [body]
  (let [name (get body "name")
        email (get body "email")
        message (get body "message")]

    ;; Forward to telegram
    (t/send-text token chat-id {:parse_mode "Markdown"}
                 (construct-message name email message))))
