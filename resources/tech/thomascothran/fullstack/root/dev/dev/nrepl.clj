(ns dev.nrepl
  (:require [cider.piggieback :as p]
            [com.stuartsierra.component :as c]
            [nrepl.server :as nrepl]
            [shadow.cljs.devtools.server.nrepl :as shadow-nrepl]))

(defn start-nrepl!
  [{:keys [port] :as c}]
  (spit ".nrepl-port" port)
  (println "starting nrepl on port " port)
  (assoc c :nrepl/server
         (nrepl/start-server
          :handler (nrepl/default-handler #'shadow-nrepl/middleware #'p/wrap-cljs-repl)
          :port port)))

(defrecord Component [port]
  c/Lifecycle
  (start [c]
    (println "about to call start with " c)
    (start-nrepl! c))
  (stop [_]))

(defn make-component
  [{:nrepl/keys [port]}]
  (assert port)
  (println "Making nrepl component")
  (Component. port))
