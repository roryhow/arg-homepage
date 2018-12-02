(ns homepage.components.nav
  (:require ;; [soda-ash.core :as sa]
   [accountant.core :as accountant :refer [navigate!]]))

;; (defn- nav-bar [active-page]
;;   [:div.navigation
;;    [sa/Menu {:pointing true :class "nav--primary"}
;;     [sa/MenuItem {:name "HOME"
;;                   :active (= active-page :home-panel)
;;                   :onClick #(navigate! "/")}]
;;     [sa/MenuItem {:name "ABOUT"
;;                   :active (= active-page :about-panel)
;;                   :onClick #(navigate! "/about")}]
;;     [sa/MenuItem {:name "CONTACT"
;;                   :active (= active-page :contact-panel)
;;                   :onClick #(navigate! "/contact")}]
;;     ]])

(defn- nav-bar [active-page]
  [:div [:h1 "hello!"]])
