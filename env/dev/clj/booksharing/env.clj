(ns booksharing.env
  (:require [selmer.parser :as parser]
            [clojure.tools.logging :as log]
            [booksharing.dev-middleware :refer [wrap-dev]]))

(def defaults
  {:init
   (fn []
     (parser/cache-off!)
     (log/info "\n-=[booksharing started successfully using the development profile]=-"))
   :stop
   (fn []
     (log/info "\n-=[booksharing has shut down successfully]=-"))
   :middleware wrap-dev})
