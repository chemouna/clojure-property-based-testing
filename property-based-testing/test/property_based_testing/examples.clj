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

;; testing clojure reverse function

(defspec reverse-first-becomes-last-prop
  100
  (prop/for-all [v (gen/vector gen/int)]
    (= (first v) (last (reverse v)))))

(defspec reverse-perserves-count-prop
  100
  (prop/for-all [v (gen/vector gen/int)]
    (= (count v) (count (reverse v)))))

(defspec reverse-two-times-same-prop
  100
  (prop/for-all [v (gen/vector gen/int)]
    (= v (reverse (reverse v)))))

(defspec reverse-two-times-same-prop2
  100
  (prop/for-all* [(gen/vector gen/int)]
                 (fn [v]
                   (= v (reverse (reverse v))))))


;; (gen/sample (gen/choose 5 10))
;; (gen/sample (gen/elements ["clojure", "Java", "Ruby", "Haskell"]))
;; (gen/sample (gen/map gen/keyword gen/nat))
;; (gen/sample (gen/tuple gen/string-alpha-numeric gen/int))
;; (gen/sample (gen/container-type gen/int))

;; (gen/sample (gen/elements [:a :b :c]))
;; (gen/sample (gen/one-of [gen/int (gen/return nil)]))
;; (gen/sample (gen/frequency [[9 gen/int]
;;                             [1 (gen/return nil)]]))

;; (gen/sample (gen/fmap set (gen/vector gen/nat)))

;; (def keyword-vector (gen/such-that not-empty (gen/vector gen/keyword)))

;; (def vec-and-elem
;;   (gen/bind keyword-vector
;;             (fn [v] (gen/tuple (gen/elements v) (gen/return v))))

;(gen/sample vec-and-elem 4)

  ;; [[3 6]           ; Frame 1
  ;;  [9 0]           ; Frame 2
  ;;  :strike         ; Frame 3
  ;;  [8 1]           ; Frame 4
  ;;  [3 :spare]      ; Frame 5
  ;;  [3 1]           ; Frame 6
  ;;  [4 3]           ; Frame 7
  ;;  [2 6]           ; Frame 8
  ;;  [9 0]           ; Frame 9
  ;;  [3 :spare 8]]   ; Frame 10 (note: 10th frames sometimes have a third roll)


;; (def first-roll-1
;;   (gen/one-of [(gen/choose 0 9)
;;                  (gen/return :strike)]))

;; (gen/sample first-roll)

;; ;; let's reduce the frequency of strikes 
;;   (def first-roll
;;     (gen/frequency [[10 (gen/choose 0 9)]
;;                     [1 (gen/return :strike)]]))

;;   (gen/sample first-roll-freq-strikes)

;;   (defn second-roll
;;     [roll]
;;     (if (= :strike roll)
;;       (gen/return :strike)
;;       (let [pins-left (- 10 roll)]
;;         (gen/tuple (gen/return roll)
;;                    (gen/one-of [(gen/choose 0 (dec pins-left))
;;                      (gen/return :spare)])))))

;;   (def frame
;;     (gen/bind first-roll second-roll))

;;   (gen/sample frame)
