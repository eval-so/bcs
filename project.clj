(defproject bcs "0.1.0"
  :description "The compilation system for breakpoint-eval."
  :url "https://github.com/breakpoint-eval/bcs"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [conch "0.3.0"]
                 [bultitude "0.1.7"]]
  :main bcs.core/-main)
