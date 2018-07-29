(ns homepage.events
  (:require
   [re-frame.core :as re-frame]
   [homepage.db :as db]))

(re-frame/reg-event-db
 ::initialize-db
 (fn [_ _]
   db/default-db))

(re-frame/reg-event-db
 ::set-active-panel
 (fn [db [_ active-panel]]
   (assoc db :active-panel active-panel)))

(re-frame/reg-event-db
 ::set-recaptcha-response
 (fn [db  [_ token]]
   (assoc db :recaptcha-token token)))

(re-frame/reg-event-db
 ::set-recaptcha-expired
 (fn [db _]
   (assoc db :recptcha-token nil)))
