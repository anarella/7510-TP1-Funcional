(ns parser-test
  (:require [clojure.test :refer :all]
            [parser :refer :all]))

(def empty-input "")
(def blank-input "       ")
(def nil-input nil)
(def valid-input
  "
 varon(juan).
 mujer(maria).
 ")

(deftest empty-input-test
  (testing "Empty input returns nil"
           (is
             (= (read-lines empty-input)
                nil))))

(deftest blank-input-test
  (testing "Blank input returns nil"
           (is
             (= (read-lines blank-input)
                nil))))

(deftest nil-input-test
  (testing "nil input returns nil"
           (is
             (= (read-lines nil-input)
                nil))))

(deftest valid-input-test
  (testing "Valid input returns list of lines"
           (is
             (= (read-lines valid-input)
                '("varon(juan).", "mujer(maria).")))))