(ns parser
  (:require [clojure.string :as str]))

(defn remove-spaces
  "Receives a string and returns it containing no spaces."
  [string]
  (str/replace string #"\s" "")
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

(defn check-line-syntax
  "Receives a line and returns true if it meets the defined syntax conditions,
  and false otherwise. The line must have a dot as last character, in order to
  be correct. Also, the valid format defined for facts and rules is:
  Facts : fact(x1, x2, ..., xn)
  Rules :
  rule(X1, X2, ..., Xn) :- fact1(X1, ..., Xn), fact2(X1, ..., Xm), ..., factk (X1, ..., Xl)
  Consider x as lowercase characters and X as uppercase ones."
  [line]
  )
