(ns dev.nrepl
  (:require [cider.piggieback :as p]
            [com.stuartsierra.component :as c]
            [nrepl.server :as nrepl]
            [shadow.cljs.devtools.server.nrepl :as shadow-nrepl]))

(defn start-nrepl!
  [{:keys [port]  :as c}]
  (spit ".nrepl-port" port)
  (println "starting nrepl on port " port)
  (assoc c :nrepl/server
         (nrepl/start-server
          :handler (nrepl/default-handler #'shadow-nrepl/middleware #'p/wrap-cljs-repl)
          :port port)))

(defrecord Component []
  c/Lifecycle
  (start [c] (start-nrepl! c) c)
  (stop [_]))

(defn make-component
  [{:nrepl/keys [port]}]
  (map->Component {:port port}))
