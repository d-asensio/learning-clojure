(ns symetric-hobbit.core
  (:gen-class))

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


(defn matching-part
  [{:keys [name size]}]
  {:name (clojure.string/replace name #"^left-" "right-")
   :size size})

(defn symmetrize-body-parts-reduce
  [asym-body-parts]
  (reduce (fn [acc-parts part]
     (into acc-parts (set [part (matching-part part)])))
   []
   asym-body-parts))

(defn -main
  [& args]
  (symmetrize-body-parts-reduce asym-hobbit-body-parts))


(-main)
