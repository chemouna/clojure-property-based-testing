(ns property-based-testing.examples
  (:require  [clojure.test :as t])
  (:require [clojure.test.check :as tc]
          [clojure.test.check.generators :as gen]
          [clojure.test.check.properties :as prop]
          [clojure.test.check.clojure-test :as ct :refer (defspec)]))

(defspec sort-idempotent-prop
  100
  (prop/for-all [v (gen/vector gen/int)]
    (= (sort v) (sort (sort v)))))

