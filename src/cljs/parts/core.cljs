(ns parts.core
  (:require [clojure.string :as string]
            [parts.items :as items]
            [reagent.core :as reagent]))

(enable-console-print!)

(def app-state
  (reagent/atom {}))

(defn good-parts-list
  []
  [:div.row-fluid
   [:div.span12
    (for [{:keys [name parts]} @app-state]
      [:div.row-fluid.well
       [:div.span12 [:strong name]]
       (for [part parts]
         [:div.row-fluid
          [:div.span4.offset4 (key part)]
          [:div.span4 (first (val part))]]
         )])]])

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
    [:h1 {:id :h1} "Common maintenance parts for durable goods"]
    [good-parts-list]
    ]])

(defn render
  []
  (reagent/render-component
   [log-app]
   (.getElementById js/document "app")))

(defn handle-items
  [items]
  (reset! app-state items)
  (render))

(defn fetch-items
  [f]
  (f items/inventory))

(defn start
  []
  (fetch-items handle-items))
