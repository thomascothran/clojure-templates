(ns dev.malli
  (:require [malli.dev :as dev]
            [malli.dev.pretty :as pretty]
            [com.stuartsierra.component :as c]))

(defrecord Component [_]
  c/Lifecycle
  (start [_]
    (dev/start! {:report (pretty/thrower)}))
  (stop [_]
    (dev/stop!)))

(defn make-component
  [m]
  (Component. m))
