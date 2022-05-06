(ns symetric-hobbit.core
  (:gen-class))

(require '[clojure.string :as string])

(def asym-hobbit-body-parts [{:name "head" :size 3}
                             {:name "left-eye" :size 1}
                             {:name "left-ear" :size 1}
                             {:name "mouth" :size 1}
                             {:name "nose" :size 1}
                             {:name "neck" :size 2}
                             {:name "left-shoulder" :size 3}
                             {:name "left-upper-arm" :size 3}
                             {:name "chest" :size 10}
                             {:name "back" :size 10}
                             {:name "left-forearm" :size 3}
                             {:name "abdomen" :size 6}
                             {:name "left-kidney" :size 1}
                             {:name "left-hand" :size 2}
                             {:name "left-knee" :size 2}
                             {:name "left-thigh" :size 4}
                             {:name "left-lower-leg" :size 3}
                             {:name "left-achilles" :size 1}
                             {:name "left-foot" :size 2}])


(defn matching-hobbit-part
  [{:keys [name] :as part}]
  (assoc part :name (string/replace name #"^left-" "right-")))

(defn expand-hobbit-part
  [part]
  (set [part (matching-hobbit-part part)]))

(defn symmetrize-body-parts
  [part-expander-fn asym-body-parts]
  (reduce
   #(into %1 (part-expander-fn %2))
   []
   asym-body-parts))

(def symmetrize-hobbit-parts (partial symmetrize-body-parts expand-hobbit-part))

(defn -main []
  (symmetrize-hobbit-parts asym-hobbit-body-parts))


(-main)
