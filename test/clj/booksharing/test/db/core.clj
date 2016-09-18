(ns booksharing.test.db.core
(:require [booksharing.db.core :refer [*db*] :as db]
[luminus-migrations.core :as migrations]
[clojure.test :refer :all]
[clojure.java.jdbc :as jdbc]
[booksharing.config :refer [env]]
[mount.core :as mount]))
(use-fixtures
:once
(fn [f]
(mount/start
#'booksharing.config/env
#'booksharing.db.core/*db*)
(migrations/migrate ["migrate"] (select-keys env [:database-url]))
(f)))
(deftest test-messages
(jdbc/with-db-transaction [t-conn *db*]
(jdbc/db-set-rollback-only! t-conn)
(let [timestamp (java.util.Date.)]
(is (= 1 (db/save-message!
t-conn
{:book "Thinking in Java1"
:message "Hello, World"
:timestamp timestamp}
{:connection t-conn})))
(is (=
{:book "Thinking in Java1"
:message "Hello, World"
:timestamp timestamp}
(-> (db/get-messages t-conn {})
(first)
(select-keys [:book :message :timestamp])))))))