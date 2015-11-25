(ns semantic_arpa.core
  (:require
   [clojure.java.io :as io]
   [clojure.data.csv :as csv]
   [clj-time.format :as f]
   [clj-time.core :as t]))

(defn writelines [file-path lines]
  (with-open [out-file (io/writer file-path)]
    (doseq [line lines] (.write out-file line))))

(def reconciliated-values
  {"NO2" {
          :dbpedia "Nitrogen_dioxide"
          :dbpedia-it "Diossido_di_azoto"}
   "CO" {
         :dbpedia "Carbon_monoxide"
         :dbpedia-it "Monossido_di_carbonio"}
   "HC" {
         :dbpedia "Hydrocarbon"
         :dbpedia-it "Idrocarburi"}
   "NO" {
         :dbpedia "Nitric_oxide"
         :dbpedia-it "Monossido_di_azoto" }
   "NOx" {
          :dbpedia "NOx"
          :dbpedia-it "NOx"}
   "O3" {
         :dbpedia "Ozone"
         :dbpedia-it "Ozono" }
   "PM10" {
           :dbpedia "Particulates"
           :dbpedia-it "PM10" }
   "SO2" {
          :dbpedia "Sulfur_dioxide"
          :dbpedia-it "Diossido_di_zolfo"}
   "CH4" {
          :openarpa "CH4"}
   "H2S" {
          :openarpa "H2S"}
   "NMHC" {
           :openarpa "NMHC"}
   })

;"\t" "ssn:observerdProperty " "\"" substance "\"" " ;" "\n"
(defn observedProperties [substance]
  (let [stuff (reconciliated-values substance)]
    (apply str (interpose ", " (mapv (fn [item] (str (name (first item)) ":" (second item)))  stuff)))
    ))

(def multiparser (f/formatter-local "dd/MM/YYYY HH:mm"))
(def id-formatter (f/formatter-local "ddMMYYYYHHmmss"))
(def rdf-formatter (f/formatter-local "dd/MM/YYYY HH:mm:ss"))
(def daytime-formatter (f/formatter-local "HH:mm:ss"))

(defn parsed-datetime [datetime]
  (f/parse-local multiparser datetime))

(defn unparsed-datetime [formatter datetime]
  (f/unparse-local formatter datetime))

(defn year [datetime]
  (t/year datetime))

(defn month [datetime]
  (t/month datetime))

(defn day [datetime]
  (t/day datetime))

(defn lines [path]
  (csv/read-csv (io/reader (io/file path))))

(defn as-ttl [[datetime substance value measure station lat lon :as record]]
  (let [parsed-datetime (parsed-datetime datetime)]
    [
     (str "\n" "openarpa-obs:" station substance
          (unparsed-datetime id-formatter parsed-datetime)
          " a " "ssn:Observation" " ;" "\n")
     (str "\t" "ssn:observationResultTime "
          "\"" (unparsed-datetime rdf-formatter parsed-datetime) "\""
          "^^xsd:dateTime ;" "\n")
     (str "\t" "ssn:isProducedBy openarpa-sens:" station " ;" "\n")
     (str "\t" "time:month " "\"" (month parsed-datetime) "\"" "^^xsd:gMonth ;" "\n")
     (str "\t" "time:year " "\"" (year parsed-datetime) "\"" "^^xsd:gYear ;" "\n")
     (str "\t" "time:day " "\"" (month parsed-datetime) "\"" "^^xsd:gDay ;" "\n")
     (str "\t" "time:inDateTime " "\"" (unparsed-datetime daytime-formatter parsed-datetime) "\"" "^^xsd:time ;" "\n")
     (str "\t" "basic:uom " "\"" measure "\"" " ;" "\n")
     ]
    )
  )

(defn main [path]
  (writelines "resources/openarpa.ttl"
              (mapcat as-ttl (lines path))
              ))
