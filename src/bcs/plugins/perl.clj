(ns bcs.plugins.perl
  (:require [bcs.tools :as tools :refer [evaluate]]))

(defmethod evaluate :perl [req]
  (tools/sandboxed "perl" "-e" (:code req)))