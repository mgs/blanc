(ns blanc.toying
  (:use compojure.core
        [ring.util.serve :only (serve)]
        ring.util.response
        markdown)
  (:require [compojure.route :as route]
            [compojure.handler :as handler]
            [fs.core :as fs]))

(defroutes main-routes
  (GET "/:year/:month/:day/:title" {uri :uri}
       (if (fs/exists? (str "public/" uri))
         (response (md-to-html-string (slurp (str "public/" uri))))
         (response (str "File not found LOL: " uri))))
  (GET "/" []
       "sups?"))

(def app
  (handler/site main-routes))