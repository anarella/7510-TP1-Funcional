(ns parser
  (:require [clojure.string :as str]))

(defn read-lines
  "Receives a string input and returns a list of the not empty lines contained
  in it. If the string is empty, contains only whitespaces or is nil the
  function returns nil."
  [input]
  (if
    (str/blank? input) nil
    ;; Remove empty lines and spaces inside lines
    (map #(str/replace % " " "")
         (filter not-empty (str/split-lines input))))
  )

(defn check-line-syntax
  "Receives a line and returns true if it meets the defined syntax conditions,
  and false otherwise. The line must have a dot as last character, in order to
  be correct. Also, the valid format defined for facts and rules is:
  Facts : fact(x1, x2, ..., xn)
  Rules :
  rule(x1, x2, ..., xn) :- fact1(x1, ..., xn), fact2(x1, ..., xm), ..., factk (x1, ..., xl)"
  [line]
  )
