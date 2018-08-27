(ns homepage.components.modal
  (:require [cljsjs.react-modal]))

(defn modal [is-open?]
  [:> js/ReactModal {:contentLabel "myLabel"
                     :isOpen is-open?}
   [:div
    [:h1 "This is the modal!"]
    [:p "Thanks for filling out the form, friend!"]]])
