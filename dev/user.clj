(ns user
  (:require [figwheel-sidecar.repl-api]))

(defn start-server! []
  (figwheel-sidecar.repl-api/start-figwheel!
    (-> (figwheel-sidecar.config/fetch-config))
    "dev-server")
  (figwheel-sidecar.repl-api/cljs-repl "dev-server"))

(comment
  (start-server!))