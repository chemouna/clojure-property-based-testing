(ns property-based-testing.quickcheck-paper-examples
  (:require  [clojure.test :as t])
  (:require [clojure.test.check :as tc]
            [clojure.test.check.generators :as gen]
            [clojure.test.check.properties :as prop]
            [clojure.test.check.clojure-test :as ct :refer (defspec)]))

(def iterations 10)

(defspec reverse-one-element-same-prop
  iterations
  (prop/for-all [v (gen/vector gen/any 1)]
    (= v (reverse v))))

(defspec reverse-two-times-same-prop
  iterations
  (prop/for-all [v (gen/vector gen/int)]
    (= v (reverse (reverse v)))))

(defspec reverse-concat-list-prop
  iterations
  (prop/for-all [v1 (gen/vector gen/int)
                 v2 (gen/vector gen/int)]
    (= (vec (reverse (concat v1 v2))) (vec (concat (reverse v1) (reverse v2))))))
