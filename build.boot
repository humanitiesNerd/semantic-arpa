(set-env!
  :project 'hoplon-semantic-test
  :version "0.1.0-SNAPSHOT"
  :dependencies '[]
  :exclusions '[]
  :source-paths #{"src"}
  :resource-paths #{"resources"}
  )

(require
;  '[adzerk.boot-cljs-repl :refer [cljs-repl start-repl]]
)

(deftask dev
  "Build semantic-arpa for local development."
  []
  (comp
  )

(deftask prod
  "Build semantic-arpa for production deployment."
  []
  (comp
  )
