(ns marketplace.shared.spec.smart-contract-events
  (:require
    [cljs.spec.alpha :as s]
    [district-designer.shared.spec.ipfs-events :refer [ipfs-hash? address? edn? event-type]]
    [district-designer.shared.spec.smart-contract-events]
    [marketplace.shared.spec.ipfs-events]
    [users.shared.spec.ipfs-events]))


(s/def ::offer-group address?)
(s/def ::offer-group-target :proxy-factory/proxy-target)
(s/def ::offer-group-version pos-int?)
(s/def ::offer-target :proxy-factory/proxy-target)
(s/def ::token-type #{:eth
                      :erc20
                      :erc721
                      :erc1155
                      :deliverable})
(s/def ::token-address address?)
(s/def ::token-contract (s/keys :req-un [::token-type
                                         ::token-address]))

(s/def ::offerable-token-contracts (s/coll-of ::token-contract))
(s/def ::requestable-token-contracts (s/coll-of ::token-contract))

(s/def ::offer-type #{:dynamic-price
                      :fixed-prices
                      :higest-bid-auction
                      :multi-token-auction
                      :deliverable-auction})


(s/def ::allowed-offer-types (s/coll-of ::offer-type))

(s/def ::create-offer-user-roles (s/coll-of :user-role/uuid))
(s/def ::offer-response-user-roles (s/coll-of :user-role/uuid))
(s/def ::resolve-dispute-user-roles (s/coll-of :user-role/uuid))
(s/def ::permission-user-roles (s/keys :req-un [::create-offer-user-roles
                                                ::offer-response-user-roles
                                                ::resolve-dispute-user-roles]))

(s/def :offer-group-created/ipfs-data
  (s/keys :req [:offer-group/name
                :offer-group/offer-fields]
          :opt [:offer-group/response-fields
                :offer-group/global-enabled?
                :offer-group/global-logo
                :offer-group/global-description]))


(defmethod event-type :marketplace/offer-group-created [_]
  (s/merge
    :district-designer.shared.spec.ipfs-events/event-base
    (s/keys :req-un [:district-designer.shared.spec.smart-contract-events/district
                     ::offer-group
                     ::offer-group-target
                     ::offer-group-version
                     ::offerable-token-contracts
                     ::requestable-token-contracts
                     ::allowed-offer-types
                     ::permission-user-roles
                     :offer-group-created/ipfs-data])))


(s/def :offer-group-updated/ipfs-data
  (s/keys :opt [:offer-group/name
                :offer-group/offer-fields
                :offer-group/response-fields
                :offer-group/global-enabled?
                :offer-group/global-logo
                :offer-group/global-description]))


(defmethod event-type :marketplace/offer-group-updated [_]
  (s/merge
    :district-designer.shared.spec.ipfs-events/event-base
    (s/keys :req-un [::offer-group
                     ::offerable-token-contracts
                     ::requestable-token-contracts
                     ::allowed-offer-types
                     ::permission-user-roles
                     :offer-group-updated/ipfs-data])))

(s/def ::offer address?)
(s/def ::offerer address?)
(s/def ::offer-version :district-designer.shared.spec.ipfs-events/version)

(s/def ::token-id nat-int?)
(s/def ::token (s/keys :req-un [::token-contract
                                ::token-id]))
(s/def ::value nat-int?)
(s/def ::token-value (s/keys :req-un [::token
                                      ::value]))
(s/def ::token-values (s/coll-of ::token-value))

(s/def ::offered-values ::token-values)

(s/def ::start-price ::value)
(s/def ::end-price ::value)
(s/def ::duration nat-int?)

(s/def ::dynamic-price-offer-request (s/keys :req-un [::token
                                                      ::start-price
                                                      ::end-price
                                                      ::duration]))

(s/def ::prices (s/coll-of (s/coll-of ::token-value)))

(s/def ::fixed-price-offer-request (s/keys ::req-un [::prices]))

(s/def ::min-price ::value)
(s/def ::min-bid-step ::value)
(s/def ::extension-trigger-duration ::duration)
(s/def ::extension-duration ::duration)

(s/def ::highest-bid-auction-offer-request (s/keys :req-un [::token
                                                            ::min-price
                                                            ::min-bid-step
                                                            ::duration
                                                            ::extension-trigger-duration
                                                            ::extension-duration]))

(s/def ::accepted-token-contracts (s/coll-of ::token-contract))


(s/def ::multi-token-auction-offer-request (s/keys :req-un [::accepted-token-contracts
                                                            ::duration
                                                            ::extension-trigger-duration
                                                            ::extension-duration]))


(s/def ::offer-request (s/keys :opt-un [::dynamic-price-offer-request
                                        ::fixed-price-offer-request
                                        ::highest-bid-auction-offer-request
                                        ::multi-token-auction-offer-request]))

(s/def ::allowed-respondents (s/coll-of address?))
(s/def ::available-values ::token-values)


(s/def :offer-created/ipfs-data
  (s/keys :req [:offer/field-values]))


(defmethod event-type :marketplace/offer-created [_]
  (s/merge
    :district-designer.shared.spec.ipfs-events/event-base
    (s/keys :req-un [::offer
                     ::offer-target
                     ::offer-version
                     ::offer-type
                     ::offerer
                     ::offered-values
                     ::offer-request
                     ::available-values
                     ::allowed-respondents
                     :offer-created/ipfs-data])))


