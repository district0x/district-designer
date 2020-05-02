(ns ^:figwheel-always district-designer.server.system.dev
  (:require [cljs.nodejs :as nodejs]))

(nodejs/enable-util-print!)

(defn on-jsload []
  (println "js reload"))

(defn -main [& args]
  (println "started"))

(set! *main-cli-fn* -main)