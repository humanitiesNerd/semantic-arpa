(ns user
  (:require [reloaded.repl :refer [system init start stop go reset]]
            [semantic-arpa.systems :refer [dev-system]]
            
            
            ))

(reloaded.repl/set-init! dev-system)
