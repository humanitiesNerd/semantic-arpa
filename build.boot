(set-env!
  :project 'semantic-arpa
  :version "0.1.0-SNAPSHOT"
  :dependencies '[[org.clojure/tools.namespace "0.2.11"]
                  [org.clojure/data.csv "0.1.3"]
                  [clj-time "0.11.0"]
                  [org.danielsz/system    "0.2.0"]]
  :exclusions '[]
  :source-paths #{"src"}
  :resource-paths #{"resources"}
  )

(require
  [com.stuartsierra.component :as component]
)

(deftask dev
  "Live repl in semantic-arpa for local development."
  []
  
  )
