(ns bcs.core
  (:require [bcs.tools :refer [evaluate]]
            [clojure.string :refer [join]]
            [bultitude.core :refer [namespaces-on-classpath]]))

(defn load-plugins
  "Load all of the language plugins on the classpath (all files with
   namespaces beginning with bcs.plugins). If a truthly arg is passed,
   reload them all if they're already loaded (otherwise require wont need
   to do anything)."
  [& [reload?]]
  (doseq [n (namespaces-on-classpath :prefix "bcs.plugins")]
    (if reload?
      (require n :reload)
      (require n))))

;; This would become more complex in the future.
(defn handle-request
  "Takes an evaluation request and sends it along to evaluate."
  [req]
  (evaluate req))

;; Entry point/example of usage
(defn -main [& args]
  (load-plugins)
  (let [[lang & code] args
        code (join " " code)]
    (println (handle-request {:lang (keyword lang) :code code}))))
