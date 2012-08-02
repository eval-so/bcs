(ns bcs.tools
  (:require [conch.core :as conch]
            [useful.state :refer [with-timing]]))

(def ^:dynamic *timeout* 10000)

(defmulti evaluate
  "Evaluate code. Takes a map containing (at the least) :lang
   and :code"
  :lang)

(defn sandboxed
  "Run the application in the sandbox with args."
  [app & args]
  (let [proc (apply conch/proc "unbuffer" app (remove nil? args))
        out (future (conch/stream-to-string proc :out))
        err (future (conch/stream-to-string proc :err))]
    (let [[exit-code ms] (with-timing (conch/exit-code proc *timeout*))]
      {:exit-code exit-code
       :out @out
       :err @err
       :elapsed-time ms})))