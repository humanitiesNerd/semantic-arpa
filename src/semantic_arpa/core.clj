(ns semantic_arpa.core
  (:require
   [clojure.java.io :as io]
   [clojure.data.csv :as csv]))


(defn lines [path]
  (csv/read-csv (io/reader (io/file path))))

(defn as-ttl [[datetime substance value measure station lat lon :as record]]
  record)

(defn main [path]
  (map as-ttl (lines path)))
