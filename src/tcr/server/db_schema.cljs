(ns tcr.server.db-schema)

(def db-idents
  [#:db{:ident :district/tcrs :valueType :db.type/ref :cardinality :db.cardinality/many}

   #:db{:ident :tcr-type/challengeable-anytime}
   #:db{:ident :tcr-type/challengeable-once}

   #:db{:ident :tcr/smart-contract :valueType :db.type/ref :cardinality :db.cardinality/one}
   #:db{:ident :tcr/create-param-change-entry-user-roles :valueType :db.type/ref :cardinality :db.cardinality/many}
   #:db{:ident :tcr/create-reg-entry-user-roles :valueType :db.type/ref :cardinality :db.cardinality/many}
   #:db{:ident :tcr/created-by :valueType :db.type/ref :cardinality :db.cardinality/one}
   #:db{:ident :tcr/created-on :valueType :db.type/instant :cardinality :db.cardinality/one}
   #:db{:ident :tcr/global-description :valueType :db.type/string :cardinality :db.cardinality/one}
   #:db{:ident :tcr/global-enabled? :valueType :db.type/boolean :cardinality :db.cardinality/one}
   #:db{:ident :tcr/global-imports-count :valueType :db.type/long :cardinality :db.cardinality/one}
   #:db{:ident :tcr/global-logo :valueType :db.type/ref :cardinality :db.cardinality/one}
   #:db{:ident :tcr/name :valueType :db.type/string :cardinality :db.cardinality/one}
   #:db{:ident :tcr/param-change-entries :valueType :db.type/ref :cardinality :db.cardinality/many}
   #:db{:ident :tcr/param-change-entry-parameters :valueType :db.type/ref :cardinality :db.cardinality/one}
   #:db{:ident :tcr/reg-entries :valueType :db.type/ref :cardinality :db.cardinality/many}
   #:db{:ident :tcr/reg-entry-field-configs :valueType :db.type/ref :cardinality :db.cardinality/many}
   #:db{:ident :tcr/reg-entry-parameters :valueType :db.type/ref :cardinality :db.cardinality/one}
   #:db{:ident :tcr/reg-entry-representation-category :valueType :db.type/ref :cardinality :db.cardinality/one}
   #:db{:ident :tcr/reg-entry-representation-token-contract :valueType :db.type/ref :cardinality :db.cardinality/one}
   #:db{:ident :tcr/type :valueType :db.type/ref :cardinality :db.cardinality/one}
   #:db{:ident :tcr/voting-token-contract :valueType :db.type/ref :cardinality :db.cardinality/one}
   #:db{:ident :tcr/voting-token-id :valueType :db.type/long :cardinality :db.cardinality/one}

   #:db{:ident :tcr-challenge/index :valueType :db.type/long :cardinality :db.cardinality/one}
   #:db{:ident :tcr-challenge/challenge-period-end :valueType :db.type/instant :cardinality :db.cardinality/one}
   #:db{:ident :tcr-challenge/challenger :valueType :db.type/ref :cardinality :db.cardinality/one}
   #:db{:ident :tcr-challenge/challenger-reward-amount :valueType :db.type/bigint :cardinality :db.cardinality/one}
   #:db{:ident :tcr-challenge/challenger-reward-claimed-on :valueType :db.type/instant :cardinality :db.cardinality/one}
   #:db{:ident :tcr-challenge/comment :valueType :db.type/string :cardinality :db.cardinality/one}
   #:db{:ident :tcr-challenge/created-on :valueType :db.type/instant :cardinality :db.cardinality/one}
   #:db{:ident :tcr-challenge/reward-pool :valueType :db.type/bigint :cardinality :db.cardinality/one}
   #:db{:ident :tcr-challenge/vote-commit-period-end :valueType :db.type/instant :cardinality :db.cardinality/one}
   #:db{:ident :tcr-challenge/vote-reveal-period-end :valueType :db.type/instant :cardinality :db.cardinality/one}
   #:db{:ident :tcr-challenge/votes :valueType :db.type/ref :cardinality :db.cardinality/many}
   #:db{:ident :tcr-challenge/votes-exclude :valueType :db.type/bigint :cardinality :db.cardinality/one}
   #:db{:ident :tcr-challenge/votes-include :valueType :db.type/bigint :cardinality :db.cardinality/one}
   #:db{:ident :tcr-challenge/votes-total :valueType :db.type/bigint :cardinality :db.cardinality/one}

   #:db{:ident :tcr-reg-entry/challenges :valueType :db.type/ref :cardinality :db.cardinality/many}
   #:db{:ident :tcr-reg-entry/created-on :valueType :db.type/instant :cardinality :db.cardinality/one}
   #:db{:ident :tcr-reg-entry/field-uuid :valueType :db.type/ref :cardinality :db.cardinality/one}
   #:db{:ident :tcr-reg-entry/smart-contract :valueType :db.type/ref :cardinality :db.cardinality/one}
   #:db{:ident :tcr-reg-entry-representation-category/erc-1155}
   #:db{:ident :tcr-reg-entry-representation-category/erc-721}
   #:db{:ident :tcr-reg-entry-representation-category/no-token}

   #:db{:ident :tcr-param-change-entry/smart-contract :valueType :db.type/ref :cardinality :db.cardinality/one}
   #:db{:ident :tcr-param-change-entry/applied-on :valueType :db.type/instant :cardinality :db.cardinality/one}
   #:db{:ident :tcr-param-change-entry/challenges :valueType :db.type/ref :cardinality :db.cardinality/many}
   #:db{:ident :tcr-param-change-entry/comment :valueType :db.type/string :cardinality :db.cardinality/one}
   #:db{:ident :tcr-param-change-entry/created-on :valueType :db.type/instant :cardinality :db.cardinality/one}
   #:db{:ident :tcr-param-change-entry/db :valueType :db.type/string :cardinality :db.cardinality/one}
   #:db{:ident :tcr-param-change-entry/parameter-key :valueType :db.type/string :cardinality :db.cardinality/one}
   #:db{:ident :tcr-param-change-entry/parameter-original-value :valueType :db.type/bigint :cardinality :db.cardinality/one}
   #:db{:ident :tcr-param-change-entry/parameter-value :valueType :db.type/bigint :cardinality :db.cardinality/one}

   #:db{:ident :tcr-vote/amount :valueType :db.type/bigint :cardinality :db.cardinality/one}
   #:db{:ident :tcr-vote/created-on :valueType :db.type/instant :cardinality :db.cardinality/one}
   #:db{:ident :tcr-vote/option :valueType :db.type/ref :cardinality :db.cardinality/one}
   #:db{:ident :tcr-vote/revealed-on :valueType :db.type/instant :cardinality :db.cardinality/one}
   #:db{:ident :tcr-vote/reward-claimed-on :valueType :db.type/instant :cardinality :db.cardinality/one}
   #:db{:ident :tcr-vote/voter :valueType :db.type/ref :cardinality :db.cardinality/one}
   #:db{:ident :tcr-vote/votes-reclaimed-on :valueType :db.type/instant :cardinality :db.cardinality/one}

   #:db{:ident :tcr-vote-option/exclude}
   #:db{:ident :tcr-vote-option/include}
   #:db{:ident :tcr-vote-option/no-vote}

   #:db{:ident :tcr-parameters/challenge-dispensation :valueType :db.type/long :cardinality :db.cardinality/one}
   #:db{:ident :tcr-parameters/challenge-period-duration :valueType :db.type/long :cardinality :db.cardinality/one}
   #:db{:ident :tcr-parameters/deposit :valueType :db.type/bigint :cardinality :db.cardinality/one}
   #:db{:ident :tcr-parameters/vote-commit-period-duration :valueType :db.type/long :cardinality :db.cardinality/one}
   #:db{:ident :tcr-parameters/vote-quorum :valueType :db.type/long :cardinality :db.cardinality/one}
   #:db{:ident :tcr-parameters/vote-reveal-period-duration :valueType :db.type/long :cardinality :db.cardinality/one}])