(s/def ::respondent address?)
(s/def ::offer-response-index nat-int?)
(s/def ::response-values ::token-values)
(s/def ::offerer-received-values ::token-values)
(s/def ::respondent-received-values ::token-values)
(s/def :offer-response/field-values :offer/field-values)
(s/def :offer-response/messages :users.shared.spec.ipfs-events/messages)

(s/def :offer-response-created/ipfs-data
  (s/keys :opt [:offer-response/field-values
                :offer-response/messages]))


(defmethod event-type :marketplace/offer-response-created [_]
  (s/merge
    :district-designer.shared.spec.ipfs-events/event-base
    (s/keys :req-un [::offer
                     ::respondent
                     ::offer-response-index
                     ::response-values
                     ::offerer-received-values
                     ::respondent-received-values
                     ::available-values
                     :offer-response-created/ipfs-data])))


(s/def :offer-request-updated/ipfs-data
  (s/keys :opt [:offer/field-values]))


(defmethod event-type :marketplace/offer-request-updated [_]
  (s/merge
    :district-designer.shared.spec.ipfs-events/event-base
    (s/keys :req-un [::offer
                     ::offer-request
                     :offer-request-updated/ipfs-data])))


(s/def :offer-response-accepted/ipfs-data
  (s/keys :opt [:offer-response/messages]))


(defmethod event-type :marketplace/offer-response-accepted [_]
  (s/merge
    :district-designer.shared.spec.ipfs-events/event-base
    (s/keys :req-un [::offer
                     ::offer-response-index
                     ::offerer-received-values
                     ::respondent-received-values
                     ::available-values
                     :offer-response-accepted/ipfs-data])))

(s/def :deliverable-received/ipfs-data
  (s/keys :opt [:offer-response/messages]))

(defmethod event-type :marketplace/deliverable-received [_]
  (s/merge
    :district-designer.shared.spec.ipfs-events/event-base
    (s/keys :req-un [::offer
                     ::offer-response-index
                     ::offerer-received-values
                     ::respondent-received-values
                     ::available-values
                     :deliverable-received/ipfs-data])))


(defmethod event-type :marketplace/offer-response-canceled [_]
  (s/merge
    :district-designer.shared.spec.ipfs-events/event-base
    (s/keys :req-un [::offer
                     ::offer-response-index])))


(s/def ::raised-by address?)
(s/def :dispute-raised/ipfs-data
  (s/keys :opt [:offer-response/messages]))

(defmethod event-type :marketplace/dispute-raised [_]
  (s/merge
    :district-designer.shared.spec.ipfs-events/event-base
    (s/keys :req-un [::offer
                     ::offer-response-index
                     ::raised-by
                     :dispute-raised/ipfs-data])))


(s/def ::resolved-by address?)
(s/def :dispute-resolved/ipfs-data
  (s/keys :opt [:offer-response/messages]))

(defmethod event-type :marketplace/dispute-resolved [_]
  (s/merge
    :district-designer.shared.spec.ipfs-events/event-base
    (s/keys :req-un [::offer
                     ::offer-response-index
                     ::offerer-received-values
                     ::respondent-received-values
                     ::available-values
                     ::resolved-by
                     :dispute-resolved/ipfs-data])))

(s/def ::withdrawn-values ::available-values)

(defmethod event-type :marketplace/available-values-withdrawn [_]
  (s/merge
    :district-designer.shared.spec.ipfs-events/event-base
    (s/keys :req-un [::offer
                     ::withdrawn-values])))

(s/def ::sponsor address?)
(s/def ::sponsored-values ::token-values)

(defmethod event-type :marketplace/sponsorship-added [_]
  (s/merge
    :district-designer.shared.spec.ipfs-events/event-base
    (s/keys :req-un [::offer
                     ::sponsor
                     ::sponsored-values
                     ::available-values])))

(defmethod event-type :marketplace/sponsorship-withdrawn [_]
  (s/merge
    :district-designer.shared.spec.ipfs-events/event-base
    (s/keys :req-un [::offer
                     ::sponsor
                     ::withdrawn-values
                     ::available-values])))

(s/def ::offer-group-target :proxy-factory/proxy-target)
(s/def ::fixed-prices-offer-target :proxy-factory/proxy-target)
(s/def ::dynamic-price-offer-target :proxy-factory/proxy-target)
(s/def ::highest-bid-auction-offer-target :proxy-factory/proxy-target)
(s/def ::multi-token-auction-offer-target :proxy-factory/proxy-target)
(s/def ::deliverable-auction-offer-target :proxy-factory/proxy-target)


(defmethod event-type :marketplace/proxy-targets-updated [_]
  (s/merge
    :district-designer.shared.spec.ipfs-events/event-base
    (s/keys :req-un [::offer-group-target
                     ::fixed-prices-offer-target
                     ::dynamic-price-offer-target
                     ::highest-bid-auction-offer-target
                     ::multi-token-auction-offer-target
                     ::deliverable-auction-offer-target])))


(defmethod event-type :marketplace/offer-group-proxy-targets-updated [_]
  (s/merge
    :district-designer.shared.spec.ipfs-events/event-base
    (s/keys :req-un [::offer-group
                     ::fixed-prices-offer-target
                     ::dynamic-price-offer-target
                     ::highest-bid-auction-offer-target
                     ::multi-token-auction-offer-target
                     ::deliverable-auction-offer-target])))


