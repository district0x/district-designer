(ns tokens.server.db-schema)

(def db-idents
  [#:db{:ident :district/token-contracts :valueType :db.type/ref :cardinality :db.cardinality/many}

   #:db{:ident :token-contract/smart-contract :valueType :db.type/ref :cardinality :db.cardinality/one}
   #:db{:ident :token-contract/added-by :valueType :db.type/string :cardinality :db.cardinality/one}
   #:db{:ident :token-contract/created-by :valueType :db.type/ref :cardinality :db.cardinality/one}
   #:db{:ident :token-contract/decimals :valueType :db.type/long :cardinality :db.cardinality/one}
   #:db{:ident :token-contract/metadata-format :valueType :db.type/string :cardinality :db.cardinality/one}
   #:db{:ident :token-contract/metadata-format-settings :valueType :db.type/string :cardinality :db.cardinality/one}
   #:db{:ident :token-contract/name :valueType :db.type/string :cardinality :db.cardinality/one}
   #:db{:ident :token-contract/nft-tokens :valueType :db.type/ref :cardinality :db.cardinality/many}
   #:db{:ident :token-contract/owner :valueType :db.type/string :cardinality :db.cardinality/one}
   #:db{:ident :token-contract/reported-misconfig-by :valueType :db.type/string :cardinality :db.cardinality/one}
   #:db{:ident :token-contract/reported-misconfig-comment :valueType :db.type/string :cardinality :db.cardinality/one}
   #:db{:ident :token-contract/reported-misconfig-on :valueType :db.type/instant :cardinality :db.cardinality/one}
   #:db{:ident :token-contract/reported-misconfig-resolved-by :valueType :db.type/string :cardinality :db.cardinality/one}
   #:db{:ident :token-contract/reported-misconfig-resolved-on :valueType :db.type/instant :cardinality :db.cardinality/one}
   #:db{:ident :token-contract/symbol :valueType :db.type/string :cardinality :db.cardinality/one}
   #:db{:ident :token-contract/total-supply :valueType :db.type/bigint :cardinality :db.cardinality/one}
   #:db{:ident :token-contract/type :valueType :db.type/ref :cardinality :db.cardinality/one}

   #:db{:ident :token-type/erc-1155}
   #:db{:ident :token-type/erc-20}
   #:db{:ident :token-type/erc-721}])