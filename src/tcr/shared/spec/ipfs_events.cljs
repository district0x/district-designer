(ns tcr.shared.spec.ipfs-events
  (:require
    [cljs.spec.alpha :as s]
    [district-designer.shared.spec.ipfs-events :refer [ipfs-hash? address? edn?]]))

(def ipfs-events
  #{:tcr/add-tcr-factory
    :tcr/update-tcr
    :tcr/add-district-tcr
    :tcr/remove-district-tcr})

(s/def :tcr/uuid uuid?)
(s/def :tcr/name string?)
(s/def :tcr/reg-entry-field-configs (s/coll-of :field-config/field-config))
(s/def :tcr/global-enabled? boolean?)
(s/def :tcr/global-logo :file/file)
(s/def :tcr/global-description string?)

(s/def :tcr/add-tcr-factory :district-designer/add-smart-contract)

(s/def :tcr/update-tcr
  (s/merge
    :district-designer.shared.spec.ipfs-events/event-base
    (s/keys :req [:tcr/uuid]
            :opt [:tcr/name
                  :tcr/reg-entry-field-configs
                  :tcr/global-enabled?
                  :tcr/global-logo
                  :tcr/global-description])))


(s/def :tcr/add-district-tcr
  (s/merge
    :district-designer.shared.spec.ipfs-events/event-base
    (s/keys :req [:district/uuid
                  :tcr/uuid])))

(s/def :tcr/remove-district-tcr :tcr/add-district-tcr)