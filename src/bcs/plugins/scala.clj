(ns bcs.plugins.scala
  (:require [bcs.tools :as tools :refer [evaluate]]))

(defmethod evaluate :scala [req]
  ;; Still going to be slower than a dead turtle if you don't have a JVM
  ;; that supports tiered compilation.
  (tools/sandboxed "scala" "-J-client" "-J-XX:+TieredCompilation" "-e" (:code req)))