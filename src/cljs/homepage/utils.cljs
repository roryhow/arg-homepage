(ns homepage.utils)

(defn clj->json [data] (.stringify js/JSON (clj->js data)))
