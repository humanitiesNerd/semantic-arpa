(ns semantic_arpa.systems
  (:require
   [com.stuartsierra.component :as component]
   [system.core :refer [defsystem]]
   (system.components 
    
    [repl-server :refer [new-repl-server]]
    
    )
  
   ;[my-app.webapp :refer [handler]]
   ;[environ.core :refer [env]]
   )
  )

;(defsystem dev-system 
;   [:repl-server (new-repl-server 1115)]
;   []
;  )

(defn dev-system []
  (component/system-map ))
