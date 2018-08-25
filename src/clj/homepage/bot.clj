(ns homepage.bot
  (:require [morse.api :as t]
            [environ.core :refer [env]]))

(def ^:private token (:telegram-token env))
(def ^:private chat-id (:chat-id env))

(defn- construct-message [first last email message]
  (str first " " last ":\n "
       "**" email "**\n"
       message))

(defn send-message
  "Receives a map of body properties, sends data to telegram"
  [body]
  (let [first (get body "firstname")
        last (get body "lastname")
        email (get body "email")
        message (get body "message")]

    ;; Forward to telegram
    (t/send-text token chat-id {:parse_mode "Markdown"}
                 (construct-message first last email message))))
