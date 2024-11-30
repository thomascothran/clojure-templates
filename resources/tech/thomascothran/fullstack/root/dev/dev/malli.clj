(ns dev.malli
  (:require [malli.dev :as dev]
            [malli.dev.pretty :as pretty]
            [com.stuartsierra.component :as c]))

(defrecord Component []
  c/Lifecycle
  (start []
    (dev/start! {:report (pretty/reporter)}))
  (stop []
    (dev/stop!)))

(defn make-component
  []
  (Component.))
