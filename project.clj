(defproject district-designer "0.0.1"
  :description "Create and trade provably rare digital assets on the Ethereum blockchain"
  :url "https://github.com/district0x/memefactory"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :dependencies [[hodur/datomic-schema "0.1.0"]
                 [hodur/engine "0.1.7"]
                 [hodur/graphviz-schema "0.1.1"]
                 [hodur/spec-schema "0.1.5"]
                 [hodur/visualizer-schema "0.1.1"]
                 [org.clojure/clojurescript "1.10.439"]]

  :source-paths ["src" "test" "resources"]

  :plugins [[lein-cljsbuild "1.1.7"]
            [lein-figwheel "0.5.18"]
            [lein-npm "0.6.2"]
            [lein-doo "0.1.8"]]

  :doo {:paths {:karma "./node_modules/karma/bin/karma"}}

  :figwheel {:css-dirs ["resources/public/css"]
             :repl-eval-timeout 120000}

  :npm {:dependencies [[source-map-support "0.5.3"]
                       [ws "4.0.0"]
                       [dotenv "8.0.0"]
                       ["@openzeppelin/contracts" "3.0.1"]
                       ["@aragon/apps-shared-minime" "1.0.2"]]}

  :profiles {:dev {:dependencies [[org.clojure/clojure "1.9.0"]
                                  [binaryage/devtools "0.9.10"]
                                  [cider/piggieback "0.4.0"]
                                  [figwheel-sidecar "0.5.18"]
                                  [org.clojure/clojure "1.9.0"]
                                  [org.clojure/tools.reader "1.3.0"]]
                   :repl-options {:nrepl-middleware [cider.piggieback/wrap-cljs-repl]}
                   :source-paths ["dev" "src"]
                   :resource-paths ["resources"]}}


  :cljsbuild {:builds [{:id "dev-server"
                        :source-paths ["src/district_designer/server" "src/district_designer/shared"]
                        :figwheel {:on-jsload "district-designer.server.system.dev/on-jsload"}
                        :compiler {:main "district-designer.server.system.dev"
                                   :output-to "dev-server/district_designer.js"
                                   :output-dir "dev-server"
                                   :target :nodejs
                                   :optimizations :none
                                   :static-fns true
                                   :fn-invoke-direct true
                                   :anon-fn-naming-policy :mapped
                                   :source-map true}}
                       {:id "server-tests"
                        :source-paths ["src/district_registry/server"
                                       "src/district_registry/shared"
                                       "src/tokens/server"
                                       "src/tokens/shared"
                                       "src/users/server"
                                       "src/users/shared"
                                       "src/marketplace/server"
                                       "src/marketplace/shared"
                                       "src/tcr/server"
                                       "src/tcr/shared"
                                       "test/server_tests"]
                        :compiler {:main "server-tests.runner"
                                   :output-to "server-tests/server-tests.js"
                                   :output-dir "server-tests"
                                   :target :nodejs
                                   :optimizations :none
                                   :verbose false
                                   :source-map true}}]})
