(ns parser
  (:require [clojure.string :as str]))

(def space-character #"\s")
(def dot-character ".")
(def rule-indicator ":-")
(def opening-parentheses "(")
(def closing-parentheses ")")
(def opening-parentheses-char \()
(def closing-parentheses-char \))
(def rule-indicator-length 2)
(def not-found -1)
(def initial-position 0)

(defn remove-spaces
  "Receives a string and returns it containing no spaces."
  [string]
  (str/replace string space-character "")
  )

(defn read-lines
  "Receives a string input and returns a list of the not empty lines contained
  in it. If the string is empty, contains only whitespaces or is nil the
  function returns nil."
  [input]
  (if
    (str/blank? input) nil
    ;; Remove empty lines and spaces inside lines
    (map remove-spaces (filter not-empty (str/split-lines input))))
  )

(defn check-fact-syntax
  "Receives a fact from the input and validates whether its syntax is correct or not,
  returning its correspondent boolean value."
  [fact]
  (let [opening-parentheses-index (.indexOf fact opening-parentheses)]
    (if
      (and (> opening-parentheses-index not-found)
           (>= (.indexOf fact closing-parentheses) opening-parentheses-index))
      true false))
  )

(defn validate-left-side
  "Receives the left side of a rule and checks the syntax of it, returning its
  correspondent boolean value."
  [left-string]
  (let [opening-parentheses-index (.indexOf left-string opening-parentheses)]
    (if
      (and (> opening-parentheses-index not-found)
           (>= (.indexOf left-string closing-parentheses) opening-parentheses-index))
      true false))
  )

(defn validate-right-side
  "Receives the right side of a rule and checks the syntax of it, returning its
  correspondent boolean value."
  [right-string]
  (let [opening-parentheses-count
        (count(filter #(= % opening-parentheses-char)(seq right-string)))
        closing-parentheses-count
        (count(filter #(= % closing-parentheses-char)(seq right-string)))]
    (if (= opening-parentheses-count closing-parentheses-count) true false))
  )

(defn check-rule-syntax
  "Receives a rule from the input and validates whether its syntax is correct or not,
  returning its correspondent boolean value."
  [rule]
  (let [indicator-position (.indexOf rule rule-indicator)
        left-side (subs rule initial-position indicator-position)
        right-side (subs rule (+ indicator-position rule-indicator-length))]
    (if
      (and (validate-left-side left-side) (validate-right-side right-side))
      true false))
  )

(defn check-line-syntax
  "Receives a line and returns true if it meets the defined syntax conditions,
  and false otherwise. The line must have a dot as last character, in order to
  be correct. Also, the valid format defined for facts and rules is:
  Facts : fact(x1, x2, ..., xn)
  Rules :
  rule(X1, X2, ..., Xn) :- fact1(X1, ..., Xn), fact2(X1, ..., Xm), ..., factk (X1, ..., Xl)"
  [line]
  (if
    (not (str/ends-with? line dot-character)) false
    (if (str/includes? line rule-indicator)
      (check-rule-syntax line)
      (check-fact-syntax line)))
  )
