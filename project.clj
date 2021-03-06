(defproject homepage "0.1.0-SNAPSHOT"
  :description "Rory How homepage"
  :url "http://www.roryhow.com"
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [org.clojure/clojurescript "1.10.439"]
                 [org.clojure/core.async "0.4.490"]
                 [reagent "0.8.1"]
                 [re-frame "0.10.6"]
                 [environ "1.1.0"]
                 [secretary "1.2.3"]
                 [org.roman01la/cljss "1.6.3"]
                 [morse "0.4.0"]
                 [ns-tracker "0.3.1"]
                 [compojure "1.6.1"]
                 [cljs-http "0.1.45"]
                 [clj-http "3.9.1"]
                 [hiccup "1.0.5"]
                 [venantius/accountant "0.2.4"]
                 [yogthos/config "1.1.1"]
                 [ring/ring-defaults "0.3.2"]
                 [ring/ring-json "0.4.0"]
                 [bk/ring-gzip "0.3.0"]
                 [ring "1.7.1"]]

  :plugins [[lein-cljsbuild "1.1.7"]
            [lein-environ "1.1.0"]]

  :min-lein-version "2.5.3"

  :source-paths ["src/clj"]

  :clean-targets ^{:protect false} ["resources/public/js/compiled" "target"
                                    "resources/public/css"]

  :figwheel {:css-dirs ["resources/public/css"]
             :ring-handler homepage.handler/handler
             :server-logfile false}

  :aliases {"dev" ["do" "clean"
                   ["repl" ":headless" ":port" "50766"]]
            "build" ["with-profile" "+prod,-dev" "do"
                     ["clean"]
                     ["cljsbuild" "once" "min"]]}

  :profiles
  {:dev [:project/dev :profiles/dev]
   :prod [:project/prod :profiles/prod]
   :uberjar [:project/uberjar :profiles/uberjar]

   ;; dev env
   :profiles/dev {}
   :project/dev
   {:dependencies [[binaryage/devtools "0.9.10"]
                   [figwheel-sidecar "0.5.18"]
                   [ring/ring-mock "0.3.2"]
                   [ring/ring-devel "1.7.1"]
                   [prone "1.6.1"]
                   [cider/piggieback "0.3.10"]
                   [day8.re-frame/tracing "0.5.1"]
                   [day8.re-frame/re-frame-10x "0.3.6"]]

    :repl-options {:nrepl-middleware [cider.piggieback/wrap-cljs-repl]
                   :init (do (use 'figwheel-sidecar.repl-api)
                             (start-figwheel!))
                   :port 50766}

    :plugins      [[lein-figwheel "0.5.16"]
                   [lein-pdo "0.1.1"]
                   [cider/cider-nrepl "0.18.0"]]

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
     :figwheel     {:on-jsload "homepage.core/mount-root"
                    :open-urls ["http://localhost:3449/"]}
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

  :prep-tasks [["cljsbuild" "once" "min"] "compile"])
