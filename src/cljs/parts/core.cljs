(ns parts.core
  (:require [clojure.string :as string]
            [parts.items :as items]
            [reagent.core :as reagent]))

(enable-console-print!)

(def app-state
  (reagent/atom {:completed []}))

(declare fetch-maintenance handle-maintenance)

(defn maint-item
  [m]
  [:tr
   [:td (:name m)]
   [:td (:parts m)]])

(defn maintenance-list
  []
  [:div
   [:table.table
    (for [m @app-state]
      (maint-item m))
    ]])

(defn log-app
  []
  [:div
   [:div.navbar.navbar-fixed-top.brand
    [:div.navbar-inner
     [:div.container
      [:div.brand {:id :headline}
       [:a {:href "//youhavethewrong.info"}
        "YouHaveTheWrong.info"]]]]]
   [:div {:id :content :class :container}
    [:h1 {:id :h1} "Maintenance log"]
    [maintenance-list]
    ]])

(defn render
  []
  (reagent/render-component
   [log-app]
   (.getElementById js/document "app")))

(defn handle-maintenance
  [maintenance]
  (reset! app-state maintenance)
  (render))

(defn fetch-maintenance
  [f]
  (f items/inventory))

(defn start
  []
  (fetch-maintenance handle-maintenance))
