(ns ^:figwheel-always district-designer.system.dev
  (:require
    [cljs.nodejs :as nodejs]
    [district-designer.server.graphql-resolvers]
    [district-designer.server.syncer]
    [marketplace.server.graphql-resolvers]
    [marketplace.server.syncer]
    [tcr.server.graphql-resolvers]
    [tcr.server.syncer]
    [tokens.server.graphql-resolvers]
    [tokens.server.syncer]
    [users.server.graphql-resolvers]
    [users.server.syncer]))

(nodejs/enable-util-print!)

(defn on-jsload []
  (println "js reload"))

(defn -main [& args]
  (println "started"))

(set! *main-cli-fn* -main)