(ns homepage.views.contact
  (:require-macros [cljs.core.async.macros :refer [go]]
                   [homepage.env :refer [api-key]])
  (:require [reagent.core :as r]
            [re-frame.core :refer [subscribe]]
            [cljs-http.client :as http]
            [soda-ash.core :as sa]
            [homepage.components.recaptcha :refer [recaptcha]]
            [homepage.utils :refer [clj->json]]))

(defn form-submit [content token]
  (http/post "/send-message" {:json-params content
                              :headers {"g-recaptcha-response" token
                                        "api-key" (api-key)}}))

(defn contact-panel []
  (let [content (r/atom {:firstname "" :lastname "" :email "" :message ""})
        recaptcha-token (subscribe [:homepage.subs/recaptcha-token])]
    (fn []
      [sa/Form {:onSubmit #(form-submit @content @recaptcha-token) :method "POST"}
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
       [sa/FormField {:style {:display "flex" :flex-direction "row" :justify-content "space-between" }}
        [recaptcha]

        [sa/Button {:type "submit"
                    :disabled (nil? @recaptcha-token)
                    :negative (nil? @recaptcha-token)
                    :positive (not (nil? @recaptcha-token))} (if (nil? @recaptcha-token) "Are you a bot, though?" "Send me a message!")]]
       ])))
