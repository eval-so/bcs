(ns bcs.tools
  (:require [conch.core :as conch]))

(def ^:dynamic *timeout* 10000)

(defmulti evaluate
  "Evaluate code. Takes a map containing (at the least) :lang
   and :code"
  :lang)

(defn sandboxed
  "Run the application in the sandbox with args and send it the
   code on stdin."
  [app & args]
  (let [proc (apply conch/proc "unbuffer" app args)
        out (future (conch/stream-to-string proc :out))
        err (future (conch/stream-to-string proc :err ))]
    (conch/done proc)
    (let [exit-code (conch/exit-code proc *timeout*)]
      {:exit-code exit-code
       :out @out
       :err @err})))