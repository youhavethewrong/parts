(ns parts.core
  (:require [clojure.string :as string]
            [parts.items :as items]
            [reagent.core :as reagent]))

;; (enable-console-print!)

(def app-state
  "Holds the state of the reagent application.  This is pretty
   static currently since the item inventory is hardcoded in the
   items namespace."
  (reagent/atom {}))

(defn parts-content
  []
  [:div.row-fluid
   [:div.span12
    (for [{:keys [name parts]} @app-state]
      [:div.row-fluid.well
       [:div.span12 [:strong name]]
       (for [part parts]
         [:div.row-fluid
          [:div.span4.offset4 (key part)]
          (for [option (val part)]
            [:div.span4.offset8 option])]
         )])]])

(defn branding
  []
  [:div.navbar.navbar-fixed-top.brand
   [:div.navbar-inner
    [:div.container
     [:div.brand {:id :headline}
      [:a {:href "https://ecik.youhavethewrong.info/blog"}
       "YouHaveTheWrong.info"]]]]])

(defn parts-app
  []
  [:div
   [branding]
   [:div {:id :content :class :container}
    [:h1 {:id :h1} "Common maintenance parts"]
    [parts-content]
    ]])

(defn render
  []
  (reagent/render-component
   [parts-app]
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
