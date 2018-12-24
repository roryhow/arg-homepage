(ns homepage.views.about)

(defn about-panel []
  [:section {:className "section" :style {:padding-bottom "0"}}
   [:div {:className "content is-medium"}
    [:p {:style {:display "inline"}} "I'm a full-stack software developer from the UK, currently living in Finland, working at "]
    [:a {:href "https://futurice.com" :target "_blank"} "Futurice"]
    [:p {:style {:display "inline"}} " and studying for a Computer Science Masters at "]
    [:a {:href "https://aalto.fi" :target "_blank"} "Aalto University."]
    [:p {:style {:display "inline"}} "You can find out a little bit more about me by looking at my "]
    [:a {:href "https://www.linkedin.com/in/roryhow/" :target "_blank"} "LinkedIn profile"]
    [:p {:style {:display "inline"}} ". Or, if you'd like to look at some of the projects that I work on you can check out my "]
    [:a {:href "https://github.com/rhow93" :target "_blank"} "GitHub profile"]
    [:p {:style {:display "inline"}} ". I also ramble occasionally on "]
    [:a {:href "https://twitter.com/argwick" :target "_blank"} "Twitter"]
    [:p {:style {:display "inline"}} "."]]
   [:div {:className "content is-medium"}
    [:p "If you want to contact me, you can do so by filling in the below form (you don't need to give me a real address but if you want me to get back to you I'd recommend it), or you can email me directly at my email - me@rory.how>"]]])
