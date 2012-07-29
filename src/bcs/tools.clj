(ns bcs.tools
  (:require [conch.core :as conch]))

(defmulti evaluate
  "Evaluate code. Takes a map containing (at the least) :lang
   and :code"
  :lang)

(defn sandboxed
  "Run the application in the sandbox with args and send it the
   code on stdin."
  [app code & args]
  (let [proc (apply conch/proc app args)]
    (conch/feed-from-string proc code)
    (conch/done proc)
    (conch/stream-to-string proc :out)))