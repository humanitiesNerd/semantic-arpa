(ns user
  (:require [reloaded.repl :refer [system init start stop go reset]]
            [semantic_arpa.systems :refer [dev-system]]
            [semantic_arpa.core :as arpa]
            
            
            ))

(reloaded.repl/set-init! dev-system)


(def a
  (mapcat arpa/as-ttl (arpa/lines "resources/piccolo.csv")))

     

(def b (into
        (vector "\t" "ssn:observerdProperty " "\"" substance "\"" " ,")
        (arpa/observedProperties substance)
        (vector " ;" "\n")))

