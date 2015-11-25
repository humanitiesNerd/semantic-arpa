(ns user
  (:require [reloaded.repl :refer [system init start stop go reset]]
            [semantic_arpa.systems :refer [dev-system]]
            [semantic_arpa.core :refer [main]]
            
            
            ))

(reloaded.repl/set-init! dev-system)
