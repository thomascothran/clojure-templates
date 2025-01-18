(ns dev.malli
  (:require [malli.dev :as dev]
            [malli.dev.pretty :as pretty]
            [com.stuartsierra.component :as c]))

(defrecord Component [_]
  c/Lifecycle
  (start [c]
    (dev/start! {:report (pretty/thrower)})
    c)
  (stop [c]
    (dev/stop!)
    c))

(defn make-component
  [m]
  (Component. m))
