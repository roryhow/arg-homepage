(ns homepage.views.contact
  (:require-macros [cljs.core.async.macros :refer [go]]
                   [homepage.env :refer [api-key]])
  (:require [reagent.core :as r]
            [re-frame.core :refer [subscribe dispatch]]
            [cljs-http.client :as http]
            [homepage.components.recaptcha :refer [recaptcha]]
            [homepage.utils :refer [clj->json]]))


(defn form-submit [e content token]
  ;; when we take a response from the HTTP request, run the following logic
  ;; the http/post returns a channel, and we are listening for the response
  ;; to be put there
  (.preventDefault e)
  (go (let [response (<! (http/post "/send-message" {:json-params content
                                                     :headers {"g-recaptcha-response" token
                                                               "api-key" (api-key)}}))]
        (if (= (:status response) 200)
          (dispatch [:homepage.events/set-form-submitted])))))

(defn contact-panel []
  (let [content (r/atom {:name "" :email "" :message ""})
        recaptcha-token (subscribe [:homepage.subs/recaptcha-token])]
    (fn []
      [:section {:className "section"}
       [:form {:className "form" :onSubmit #(form-submit % @content @recaptcha-token)}
        [:div {:className "field is-horizontal"}
         [:div {:className "field-body"}
          [:div {:className "field"}
           [:label {:className "label"} "Name"]
           [:div {:className "control has-icons-left is-expanded"}
            [:span {:className "icon is-small is-left"}
             [:i {:className "fas fa-user"}]]
            [:input {:className "input"
                     :placeholder "Maurice Moss"
                     :on-change #(swap! content assoc :name (-> % .-target .-value))}]]]
          [:div {:className "field"}
           [:label {:className "label"} "Email"]
           [:div {:className "control has-icons-left is-expanded"}
            [:span {:className "icon is-small is-left"}
             [:i {:className "fas fa-envelope"}]]
            [:input {:className "input"
                     :placeholder "maurice.moss@reynolmindustries.co.uk"
                     :on-change #(swap! content assoc :email (-> % .-target .-value))}]]]]]
        [:div {:className "field"}
         [:label {:className "label"} "Message"]
         [:div {:className "control"}
          [:textarea {:on-change #(swap! content assoc :message (-> % .-target .-value))
                      :className "textarea"
                      :rows "5"
                      :placeholder "Please, let me offer you this well-paying job"
                      }]]]
        [:div {:className "level"}
         [:div {:className "level-left"} [recaptcha]]
         [:div {:className "level-right"}
          [:button {:className (if @recaptcha-token "button is-dark" "button is-danger")
                    :style {:height "74px" :width "304px"}
                    :disabled (nil? @recaptcha-token)
                    :type "submit"}
           (if @recaptcha-token "Send the message!" "Do the recaptcha u bot")]]]]])))
