(set-env!
 :dependencies '[
;;                 [com.cemerick/piggieback "LATEST" ]
                 [com.cemerick/piggieback "0.2.2" :scope "test"]
                 [weasel "0.7.0" :scope "test"]
                 [org.clojure/tools.nrepl "0.2.10"]
                 [adzerk/boot-cljs-repl "0.3.3"]
                 [adzerk/boot-cljs          "1.7.228-2"] ;; was :scope "test"
                  [adzerk/boot-reload        "0.4.13"] ;; was :scope "test"
                  [hoplon/hoplon             "6.0.0-alpha17"]
                  [org.clojure/clojure       "1.8.0"] ;; no :scope "test" on this one
                  [org.clojure/clojurescript "1.9.293"] ;; was 1.9.494
                  [tailrecursion/boot-jetty  "0.1.3"]]
  :source-paths #{"src"}
  :asset-paths  #{"assets"})

(require
 '[adzerk.boot-cljs-repl :refer [cljs-repl start-repl]]

  '[adzerk.boot-cljs         :refer [cljs]]
  '[adzerk.boot-reload       :refer [reload]]
  '[hoplon.boot-hoplon       :refer [hoplon prerender]]
  '[tailrecursion.boot-jetty :refer [serve]])

(deftask dev
  "Build address-book for local development."
  []
  (comp
    (watch)
    (speak)
    (hoplon)
    (reload)
    (cljs-repl)
    (cljs)
    (serve :port 8000)))

(deftask prod
  "Build address-book for production deployment."
  []
  (comp
    (hoplon)
    (cljs :optimizations :advanced)
    (target :dir #{"target"})))
