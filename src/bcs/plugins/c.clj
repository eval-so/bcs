(ns bcs.plugins.c
  (:require [bcs.tools :as tools :refer [evaluate]])
  (:import java.io.File))

(defmethod evaluate :c [req]
  (let [cfile (File/createTempFile "breakp" ".c")
        executable (File/createTempFile "breakp" "")]
    (spit cfile (:code req))
    (let [compiled (tools/unsandboxed "gcc" "-o" (.getPath executable) (.getPath cfile))]
      (if (= 0 (:exit-code compiled))
        (assoc (tools/sandboxed (.getPath executable)) :gcc compiled)
        compiled))))