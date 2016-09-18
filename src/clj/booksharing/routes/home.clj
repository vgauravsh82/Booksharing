(ns booksharing.routes.home
  (:require [booksharing.layout :as layout]
            [compojure.core :refer [defroutes GET POST]]
            [ring.util.http-response :as response]
            [clojure.java.io :as io]
            [booksharing.db.core :as db]
            [bouncer.core :as b]
            [bouncer.validators :as v]))


(defn validate-message [params]
  (first
    (b/validate
      params
      :book v/required
      :message [v/required [v/min-count 10]])))


(defn home-page [{:keys [flash]}]
  (layout/render
    "home.html"
    (merge {:messages (db/get-messages)}
           (select-keys flash [:book :message :errors]))))

(defn save-book! [{:keys [params]}]
  (if-let [errors (validate-message params)]
    (-> (response/found "/")
        (assoc :flash (assoc params :errors errors)))
    (do
      (db/save-book!
        (assoc params :timestamp (java.util.Date.)))
      (response/found "/"))))

(defn about-page []
  (layout/render "about.html"))

(defn login-page []
  (layout/render "login.html"))

(defroutes home-routes
  (GET "/" request (home-page request))
  (POST "/book" request (save-book! request))
  (GET "/login" [] (login-page))
  (GET "/about" [] (about-page)))

