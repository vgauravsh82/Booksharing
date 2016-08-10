(ns booksharing.env
  (:require [clojure.tools.logging :as log]))

(def defaults
  {:init
   (fn []
     (log/info "\n-=[booksharing started successfully]=-"))
   :stop
   (fn []
     (log/info "\n-=[booksharing has shut down successfully]=-"))
   :middleware identity})
