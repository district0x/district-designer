(ns marketplace.server.db-schema)

(def db-idents
  [#:db{:ident :district/offers-groups :valueType :db.type/ref :cardinality :db.cardinality/many}

   #:db{:ident :asset-category/deliverable}
   #:db{:ident :asset-category/erc-1155}
   #:db{:ident :asset-category/erc-20}
   #:db{:ident :asset-category/erc-721}
   #:db{:ident :asset-category/eth}

   #:db{:ident :offer-type/deliverable-auction}
   #:db{:ident :offer-type/dynamic-price}
   #:db{:ident :offer-type/fixed-price}
   #:db{:ident :offer-type/higest-bid-auction}
   #:db{:ident :offer-type/multi-token-auction}

   #:db{:ident :trade-asset/asset-category :valueType :db.type/ref :cardinality :db.cardinality/one}
   #:db{:ident :trade-asset/token-contract :valueType :db.type/ref :cardinality :db.cardinality/one}

   #:db{:ident :offer-group/smart-contract :valueType :db.type/ref :cardinality :db.cardinality/one}
   #:db{:ident :offer-group/allowed-offer-types :valueType :db.type/ref :cardinality :db.cardinality/many}
   #:db{:ident :offer-group/create-offer-user-roles :valueType :db.type/ref :cardinality :db.cardinality/many}
   #:db{:ident :offer-group/created-by :valueType :db.type/ref :cardinality :db.cardinality/one}
   #:db{:ident :offer-group/global-description :valueType :db.type/string :cardinality :db.cardinality/one}
   #:db{:ident :offer-group/global-enabled? :valueType :db.type/boolean :cardinality :db.cardinality/one}
   #:db{:ident :offer-group/global-imports-count :valueType :db.type/long :cardinality :db.cardinality/one}
   #:db{:ident :offer-group/global-logo :valueType :db.type/ref :cardinality :db.cardinality/one}
   #:db{:ident :offer-group/name :valueType :db.type/string :cardinality :db.cardinality/one}
   #:db{:ident :offer-group/offer-field-configs :valueType :db.type/ref :cardinality :db.cardinality/many}
   #:db{:ident :offer-group/offer-response-user-roles :valueType :db.type/ref :cardinality :db.cardinality/many}
   #:db{:ident :offer-group/offerable-assets :valueType :db.type/ref :cardinality :db.cardinality/many}
   #:db{:ident :offer-group/requestable-assets :valueType :db.type/ref :cardinality :db.cardinality/many}
   #:db{:ident :offer-group/resolve-dispute-user-roles :valueType :db.type/ref :cardinality :db.cardinality/many}
   #:db{:ident :offer-group/response-field-configs :valueType :db.type/ref :cardinality :db.cardinality/many}
   #:db{:ident :offer-group/user-ratings :valueType :db.type/ref :cardinality :db.cardinality/many}

   #:db{:ident :offer-group-user-rating/rating :valueType :db.type/float :cardinality :db.cardinality/one}
   #:db{:ident :offer-group-user-rating/ratings-count :valueType :db.type/long :cardinality :db.cardinality/one}
   #:db{:ident :offer-group-user-rating/user :valueType :db.type/ref :cardinality :db.cardinality/one}

   #:db{:ident :offer/smart-contract :valueType :db.type/ref :cardinality :db.cardinality/one}
   #:db{:ident :offer/allowed-respondents :valueType :db.type/string :cardinality :db.cardinality/many}
   #:db{:ident :offer/available-supply :valueType :db.type/ref :cardinality :db.cardinality/one}
   #:db{:ident :offer/created-on :valueType :db.type/instant :cardinality :db.cardinality/one}
   #:db{:ident :offer/field-uuid :valueType :db.type/ref :cardinality :db.cardinality/one}
   #:db{:ident :offer/offer-request :valueType :db.type/ref :cardinality :db.cardinality/one}
   #:db{:ident :offer/offer-responses :valueType :db.type/ref :cardinality :db.cardinality/many}
   #:db{:ident :offer/offered-value :valueType :db.type/ref :cardinality :db.cardinality/one}
   #:db{:ident :offer/offerer :valueType :db.type/ref :cardinality :db.cardinality/one}
   #:db{:ident :offer/type :valueType :db.type/ref :cardinality :db.cardinality/one}

   #:db{:ident :offer-request/accepted-tokens :valueType :db.type/ref :cardinality :db.cardinality/many}
   #:db{:ident :offer-request/duration :valueType :db.type/long :cardinality :db.cardinality/one}
   #:db{:ident :offer-request/end-price :valueType :db.type/bigint :cardinality :db.cardinality/one}
   #:db{:ident :offer-request/extension-duration :valueType :db.type/long :cardinality :db.cardinality/one}
   #:db{:ident :offer-request/extension-trigger-duration :valueType :db.type/long :cardinality :db.cardinality/one}
   #:db{:ident :offer-request/fixed-prices :valueType :db.type/ref :cardinality :db.cardinality/many}
   #:db{:ident :offer-request/min-bid-step :valueType :db.type/bigint :cardinality :db.cardinality/one}
   #:db{:ident :offer-request/min-price :valueType :db.type/bigint :cardinality :db.cardinality/one}
   #:db{:ident :offer-request/start-price :valueType :db.type/bigint :cardinality :db.cardinality/one}
   #:db{:ident :offer-request/token-contract :valueType :db.type/ref :cardinality :db.cardinality/one}
   #:db{:ident :offer-request/token-id :valueType :db.type/long :cardinality :db.cardinality/one}

   #:db{:ident :offer-response/index :valueType :db.type/long :cardinality :db.cardinality/one}
   #:db{:ident :offer-response/canceled-on :valueType :db.type/instant :cardinality :db.cardinality/one}
   #:db{:ident :offer-response/completed-on :valueType :db.type/instant :cardinality :db.cardinality/one}
   #:db{:ident :offer-response/created-on :valueType :db.type/instant :cardinality :db.cardinality/one}
   #:db{:ident :offer-response/dispute :valueType :db.type/ref :cardinality :db.cardinality/one}
   #:db{:ident :offer-response/dispute-resolvers :valueType :db.type/string :cardinality :db.cardinality/many}
   #:db{:ident :offer-response/field-uuid :valueType :db.type/ref :cardinality :db.cardinality/one}
   #:db{:ident :offer-response/messages :valueType :db.type/ref :cardinality :db.cardinality/many}
   #:db{:ident :offer-response/offerer-feedback :valueType :db.type/ref :cardinality :db.cardinality/one}
   #:db{:ident :offer-response/offerer-received-value :valueType :db.type/ref :cardinality :db.cardinality/one}
   #:db{:ident :offer-response/respondent :valueType :db.type/ref :cardinality :db.cardinality/one}
   #:db{:ident :offer-response/respondent-feedback :valueType :db.type/ref :cardinality :db.cardinality/one}
   #:db{:ident :offer-response/respondent-received-value :valueType :db.type/ref :cardinality :db.cardinality/one}
   #:db{:ident :offer-response/response-value :valueType :db.type/ref :cardinality :db.cardinality/one}
   #:db{:ident :offer-response/updated-on :valueType :db.type/instant :cardinality :db.cardinality/one}

   #:db{:ident :offer-dispute/raised-on :valueType :db.type/instant :cardinality :db.cardinality/one}
   #:db{:ident :offer-dispute/resolved-by :valueType :db.type/string :cardinality :db.cardinality/one}
   #:db{:ident :offer-dispute/resolved-on :valueType :db.type/instant :cardinality :db.cardinality/one}

   #:db{:ident :trade-value/asset :valueType :db.type/ref :cardinality :db.cardinality/one}
   #:db{:ident :trade-value/nft-tokens :valueType :db.type/ref :cardinality :db.cardinality/many}
   #:db{:ident :trade-value/textual-repr :valueType :db.type/string :cardinality :db.cardinality/one}
   #:db{:ident :trade-value/values :valueType :db.type/bigint :cardinality :db.cardinality/many}

   #:db{:ident :feedback/uuid :valueType :db.type/uuid :cardinality :db.cardinality/one :unique :db.unique/identity}
   #:db{:ident :feedback/created-on :valueType :db.type/instant :cardinality :db.cardinality/one}
   #:db{:ident :feedback/rating :valueType :db.type/float :cardinality :db.cardinality/one}
   #:db{:ident :feedback/receiver :valueType :db.type/ref :cardinality :db.cardinality/one}
   #:db{:ident :feedback/sender :valueType :db.type/ref :cardinality :db.cardinality/one}
   #:db{:ident :feedback/text :valueType :db.type/string :cardinality :db.cardinality/one}
   #:db{:ident :feedback/updated-on :valueType :db.type/instant :cardinality :db.cardinality/one}])