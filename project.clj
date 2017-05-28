(defproject info.youhavethewrong/parts "1.0-SNAPSHOT"
  :description "A UI for keeping track of parts used for maintenance."
  :license {:name "Apache License, Version 2.0"
            :url "https://www.apache.org/licenses/LICENSE-2.0"}
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/clojurescript "1.7.170"]
                 [reagent "0.6.0"]]
  :hooks [leiningen.cljsbuild]

  :plugins [[lein-cljsbuild "1.1.3"]]
  :cljsbuild {
              :builds [{:source-paths ["src/cljs"]
                        :compiler {:output-to "resources/public/js/app.js"
                                   :optimizations :whitespace
                                   :pretty-print true}}]})
