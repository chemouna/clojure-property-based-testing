(ns property-based-testing.testing-number-addition
  (:require  [clojure.test :as t])
  ;(:use clojure.test)
  (:require [clojure.test.check :as tc]
            [clojure.test.check.generators :as gen]
            [clojure.test.check.properties :as prop]
            [clojure.test.check.clojure-test :as ct :refer (defspec)]))

; Associativity.

(defspec number-addition-associativity-prop
  (prop/for-all [a gen/int b gen/int c gen/int]
    (= (+ a (+ b c)) (+ (+ a b) c))))

; Exercise: What happens if you use floats or bytes instead of ints?

(defspec number-addition-associativity-prop-byte
  (prop/for-all [a gen/byte b gen/byte c gen/byte]
    (= (+ a (+ b c)) (+ (+ a b) c))))

; Commutativity.

(defspec number-addition-commutativity-prop
  (prop/for-all [a gen/int b gen/int]
    (= (+ a b) (+ b a))))

; Addition with the identity (zero)

(defspec number-addition-with-identity-prop
  (prop/for-all [a gen/int]
    (and (= a (+ a 0)) (= (+ a 0) a))))

