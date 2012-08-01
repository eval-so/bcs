(ns bcs.plugins.bash
  (:require [bcs.tools :as tools :refer [evaluate]]))

(defmethod evaluate :bash [req]
  (tools/sandboxed "bash" "-c" (:code req)))