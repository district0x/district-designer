(ns tcr.shared.spec.ipfs-events
  (:require
    [cljs.spec.alpha :as s]
    [district-designer.shared.spec.ipfs-events :refer [ipfs-hash? address? edn? event-type]]))


(s/def :tcr/address address?)
(s/def :tcr/name string?)
(s/def :tcr/reg-entry-fields (s/coll-of :field/field))
(s/def :tcr/global-enabled? :global/enabled?)
(s/def :tcr/global-logo :file/file)
(s/def :tcr/global-description :global/description)

(defmethod event-type :tcr/add-tcr-factory [_]
  :district-designer/add-smart-contract)

(defmethod event-type :tcr/update-tcr [_]
  (s/merge
    :district-designer.shared.spec.ipfs-events/event-base
    (s/keys :req [:tcr/address]
            :opt [:tcr/name
                  :tcr/reg-entry-fields
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