(ns fwpd.core
  (:gen-class))

(require '[clojure.string :as string])

(defn parse-csv-lines [raw-csv]
  (string/split raw-csv #"\n"))

(defn parse-csv-line [csv-line]
  (string/split csv-line #","))

(defn parse-csv [raw-csv]
  (let [csv-lines (parse-csv-lines raw-csv)]
    (map parse-csv-line csv-lines)))

(defn map-csv-line-to-vampire [[name glitter-index]]
  {:name name
   :glitter-index (Integer. glitter-index)})

(defn convert-parsed-content-to-vampire-records [parsed-content]
  (into [] (map map-csv-line-to-vampire parsed-content)))

(defn create-glitter-filter [minimum-glitter]
  #(>= (:glitter-index %) minimum-glitter))

(defn -main [& _]
  (let [file-name "suspects.csv"
        file-content (slurp file-name)
        parsed-content (parse-csv file-content)
        vampire-records (convert-parsed-content-to-vampire-records parsed-content)
        glitter-filter (create-glitter-filter 3)]
    (filter glitter-filter vampire-records)))

(-main)