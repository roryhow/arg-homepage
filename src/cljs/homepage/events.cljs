(ns homepage.events
  (:require
   [re-frame.core :as re-frame]
   [day8.re-frame.tracing :refer-macros [fn-traced]]
   [homepage.db :as db]))

(re-frame/reg-event-db
 ::initialize-db
 (fn-traced [_ _]
            db/default-db))

(re-frame/reg-event-db
 ::set-active-panel
 (fn-traced [db [_ active-panel]]
            (assoc db :active-panel active-panel)))

(re-frame/reg-event-db
 ::set-recaptcha-response
 (fn-traced [db  [_ token]]
            (assoc db :recaptcha-token token)))

(re-frame/reg-event-db
 ::set-recaptcha-expired
 (fn-traced [db _]
            (assoc db :recaptcha-token nil)))
