(ns ^:figwheel-no-load homepage.dev
  (:require
   [homepage.core :as core]
   [devtools.core :as devtools]))

(devtools/install!)

(enable-console-print!)

(core/init)
