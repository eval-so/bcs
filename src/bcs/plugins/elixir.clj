(ns bcs.plugins.elixir
  (:require [bcs.tools :as tools :refer [evaluate]]))

(defmethod evaluate :elixir [req]
  (tools/sandboxed "elixir" "-e" (:code req)))