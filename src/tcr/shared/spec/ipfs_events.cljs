(ns tcr.shared.spec.ipfs-events
  (:require
    [cljs.spec.alpha :as s]
    [district-designer.shared.spec.ipfs-events :refer [ipfs-hash? address? edn? event-type]]))


(s/def :tcr/address address?)
(s/def :tcr/name string?)
(s/def :tcr/reg-entry-field-configs (s/coll-of :field-config/field-config))
(s/def :tcr/global-enabled? boolean?)
(s/def :tcr/global-logo :file/file)
(s/def :tcr/global-description string?)

(defmethod event-type :tcr/add-tcr-factory [_]
  :district-designer/add-smart-contract)

(defmethod event-type :tcr/update-tcr [_]
  (s/merge
    :district-designer.shared.spec.ipfs-events/event-base
    (s/keys :req [:tcr/address]
            :opt [:tcr/name
                  :tcr/reg-entry-field-configs
                  :tcr/global-enabled?
                  :tcr/global-logo
                  :tcr/global-description])))


(defmethod event-type :tcr/add-district-tcr [_]
  (s/merge
    :district-designer.shared.spec.ipfs-events/event-base
    (s/keys :req [:district/address
                  :tcr/address])))

(defmethod event-type :tcr/remove-district-tcr [_]
  :tcr/add-district-tcr)