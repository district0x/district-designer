(ns marketplace.shared.spec.ipfs-events
  (:require
    [cljs.spec.alpha :as s]
    [district-designer.shared.spec.ipfs-events :refer [ipfs-hash? address? edn?]]
    [users.shared.spec.ipfs-events]))


(def ipfs-events
  #{:marketplace/update-offer-group
    :marketplace/add-district-offer-group
    :marketplace/remove-district-offer-group
    :marketplace/update-offer
    :marketplace/add-message
    :marketplace/add-feedback
    :marketplace/update-feedback})


(defn rating? [x]
  (and (number? x) (>= x 0) (<= x 5)))

(s/def :offer-group/address address?)
(s/def :offer-group/name string?)
(s/def :offer-group/offer-field-configs (s/coll-of :field-config/field-config))
(s/def :offer-group/response-field-configs (s/coll-of :field-config/field-config))
(s/def :offer-group/global-enabled? :global/enabled?)
(s/def :offer-group/global-logo :global/logo)
(s/def :offer-group/global-description :global/description)

(s/def :offer/address address?)
(s/def :offer-response/uuid uuid?)

(s/def :feedback/uuid uuid?)
(s/def :feedback/rating rating?)
(s/def :feedback/text string?)

(s/def :marketplace/add-offer-group-factory :district-designer/add-smart-contract)

(s/def :marketplace/update-offer-group
  (s/merge
    :district-designer.shared.spec.ipfs-events/event-base
    (s/keys :req [:offer-group/address]
            :opt [:offer-group/name
                  :offer-group/offer-field-configs
                  :offer-group/response-field-configs
                  :offer-group/global-enabled?
                  :offer-group/global-logo
                  :offer-group/global-description])))


(s/def :marketplace/add-district-offer-group
  (s/merge
    :district-designer.shared.spec.ipfs-events/event-base
    (s/keys :req [:district/uuid
                  :offer-group/address])))


(s/def :marketplace/remove-district-offer-group :marketplace/add-district-offer-group)


(s/def :marketplace/update-offer
  (s/merge
    :district-designer.shared.spec.ipfs-events/event-base
    (s/keys :req [:offer/address])))


(s/def :marketplace/add-message
  (s/merge
    :district-designer.shared.spec.ipfs-events/event-base
    (s/keys :req [:offer-response/uuid
                  :message/uuid
                  :message/receiver
                  :message/text]
            :opt [:message/files])))


(s/def :marketplace/add-feedback
  (s/merge
    :district-designer.shared.spec.ipfs-events/event-base
    (s/keys :req [:offer-response/uuid
                  :feedback/uuid
                  :feedback/rating
                  :feedback/text])))


(s/def :marketplace/update-feedback :marketplace/add-feedback)