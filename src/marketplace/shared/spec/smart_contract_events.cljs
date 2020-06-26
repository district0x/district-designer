(ns marketplace.shared.spec.smart-contract-events
  (:require
    [cljs.spec.alpha :as s]
    [district-designer.shared.spec.ipfs-events :refer [ipfs-hash? address? edn? event-type]]
    [district-designer.shared.spec.smart-contract-events]
    [marketplace.shared.spec.ipfs-events]
    [users.shared.spec.ipfs-events]))


(s/def ::offer-group address?)
(s/def ::offer-group-base-contract address?)
(s/def ::offer-group-target :proxy-factory/proxy-target)
(s/def ::offer-target :proxy-factory/proxy-target)
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

(s/def ::offerable-assets ::trade-assets)
(s/def ::requestable-assets ::trade-assets)

(s/def ::offer-type #{:offer-type/dynamic-price
                      :offer-type/fixed-prices
                      :offer-type/higest-bid-auction
                      :offer-type/multi-token-auction
                      :offer-type/deliverable-auction})


(s/def ::allowed-offer-types (s/coll-of ::offer-type))

(s/def ::create-offer-fee nat-int?)
(s/def ::offer-response-fee nat-int?)

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
    (s/keys :req-un [:district-designer.shared.spec.smart-contract-events/district
                     ::offer-group
                     ::offer-group-target
                     ::offer-group-version
                     ::offerable-assets
                     ::requestable-assets
                     ::allowed-offer-types
                     ::permission-user-roles
                     :offer-group-created/ipfs-data])))


(s/def :offer-group-updated/ipfs-data
  (s/keys :opt [:offer-group/name
                :offer-group/offer-field-configs
                :offer-group/response-field-configs
                :offer-group/global-enabled?
                :offer-group/global-logo
                :offer-group/global-description]))


(defmethod event-type :marketplace/offer-group-updated [_]
  (s/merge
    :district-designer.shared.spec.ipfs-events/event-base
    (s/keys :req-un [::offer-group
                     ::offerable-assets
                     ::requestable-assets
                     ::allowed-offer-types
                     ::permission-user-roles
                     :offer-group-updated/ipfs-data])))

(s/def ::offer address?)
(s/def ::offerer address?)
(s/def ::offer-version :district-designer.shared.spec.ipfs-events/version)

(s/def ::token-id nat-int?)
(s/def ::token-ids (s/coll-of ::token-id))

(s/def ::value nat-int?)
(s/def ::values (s/coll-of ::value))
(s/def ::eth-value (s/keys :req-un [::value]))
(s/def ::erc20-value (s/keys :req-un [::value]))
(s/def ::erc721-value (s/keys :req-un [::token-id]))
(s/def ::erc1155-value (s/keys :req-un [::token-ids ::values]))
(s/def ::trade-value (s/keys :req-un [::trade-asset]
                             :opt-un [::eth-value
                                      ::erc20-value
                                      ::erc721-value
                                      ::erc1155-value]))
(s/def ::offered-value ::trade-value)
(s/def ::token-type #{:token-type/eth
                      :token-type/erc-20
                      :token-type/erc-721
                      :token-type/erc-1155})

(s/def ::start-price ::value)
(s/def ::end-price ::value)
(s/def ::duration nat-int?)

(s/def ::dynamic-price-offer-request (s/keys :req-un [::token-type
                                                      ::token-address
                                                      ::token-id
                                                      ::start-price
                                                      ::end-price
                                                      ::duration]))

(s/def ::prices (s/coll-of ::trade-value))

(s/def ::fixed-price-offer-request (s/keys ::req-un [::prices]))

(s/def ::min-price ::value)
(s/def ::min-bid-step ::value)
(s/def ::extension-trigger-duration ::duration)
(s/def ::extension-duration ::duration)

(s/def ::highest-bid-auction-offer-request (s/keys :req-un [::token-type
                                                            ::token-address
                                                            ::token-id
                                                            ::min-price
                                                            ::min-bid-step
                                                            ::duration
                                                            ::extension-trigger-duration
                                                            ::extension-duration]))

(s/def ::token (s/keys :req-un [::token-type
                                ::token-address]))

(s/def ::accepted-tokens (s/coll-of :token))


(s/def ::multi-token-auction-offer-request (s/keys :req-un [::accepted-tokens
                                                            ::duration
                                                            ::extension-trigger-duration
                                                            ::extension-duration]))


(s/def ::offer-request (s/keys :opt-un [::dynamic-price-offer-request
                                        ::fixed-price-offer-request
                                        ::highest-bid-auction-offer-request
                                        ::multi-token-auction-offer-request]))

(s/def ::requested-values (s/coll-of ::trade-value))

(s/def ::allowed-respondents (s/coll-of address?))
(s/def ::available-supply ::trade-value)

(s/def :offer-created/ipfs-data
  (s/keys))


