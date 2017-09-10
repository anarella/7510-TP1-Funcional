(ns parser-test
  (:require [clojure.test :refer :all]
            [parser :refer :all]))

(def empty-input "")
(def blank-input "       ")
(def nil-input nil)
(def valid-input "
  varon(juan).

  mujer(maria).

")
(def fact "  varon (juan). ")
(def rule "hijo(X, Y) :- varon(X), padre(Y, X).")
(def valid-fact-line "varon(juan).")
(def no-dot-line "varon")
(def valid-rule-line "hijo(X,Y):-varon(X),padre(Y,X).")
(def invalid-rule-line "hijo(x,Y):-varon(X),padre(Y,X).")


(deftest read-lines-empty-input-test
  (testing "Empty input returns nil"
           (is
             (= (read-lines empty-input)
                nil))))

(deftest read-lines-blank-input-test
  (testing "Blank input returns nil"
           (is
             (= (read-lines blank-input)
                nil))))

(deftest read-lines-input-test
  (testing "nil input returns nil"
           (is
             (= (read-lines nil-input)
                nil))))

(deftest read-lines-valid-input-test
  (testing "Valid input returns list of lines"
           (is
             (= (read-lines valid-input)
                '("varon(juan).", "mujer(maria).")))))

(deftest remove-spaces-fact-test
  (testing "Fact line returns fact line without spaces"
           (is
            (= (remove-spaces fact)
               "varon(juan)."))))

(deftest remove-spaces-rule-test
  (testing "Rule line returns rule line without spaces"
           (is
            (= (remove-spaces rule)
               "hijo(X,Y):-varon(X),padre(Y,X)."))))

(deftest check-line-syntax-valid-fact-line-test
  (testing "Fact line with correct syntax returns true"
           (is
            (= (check-line-syntax valid-fact-line)
               true))))

(deftest check-line-syntax-no-dot-line-test
  (testing "Line without dot as last character returns false"
           (is
            (= (check-line-syntax no-dot-line)
               false))))

(deftest check-line-syntax-valid-rule-line-test
  (testing "Rule line with correct syntax returns true"
           (is
            (= (check-line-syntax valid-rule-line)
               true))))

(deftest check-line-syntax-invalid-rule-line-test
  (testing "Rule line with a lowcase parameter returns false"
           (is
            (= (check-line-syntax invalid-rule-line)
               false))))

