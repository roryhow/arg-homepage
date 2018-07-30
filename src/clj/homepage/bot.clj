(ns homepage.bot)


(defn send-message [{ body :body }]
  (let [first (get body "firstname")
        last (get body "lastname")
        email (get body "email")
        message (get body "message")]
    (println (str first " " last "\nemail: " email "\nsays:\n" message))
    body))
