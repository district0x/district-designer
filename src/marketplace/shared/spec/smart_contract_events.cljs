(ns marketplace.shared.spec.smart-contract-events
  (:require
    [cljs.spec.alpha :as s]
    [district-designer.shared.spec.ipfs-events :refer [ipfs-hash? address? edn? event-type]]
    [marketplace.shared.spec.ipfs-events]))


(def smart-contract-events
  #{:marketplace/offer-group-created
    :marketplace/offer-group-updated
    :marketplace/offer-created
    :marketplace/offer-response-created
    :marketplace/offer-response-accepted
    :marketplace/offer-canceled
    :marketplace/offer-response-canceled
    :marketplace/dispute-raised
    :marketplace/dispute-resolved
    :marketplace/base-contracts-updated
    :marketplace/offer-group-base-contracts-updated})


(s/def ::offer-group address?)
(s/def ::offer-group-base-contract address?)
(s/def ::offer-group-ipfs-abi ipfs-hash?)
(s/def ::offer-base-contract address?)
(s/def ::offer-ipfs-abi ipfs-hash?)
(s/def ::offer-group-version pos-int?)
(s/def ::trade-asset-category #{:trade-asset-category/eth
                                :trade-asset-category/erc-20
                                :trade-asset-category/erc-721
                                :trade-asset-category/erc-1155
                                :trade-asset-category/deliverable})
(s/def ::token-address address?)

(s/def ::trade-asset (s/keys :req-un [::trade-asset-category]
                             :opt-un [::token-address]))
(s/def ::trade-assets (s/coll-of ::trade-asset))

(s/def ::assets-to-offer ::trade-assets)
(s/def ::assets-to-request ::trade-assets)

(s/def ::offer-type #{:offer-type/dynamic-price
                      :offer-type/fixed-price
                      :offer-type/higest-bid-wins
                      :offer-type/multiple-fixed-prices
                      :offer-type/offerer-picks-winner})

(s/def ::create-offer-fee nat-int?)
(s/def ::offer-response-fee nat-int?)
(s/def ::fees (s/keys :req-un [::create-offer-fee
                               ::offer-response-fee]))
(s/def ::create-offer-user-roles (s/coll-of :user-role/uuid))
(s/def ::offer-response-user-roles (s/coll-of :user-role/uuid))
(s/def ::resolve-dispute-user-roles (s/coll-of :user-role/uuid))
(s/def ::permission-user-roles (s/keys :req-un [::create-offer-user-roles
                                                ::offer-response-user-roles
                                                ::resolve-dispute-user-roles]))
(s/def :offer-group/name string?)
(s/def :offer-group/offer-field-configs (s/coll-of :field-config/field-config))
(s/def :offer-group/response-field-configs (s/coll-of :field-config/field-config))


(s/def :offer-group-created/ipfs-data
  (s/keys :req [:offer-group/name
                :offer-group/offer-field-configs]
          :opt [:offer-group/response-field-configs
                :offer-group/global-enabled?
                :offer-group/global-logo
                :offer-group/global-description]))


(defmethod event-type :marketplace/offer-group-created [_]
  (s/merge
    :district-designer.shared.spec.ipfs-events/event-base
    (s/keys :req-un [:district-designer.shared.spec/district
                     ::offer-group
                     ::offer-group-base-contract
                     ::offer-group-ipfs-abi
                     ::offer-group-version
                     ::assets-to-offer
                     ::assets-to-request
                     ::offer-type
                     ::fees
                     ::permission-user-roles
                     :offer-group-created/ipfs-data])))


(s/def :offer-group-updated/ipfs-data
  (s/keys :opt [:offer-group/name
                :offer-group/offer-field-configs
                :offer-group/response-field-configs
                :offer-group/global-enabled?
                :offer-group/global-logo
                :offer-group/global-description]))


(s/def ::added-assets-to-offer ::assets-to-offer)
(s/def ::added-assets-to-request ::assets-to-request)

(defmethod event-type :marketplace/offer-group-updated [_]
  (s/merge
    :district-designer.shared.spec.ipfs-events/event-base
    (s/keys :req-un [::offer-group
                     ::added-assets-to-offer
                     ::added-assets-to-request
                     ::fees
                     ::permission-user-roles
                     :offer-group-updated/ipfs-data])))

(s/def ::offer address?)
(s/def ::offerer address?)
(s/def ::offer-version :district-designer.shared.spec.ipfs-events/version)

