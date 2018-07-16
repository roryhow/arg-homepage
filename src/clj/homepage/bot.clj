(ns homepage.bot)

;; (defn send-message [{:keys [firstname lastname email message]}]
;;   {:message (str "Thanks for your message: " message)})

(defn send-message [req]
  (do
    (println req)
    req))
