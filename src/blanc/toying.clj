(ns blanc.toying
  (:use compojure.core
        [ring.util.serve :only (serve)]
        ring.util.response)
  (:require [compojure.route :as route]
            [compojure.handler :as handler]
            [fs.core :as fs]))

(defroutes main-routes
  (GET "/:path" [path]
       (if (fs/exists? (str "public/" path))
         (file-response (file-response path {:root "public"}))
         (response "File not found LOL")))
  (GET "/" []
       "sups?"))

(def app
  (handler/site main-routes))

(comment )