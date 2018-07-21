(ns homepage.bot)

;; (defn send-message [{:keys [firstname lastname email message]}]
;;   {:message (str "Thanks for your message: " message)})

(defn send-message [{ body :body }]
  (do
    (println (get body "message" "seems like you didn't say anything"))
    body))
