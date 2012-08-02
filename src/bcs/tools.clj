(ns bcs.tools
  (:require [conch.core :as conch]
            [useful.state :refer [with-timing]]))

(def ^:dynamic *timeout* 10000)

(defmulti evaluate
  "Evaluate code. Takes a map containing (at the least) :lang
   and :code"
  :lang)

(defn- shell-out
  "Run the application with args. Wraps the application in unbuffer to prevent
   output buffering. Returns a map of exit-code, output, err, and the time it
   took for the program to finish."
  [app sandbox? & args]
  (let [proc (apply conch/proc "unbuffer" app (remove nil? args))
        out (future (conch/stream-to-string proc :out))
        err (future (conch/stream-to-string proc :err))]
    (let [[exit-code ms] (with-timing (conch/exit-code proc *timeout*))]
      {:exit-code exit-code
       :out @out
       :err @err
       :elapsed-time ms})))

(defn unsandboxed
  "Run application with args. Does not use the selinux sandbox but
   does use *timeout* to timeout if necessary."
  [app & args]
  (apply shell-out app false args))

;; Protip: there is no sandbox yet.
(defn sandboxed.
  "Run the application with args inside of the sandbox."
  [app & args]
  (apply shell-out app true args))