(s/def ::token-amount nat-int?)
(s/def ::token-id nat-int?)
(s/def ::trade-value (s/keys :req-un [::token-address
                                      ::trade-asset-category]
                             :opt-un [::token-amount]))
(s/def ::offered-value ::trade-value)
(s/def ::requested-values (s/coll-of ::trade-value))
(s/def :trade-auction/start-amount nat-int?)
(s/def :trade-auction/end-amount nat-int?)
(s/def :trade-auction/min-amount pos-int?)
(s/def :trade-auction/max-amount pos-int?)
(s/def :trade-auction/min-bid-step pos-int?)
(s/def :trade-auction/duration pos-int?)

(s/def ::trade-auction (s/keys :req-un [::trade-asset]
                               :opt-un [:trade-auction/start-amount
                                        :trade-auction/end-amount
                                        :trade-auction/min-amount
                                        :trade-auction/max-amount
                                        :trade-auction/min-bid-step
                                        :trade-auction/duration]))
(s/def ::requested-auction ::trade-auction)

(s/def ::allowed-respondents (s/coll-of address?))

(s/def :offer-created/ipfs-data
  (s/keys))


(defmethod event-type :marketplace/offer-created [_]
  (s/merge
    :district-designer.shared.spec.ipfs-events/event-base
    (s/keys :req-un [::offer
                     ::offer-base-contract
                     ::offer-ipfs-abi
                     ::offer-version
                     ::offerer
                     ::offered-value
                     ::requested-values
                     ::requested-auction
                     ::allowed-respondents
                     :offer-created/ipfs-data])))


(s/def ::respondent address?)
(s/def ::offer-response uuid?)
(s/def ::offerer-traded-value ::trade-value)
(s/def ::respondent-traded-value ::trade-value)


(s/def :offer-response-created/ipfs-data
  (s/keys))


(defmethod event-type :marketplace/offer-response-created [_]
  (s/merge
    :district-designer.shared.spec.ipfs-events/event-base
    (s/keys :req-un [::offer
                     ::respondent
                     ::offer-response
                     ::offerer-traded-value
                     ::respondent-traded-value
                     :offer-response-created/ipfs-data])))


(defmethod event-type :marketplace/offer-response-accepted [_]
  ; TODO: Add IPFS data
  (s/merge
    :district-designer.shared.spec.ipfs-events/event-base
    (s/keys :req-un [::offer-response
                     ::offerer-traded-value
                     ::respondent-traded-value])))


(defmethod event-type :marketplace/offer-canceled [_]
  (s/merge
    :district-designer.shared.spec.ipfs-events/event-base
    (s/keys :req-un [::offer])))


(defmethod event-type :marketplace/offer-response-canceled [_]
  (s/merge
    :district-designer.shared.spec.ipfs-events/event-base
    (s/keys :req-un [::offer-response])))


; TODO: OfferDeliverableReceived
; TODO: OfferAvailableValueUpdated
; TODO: OfferRequestUpdated

(s/def ::raised-by address?)

(defmethod event-type :marketplace/dispute-raised [_]
  ; TODO: Add ipfs-data
  (s/merge
    :district-designer.shared.spec.ipfs-events/event-base
    (s/keys :req-un [::offer-response
                     ::raised-by])))

(s/def ::value-for-offerer ::trade-value)
(s/def ::value-for-respondent ::trade-value)
(s/def ::resolved-by address?)

(defmethod event-type :marketplace/dispute-resolved [_]
  ; TODO: Add ipfs-data
  (s/merge
    :district-designer.shared.spec.ipfs-events/event-base
    (s/keys :req-un [::offer-response
                     ::value-for-offerer
                     ::value-for-respondent
                     ::resolved-by])))


(defmethod event-type :marketplace/base-contracts-updated [_]
  (s/merge
    :district-designer.shared.spec.ipfs-events/event-base
    (s/keys :req-un [::offer-group-base-contract
                     ::offer-group-ipfs-abi
                     ::offer-base-contract
                     ::offer-ipfs-abi])))


(defmethod event-type :marketplace/offer-group-base-contracts-updated [_]
  (s/merge
    :district-designer.shared.spec.ipfs-events/event-base
    (s/keys :req-un [::offer-group
                     ::offer-base-contract
                     ::offer-ipfs-abi])))


