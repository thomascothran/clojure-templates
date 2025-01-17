(ns dev
  (:require [{{top/ns}}.server :as server]
            [dev.main]
            [clojure.tools.namespace.repl :refer (refresh)]
            [com.stuartsierra.component :as component]))

(defonce system nil)

(defn init []
  (alter-var-root #'system
    (constantly (dev.main/system {}))))

(defn start []
  (alter-var-root #'system component/start))

(defn stop []
  (alter-var-root #'system
    (fn [s] (when s (component/stop s)))))

(defn go! [& _]
  (println "initiating dev system")
  (init)
  (println "starting dev system")
  (start)
  (println "dev system started:\n" system)
  @(promise))

(defn reset []
  (stop)
  (refresh :after 'user/go))
