(ns dev.main
  "Compose and run the dev system"
  (:require [{{top/ns}}.server :as server]
            [dev.nrepl :as nrepl]
            [dev.malli]
            [dev.shadow :as shadow]
            [{{top/ns}}.logging :as logging]
            [com.stuartsierra.component :as c]))

(defn system
  [{:keys [port nrepl-port]}]
  (let [port (or port 3333)
        nrepl-port (or nrepl-port 3255)]
   (c/system-map
     :server (server/make-component {:hot-reload true 
                                     :port port})
     :nrepl (nrepl/make-component {:nrepl/port nrepl-port})
     :shadow (shadow/make-component {:shadow-cljs/builds [:app :test]})
     :malli (dev.malli/make-component nil)
     :logging (logging/make-component {:publisher-opts [{:type :console}]}))))
  
