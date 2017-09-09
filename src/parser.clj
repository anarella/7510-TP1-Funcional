(ns parser
  (:require [clojure.string :as str]))

(defn read-lines
  "Process every line of the input given into a list of lines."
  [input]
  (if
    (str/blank? input)
    nil
    ;; Remove first empty line
    (filter not-empty (str/split-lines input))))
