(defproject homepage "0.1.0-SNAPSHOT"
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

  :min-lein-version "2.5.3"

  :source-paths ["src/clj"]

  :clean-targets ^{:protect false} ["resources/public/js/compiled" "target"
                                    "resources/public/css"]

  :figwheel {:css-dirs ["resources/public/css"]
             :ring-handler homepage.handler/handler
             :server-logfile false}

  :garden {:builds [{:id           "screen"
                     :source-paths ["src/clj"]
                     :stylesheet   homepage.css/screen
                     :compiler     {:output-to     "resources/public/css/screen.css"
                                    :pretty-print? true}}]}

  :repl-options {:nrepl-middleware [cider.piggieback/wrap-cljs-repl]}

  :aliases {"dev" ["do" "clean"
                   ["pdo" ["figwheel" "dev"]
                    ["garden" "auto"]]]
            "build" ["with-profile" "+prod,-dev" "do"
                     ["clean"]
                     ["cljsbuild" "once" "min"]
                     ["garden" "once"]]}

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
                   [cider/piggieback "0.3.8"]
                   [day8.re-frame/tracing "0.5.1"]
                   [day8.re-frame/re-frame-10x "0.3.3-react16"]]

    :plugins      [[lein-figwheel "0.5.16"]
                   [lein-pdo "0.1.1"]]
    :source-paths ["env/dev/clj"]}

   ;; prod env
   :profiles/prod {}
   :project/prod
   {:source-paths ["env/prod/clj"]
    :prep-tasks ["compile" ["cljsbuild" "once" "min"]]
    :dependencies [[day8.re-frame/tracing-stubs "0.5.1"]]
    :aot :all
    :omit-source true }

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
   [{:id           "dev"
     :source-paths ["src/cljs" "env/dev/cljs"]
     :figwheel     {:on-jsload "homepage.core/mount-root"}
     :compiler     {:main                 homepage.core
                    :output-to            "resources/public/js/compiled/app.js"
                    :output-dir           "resources/public/js/compiled/out"
                    :asset-path           "js/compiled/out"
                    :source-map-timestamp true
                    :closure-defines      {"re_frame.trace.trace_enabled_QMARK_" true
                                           "day8.re_frame.tracing.trace_enabled_QMARK_"  true}
                    :preloads             [devtools.preload day8.re-frame-10x.preload]
                    :external-config      {:devtools/config {:features-to-install :all}}
                    }}

    {:id           "min"
     :source-paths ["src/cljs" "env/prod/cljs"]
     :jar true
     :compiler     {:main            homepage.core
                    :output-to       "resources/public/js/compiled/app.js"
                    :optimizations   :advanced
                    :closure-defines {goog.DEBUG false}
                    :pretty-print    false}}
    ]}

  :main homepage.server

  :aot [homepage.server]

  :uberjar-name "homepage.jar"

  :prep-tasks [["cljsbuild" "once" "min"]["garden" "once"] "compile"])
