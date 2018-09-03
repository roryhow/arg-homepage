(defproject homepage "0.2.0"
  :description "Rory How homepage"
  :url "http://www.roryhow.com"
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [org.clojure/clojurescript "1.10.339"]
                 [org.clojure/core.async "0.3.465"]
                 [reagent "0.8.1"]
                 [re-frame "0.10.5"]
                 [environ "1.1.0"]
                 [secretary "1.2.3"]
                 [garden "1.3.5"]
                 [morse "0.4.0"]
                 [ns-tracker "0.3.1"]
                 [compojure "1.6.1"]
                 [soda-ash "0.81.1"]
                 [cljs-http "0.1.45"]
                 [clj-http "3.9.1"]
                 [hiccup "1.0.5"]
                 [cljsjs/react-modal "3.4.4-1"]
                 [venantius/accountant "0.2.4"]
                 [yogthos/config "1.1.1"]
                 [ring/ring-defaults "0.3.2"]
                 [ring/ring-json "0.4.0"]
                 [ring "1.6.3"]]

  :plugins [[lein-cljsbuild "1.1.7"]
            [lein-environ "1.1.0"]
            [lein-garden "0.2.8"]]

  :min-lein-version "2.7.1"

  :source-paths ["src/clj" "src/cljs"]
  :resource-paths ["target" "resources"]

  :clean-targets ^{:protect false} ["resources/public/js/compiled" "target"
                                    "resources/public/css"]

  :aliases {"fig"   ["trampoline" "run" "-m" "figwheel.main"]
            "build" ["trampoline" "run" "-m" "figwheel.main" "-b" "dev" "-r"]
            "min"   ["run" "-m" "figwheel.main" "-O" "advanced" "-bo" "dev"]}

  :profiles
  {:dev [:project/dev :profiles/dev]
   :prod [:project/prod :profiles/prod]
   :uberjar [:project/uberjar :profiles/uberjar]

   ;; dev env
   :profiles/dev {}
   :project/dev
   {:dependencies [[binaryage/devtools "0.9.10"]
                   [figwheel-sidecar "0.5.16"]
                   [ring/ring-mock "0.3.2"]
                   [ring/ring-devel "1.6.3"]
                   [prone "1.6.0"]
                   [cider/piggieback "0.3.9"]
                   [day8.re-frame/tracing "0.5.1"]
                   [day8.re-frame/re-frame-10x "0.3.3-react16"]
                   [com.bhauman/figwheel-main "0.1.8"]
                   [com.bhauman/rebel-readline-cljs "0.1.4"]]
    :repl-options {:nrepl-middleware [cider.piggieback/wrap-cljs-repl]}
    :source-paths ["env/dev/clj"]}

   ;; heroku env
   :profiles/uberjar {}
   :project/uberjar
   {:source-paths ["env/prod/clj"]
    :prep-tasks ["compile" ["cljsbuild" "once" "min"]]
    :dependencies [[day8.re-frame/tracing-stubs "0.5.1"]]
    :aot :all
    :omit-source true}}

  :cljsbuild
  {:builds
   [{:id           "min"
     :source-paths ["src/cljs" "env/prod/cljs"]
     :jar true
     :compiler     {:main            homepage.core
                    :output-to       "target/public/js/compiled/app.js"
                    :optimizations   :advanced
                    :closure-defines {goog.DEBUG false}
                    :pretty-print    false}}]}

  :main homepage.server

  :aot [homepage.server]

  :uberjar-name "homepage.jar"

  :prep-tasks [["cljsbuild" "once" "min"]["garden" "once"] "compile"])
