(ns dev.main
  "Compose and run the dev system"
  (:require [{{top/ns}}.server :as server]
            [dev.nrepl :as nrepl]
            [dev.malli]
            [{{top/ns}}.logging :as logging]
            [com.stuartsierra.component :as c]))

(def system
  (c/system-map
    :server (server/component {:hot-reload true :port 3333})
    :nrepl (nrepl/make-component {:port 3255})
    :shadow (shadow/make-component {:shadow-cljs/builds [:dev :test]})
    :malli (dev.malli/make-component)
    :logging (logging/make-component {:publisher-opts [{:type :console}]})))
  
