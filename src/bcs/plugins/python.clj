(ns bcs.plugins.python
  (:require [bcs.tools :as tools :refer [evaluate]]))

(defmethod evaluate :python [req]
  (tools/sandboxed "python" "-c" (:code req)))