(defmethod event-type :marketplace/offer-created [_]
  (s/merge
    :district-designer.shared.spec.ipfs-events/event-base
    (s/keys :req-un [::offer
                     ::offer-target
                     ::offer-version
                     ::offer-type
                     ::offerer
                     ::offered-value
                     ::offer-request
                     ::available-supply
                     ::allowed-respondents
                     :offer-created/ipfs-data])))


(s/def ::respondent address?)
(s/def ::offer-response-index nat-int?)
(s/def ::response-value ::trade-value)
(s/def ::offerer-received-value ::trade-value)
(s/def ::respondent-received-value ::trade-value)


(s/def :offer-response-created/ipfs-data
  (s/keys :opt-un [:users.shared.spec.ipfs-events/messages]))


(defmethod event-type :marketplace/offer-response-created [_]
  (s/merge
    :district-designer.shared.spec.ipfs-events/event-base
    (s/keys :req-un [::offer
                     ::respondent
                     ::offer-response-index
                     ::response-value
                     ::offerer-received-value
                     ::respondent-received-value
                     ::available-supply
                     :offer-response-created/ipfs-data])))


(s/def :offer-request-updated/ipfs-data
  (s/keys))


(defmethod event-type :marketplace/offer-request-updated [_]
  (s/merge
    :district-designer.shared.spec.ipfs-events/event-base
    (s/keys :req-un [::offer
                     ::offer-request
                     :offer-request-updated/ipfs-data])))


(s/def :offer-response-accepted/ipfs-data
  (s/keys :opt-un [:users.shared.spec.ipfs-events/messages]))


(defmethod event-type :marketplace/offer-response-accepted [_]
  (s/merge
    :district-designer.shared.spec.ipfs-events/event-base
    (s/keys :req-un [::offer
                     ::offer-response-index
                     ::offerer-received-value
                     ::respondent-received-value
                     ::available-supply
                     :offer-response-accepted/ipfs-data])))


(defmethod event-type :marketplace/supply-withdrawn [_]
  (s/merge
    :district-designer.shared.spec.ipfs-events/event-base
    (s/keys :req-un [::offer])))


(s/def :deliverable-received/ipfs-data
  (s/keys :opt-un [:users.shared.spec.ipfs-events/messages]))


(defmethod event-type :marketplace/deliverable-received [_]
  (s/merge
    :district-designer.shared.spec.ipfs-events/event-base
    (s/keys :req-un [::offer
                     ::offer-response-index
                     ::offerer-received-value
                     ::respondent-received-value
                     ::available-supply
                     :deliverable-received/ipfs-data])))


(defmethod event-type :marketplace/offer-response-canceled [_]
  (s/merge
    :district-designer.shared.spec.ipfs-events/event-base
    (s/keys :req-un [::offer
                     ::offer-response-index])))


(s/def ::raised-by address?)
(s/def :dispute-raised/ipfs-data
  (s/keys :opt-un [:users.shared.spec.ipfs-events/messages]))

(defmethod event-type :marketplace/dispute-raised [_]
  (s/merge
    :district-designer.shared.spec.ipfs-events/event-base
    (s/keys :req-un [::offer
                     ::offer-response-index
                     ::raised-by
                     :dispute-raised/ipfs-data])))


(s/def ::value-for-offerer ::trade-value)
(s/def ::value-for-respondent ::trade-value)
(s/def ::resolved-by address?)
(s/def :dispute-resolved/ipfs-data
  (s/keys :opt-un [:users.shared.spec.ipfs-events/messages]))

(defmethod event-type :marketplace/dispute-resolved [_]
  (s/merge
    :district-designer.shared.spec.ipfs-events/event-base
    (s/keys :req-un [::offer
                     ::offer-response-index
                     ::offerer-received-value
                     ::respondent-received-value
                     ::available-supply
                     ::resolved-by
                     :dispute-resolved/ipfs-data])))


(s/def ::sponsor address?)
(s/def ::sponsorship ::trade-value)

(defmethod event-type :marketplace/sponsorship-added [_]
  (s/merge
    :district-designer.shared.spec.ipfs-events/event-base
    (s/keys :req-un [::offer
                     ::sponsor
                     ::sponsorship])))

(s/def ::withdrawal ::trade-value)

(defmethod event-type :marketplace/sponsorship-withdrawn [_]
  (s/merge
    :district-designer.shared.spec.ipfs-events/event-base
    (s/keys :req-un [::offer
                     ::sponsor
                     ::withdrawal])))

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


(defmethod event-type :marketplace/offer-group-base-contracts-updated [_]
  (s/merge
    :district-designer.shared.spec.ipfs-events/event-base
    (s/keys :req-un [::offer-group
                     ::fixed-prices-offer-target
                     ::dynamic-price-offer-target
                     ::highest-bid-auction-offer-target
                     ::multi-token-auction-offer-target
                     ::deliverable-auction-offer-target])))


