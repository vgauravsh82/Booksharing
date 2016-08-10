(ns user
  (:require [mount.core :as mount]
            booksharing.core))

(defn start []
  (mount/start-without #'booksharing.core/repl-server))

(defn stop []
  (mount/stop-except #'booksharing.core/repl-server))

(defn restart []
  (stop)
  (start))


