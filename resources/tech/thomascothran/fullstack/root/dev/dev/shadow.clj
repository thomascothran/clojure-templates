(ns dev.shadow
  (:require [shadow.cljs.devtools.server :as server]
            [shadow.cljs.devtools.api :as shadow]
            [com.stuartsierra.component :as c]))

(defn start!
  [{:keys [builds]}]
  (server/start!)
  (doseq [build builds]
    (shadow/watch build)))

(defrecord Component [builds]
  c/Lifecycle

  (start [c]
    (start! c)
    c)

  (stop [_]))

(defn make-component
  [{:shadow-cljs/keys [builds]}]
  (map->Component {:builds builds}))
