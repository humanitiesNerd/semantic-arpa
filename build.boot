(set-env!
  :project 'semantic-arpa
  :version "0.1.0-SNAPSHOT"
  :dependencies '[[org.clojure/tools.namespace "0.2.11"]]
  :exclusions '[]
  :source-paths #{"src"}
  :resource-paths #{"resources"}
  )

;(require
;  '[adzerk.boot-cljs-repl :refer [cljs-repl start-repl]]
;)

(deftask dev
  "Build semantic-arpa for local development."
  []
  
  )

(deftask prod
  "Build semantic-arpa for production deployment."
  []
  (comp
  ))
