(ns bcs.tools
  (:require [conch.core :as conch]))

(def ^:dynamic *timeout* 10000)

(defmulti evaluate
  "Evaluate code. Takes a map containing (at the least) :lang
   and :code"
  :lang)

(defmacro raw-time
  "Same as clojure.core/time but returns a vector of a the result of
   the code and the milliseconds rather than printing a string and runs
   code in an implicit do."
  [& body]
  `(let [start# (System/nanoTime)
         ret# ~(cons 'do body)]
     [ret# (/ (double (- (System/nanoTime) start#)) 1000000.0)]))

(defn sandboxed
  "Run the application in the sandbox with args."
  [app & args]
  (let [proc (apply conch/proc "unbuffer" app args)
        out (future (conch/stream-to-string proc :out))
        err (future (conch/stream-to-string proc :err ))]
    (let [[exit-code ms] (raw-time (conch/exit-code proc *timeout*))]
      {:exit-code exit-code
       :out @out
       :err @err
       :elapsed-time ms})))