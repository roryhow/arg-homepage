(ns homepage.components.modal
  ;; (:require [cljsjs.react-modal])
  )


(defn modal [] [:h1 "Modal!"])

;; (def modal-style {:overlay {:background-color "rgba(0, 0, 0, 0.5)"}
;;                   :content {:background-color "white"
;;                             :top "25%"
;;                             :left "50%"
;;                             :transform "translate(-50%, -50%)"
;;                             :right "unset"
;;                             :bottom "unset"}})
;; (defn modal [is-open?]
;;   [:> js/ReactModal {:contentLabel "myLabel"
;;                      :style modal-style
;;                      :isOpen is-open?}
;;    [:div
;;     [:h1 "This is the modal!"]
;;     [:p "Thanks for filling out the form, friend!"]]])
