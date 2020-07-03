(ns tokens.server.db-schema)

(def db-idents
  [#:db{:ident :district/user-profiles :valueType :db.type/ref :cardinality :db.cardinality/many}

   #:db{:ident :user/address :valueType :db.type/string :cardinality :db.cardinality/one :unique :db.unique/identity}
   #:db{:ident :user/global-rating :valueType :db.type/float :cardinality :db.cardinality/one}
   #:db{:ident :user/global-ratings-count :valueType :db.type/long :cardinality :db.cardinality/one}

   #:db{:ident :user-profile/uuid :valueType :db.type/uuid :cardinality :db.cardinality/one :unique :db.unique/identity}
   #:db{:ident :user-profile/created-by :valueType :db.type/ref :cardinality :db.cardinality/one}
   #:db{:ident :user-profile/field-configs :valueType :db.type/ref :cardinality :db.cardinality/many}
   #:db{:ident :user-profile/global-description :valueType :db.type/string :cardinality :db.cardinality/one}
   #:db{:ident :user-profile/global-enabled? :valueType :db.type/boolean :cardinality :db.cardinality/one}
   #:db{:ident :user-profile/global-imports-count :valueType :db.type/long :cardinality :db.cardinality/one}
   #:db{:ident :user-profile/global-logo :valueType :db.type/ref :cardinality :db.cardinality/one}
   #:db{:ident :user-profile/name :valueType :db.type/string :cardinality :db.cardinality/one}])