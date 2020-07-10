(ns marketplace.shared.spec.ipfs-events
  (:require
    [cljs.spec.alpha :as s]
    [district-designer.shared.spec.ipfs-events :refer [ipfs-hash? address? edn? event-type]]
    [users.shared.spec.ipfs-events]))


(defn rating? [x]
  (and (number? x) (>= x 0) (<= x 5)))

(s/def :offer-group/address address?)
(s/def :offer-group/name string?)
(s/def :offer-group/offer-fields (s/coll-of :field/field))
(s/def :offer-group/response-fields (s/coll-of :field/field))
(s/def :offer-group/global-enabled? :global/enabled?)
(s/def :offer-group/global-logo :global/logo)
(s/def :offer-group/global-description :global/description)

(s/def :offer/address address?)
(s/def :offer-response/index nat-int?)

(s/def :feedback/uuid uuid?)
(s/def :feedback/rating rating?)
(s/def :feedback/text string?)

(defmethod event-type :marketplace/add-offer-group-factory [_]
  :district-designer/add-smart-contract)

(defmethod event-type :marketplace/update-offer-group [_]
  (s/merge
    :district-designer.shared.spec.ipfs-events/event-base
    (s/keys :req [:offer-group/address]
            :opt [:offer-group/name
                  :offer-group/offer-fields
                  :offer-group/response-fields
                  :offer-group/global-enabled?
                  :offer-group/global-logo
                  :offer-group/global-description])))


(defmethod event-type :marketplace/add-district-offer-group [_]
  (s/merge
    :district-designer.shared.spec.ipfs-events/event-base
    (s/keys :req [:district/address
                  :offer-group/address])))


(defmethod event-type :marketplace/remove-district-offer-group [_]
  (s/merge
    :district-designer.shared.spec.ipfs-events/event-base
    (s/keys :req [:district/address
                  :offer-group/address])))

(s/def :offer/field-values (s/coll-of :field-value/field-value))

(defmethod event-type :marketplace/update-offer [_]
  (s/merge
    :district-designer.shared.spec.ipfs-events/event-base
    (s/keys :req [:offer/address
                  :offer/field-values])))


(defmethod event-type :marketplace/add-offer-response-message [_]
  (s/merge
    :district-designer.shared.spec.ipfs-events/event-base
    (s/keys :req [:offer/address
                  :offer-response/index
                  :message/uuid
                  :message/receiver
                  :message/text]
            :opt [:message/files])))


(defmethod event-type :marketplace/add-feedback [_]
  (s/merge
    :district-designer.shared.spec.ipfs-events/event-base
    (s/keys :req [:offer/address
                  :offer-response/index
                  :feedback/uuid
                  :feedback/rating
                  :feedback/text])))


(defmethod event-type :marketplace/update-feedback [_]
  (s/merge
    :district-designer.shared.spec.ipfs-events/event-base
    (s/keys :req [:offer/address
                  :offer-response/index
                  :feedback/uuid]
            :opt [:feedback/rating
                  :feedback/text])))