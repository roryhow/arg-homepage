(ns homepage.views.contact
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [reagent.core :as r]
            [cljs-http.client :as http]
            [soda-ash.core :as sa]
            [homepage.components.recaptcha :refer [recaptcha]]
            [homepage.utils :refer [clj->json]]))

(defn form-submit [content]
  (.log js/console content)
  (http/post "/send-message" {:json-params content}))

(defn contact-panel []
  (let [content (r/atom {:firstname "" :lastname "" :email "" :message ""})]
    (fn []
      [sa/Form {:onSubmit #(form-submit @content)}
       [sa/FormGroup {:widths "equal"}
        [sa/FormInput
         {:fluid true
          :label "First Name"
          :placeholder "First Name"
          :value (:firstname @content)
          :onChange #(swap! content assoc :firstname (.-value %2))
          }]
        [sa/FormInput
         {:fluid true
          :label "Last Name"
          :placeholder "Last Name"
          :value (:lastname @content)
          :onChange #(swap! content assoc :lastname (.-value %2))
          }]
        [sa/FormInput
         {:fluid true
          :label "Email"
          :placeholder "mauricemoss@reynolmindustries.com"
          :value (:email @content)
          :onChange #(swap! content assoc :email (.-value %2))
          }]]
       [sa/FormField
        [sa/TextArea
         {:label "Message"
          :placeholder "What would you like to say?"
          :value (:message @content)
          :onChange #(swap! content assoc :message (.-value %2))
          }]]
       [sa/FormField
        [sa/Button {:type "submit"} "submit"]]
       [recaptcha]
       ])))
