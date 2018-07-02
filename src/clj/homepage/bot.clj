(ns homepage.bot)

(defn send-message [req]
  (prn (:message req))
  req)
