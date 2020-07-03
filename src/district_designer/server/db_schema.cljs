(ns district-designer.server.db-schema)

(def db-idents
  [#:db{:ident :smart-contract/address :valueType :db.type/string :cardinality :db.cardinality/one :unique :db.unique/identity}
   #:db{:ident :smart-contract/abi :valueType :db.type/ref :cardinality :db.cardinality/one}
   #:db{:ident :smart-contract/district :valueType :db.type/ref :cardinality :db.cardinality/one}
   #:db{:ident :smart-contract/name :valueType :db.type/string :cardinality :db.cardinality/one :index true}
   #:db{:ident :smart-contract/owner :valueType :db.type/string :cardinality :db.cardinality/one}
   #:db{:ident :smart-contract/proxy-target :valueType :db.type/string :cardinality :db.cardinality/one}
   #:db{:ident :smart-contract/proxy-type :valueType :db.type/ref :cardinality :db.cardinality/one}
   #:db{:ident :smart-contract/proxy? :valueType :db.type/boolean :cardinality :db.cardinality/one}
   #:db{:ident :smart-contract/version :valueType :db.type/long :cardinality :db.cardinality/one :index true}

   #:db{:ident :smart-contract-events/smart-contract :valueType :db.type/ref :cardinality :db.cardinality/one}
   #:db{:ident :smart-contract-events/event-key :valueType :db.type/string :cardinality :db.cardinality/one}
   #:db{:ident :smart-contract-events/smart-contract+event-key :valueType :db.type/tuple :cardinality :db.cardinality/one
        :tupleAttrs [:smart-contract-events/smart-contract :smart-contract-events/event-key] :unique :db.unique/identity}
   #:db{:ident :smart-contract-events/last-block-number :valueType :db.type/long :cardinality :db.cardinality/one}
   #:db{:ident :smart-contract-events/last-log-index :valueType :db.type/long :cardinality :db.cardinality/one}

   #:db{:ident :ipfs-events/last-hash :valueType :db.type/string :cardinality :db.cardinality/one :unique :db.unique/identity}

   #:db{:ident :district/smart-contract :valueType :db.type/ref :cardinality :db.cardinality/one :unique :db.unique/identity}
   #:db{:ident :district/admin-user-role :valueType :db.type/ref :cardinality :db.cardinality/one}
   #:db{:ident :district/cover-image :valueType :db.type/ref :cardinality :db.cardinality/one}
   #:db{:ident :district/created-on :valueType :db.type/instant :cardinality :db.cardinality/one}
   #:db{:ident :district/css-file :valueType :db.type/ref :cardinality :db.cardinality/one}
   #:db{:ident :district/database-views :valueType :db.type/ref :cardinality :db.cardinality/many}
   #:db{:ident :district/description :valueType :db.type/string :cardinality :db.cardinality/one}
   #:db{:ident :district/favicon :valueType :db.type/ref :cardinality :db.cardinality/one}
   #:db{:ident :district/ga-tracking-id :valueType :db.type/string :cardinality :db.cardinality/one}
   #:db{:ident :district/less-file :valueType :db.type/ref :cardinality :db.cardinality/one}
   #:db{:ident :district/logo :valueType :db.type/ref :cardinality :db.cardinality/one}
   #:db{:ident :district/modules :valueType :db.type/ref :cardinality :db.cardinality/many}
   #:db{:ident :district/name :valueType :db.type/string :cardinality :db.cardinality/one}
   #:db{:ident :district/pages :valueType :db.type/ref :cardinality :db.cardinality/many}
   #:db{:ident :district/permissions-user-roles :valueType :db.type/ref :cardinality :db.cardinality/many}
   #:db{:ident :district/statistics-views :valueType :db.type/ref :cardinality :db.cardinality/many}
   #:db{:ident :district/subdomain :valueType :db.type/string :cardinality :db.cardinality/one :unique :db.unique/identity}
   #:db{:ident :district/theme :valueType :db.type/ref :cardinality :db.cardinality/one}
   #:db{:ident :district/theme-settings :valueType :db.type/string :cardinality :db.cardinality/one}
   #:db{:ident :district/title :valueType :db.type/string :cardinality :db.cardinality/one}
   #:db{:ident :district/treasury :valueType :db.type/string :cardinality :db.cardinality/one}
   #:db{:ident :district/ui-components :valueType :db.type/ref :cardinality :db.cardinality/many}
   #:db{:ident :district/updated-on :valueType :db.type/instant :cardinality :db.cardinality/one}
   #:db{:ident :district/user-roles :valueType :db.type/ref :cardinality :db.cardinality/many}

   #:db{:ident :field-config/uuid :valueType :db.type/uuid :cardinality :db.cardinality/one :unique :db.unique/identity}
   #:db{:ident :field-config/name :valueType :db.type/string :cardinality :db.cardinality/one}
   #:db{:ident :field-config/namespace :valueType :db.type/string :cardinality :db.cardinality/one}
   #:db{:ident :field-config/settings :valueType :db.type/string :cardinality :db.cardinality/one}
   #:db{:ident :field-config/type :valueType :db.type/string :cardinality :db.cardinality/one}

   #:db{:ident :file/hash :valueType :db.type/string :cardinality :db.cardinality/one :unique :db.unique/identity}
   #:db{:ident :file/decryptable-by :valueType :db.type/string :cardinality :db.cardinality/one}
   #:db{:ident :file/encrypted? :valueType :db.type/boolean :cardinality :db.cardinality/one}
   #:db{:ident :file/name :valueType :db.type/string :cardinality :db.cardinality/one}



   #:db{:ident :module/id :valueType :db.type/string :cardinality :db.cardinality/one :unique :db.unique/identity}
   #:db{:ident :module/description :valueType :db.type/string :cardinality :db.cardinality/one}
   #:db{:ident :module/installs-count :valueType :db.type/long :cardinality :db.cardinality/one}
   #:db{:ident :module/logo :valueType :db.type/ref :cardinality :db.cardinality/one}
   #:db{:ident :module/name :valueType :db.type/string :cardinality :db.cardinality/one :unique :db.unique/identity}
   #:db{:ident :module/owner :valueType :db.type/string :cardinality :db.cardinality/one}
   #:db{:ident :module/preview-images :valueType :db.type/ref :cardinality :db.cardinality/many}

   #:db{:ident :permission/id :valueType :db.type/string :cardinality :db.cardinality/one :unique :db.unique/identity}
   #:db{:ident :permission/description :valueType :db.type/string :cardinality :db.cardinality/one}
   #:db{:ident :permission/name :valueType :db.type/string :cardinality :db.cardinality/one}
   #:db{:ident :permission-user-roles/permission :valueType :db.type/ref :cardinality :db.cardinality/one}
   #:db{:ident :permission-user-roles/user-roles :valueType :db.type/ref :cardinality :db.cardinality/many}

   #:db{:ident :user-role/addresses :valueType :db.type/string :cardinality :db.cardinality/many}
   #:db{:ident :user-role/name :valueType :db.type/string :cardinality :db.cardinality/one}
   #:db{:ident :user-role/uuid :valueType :db.type/uuid :cardinality :db.cardinality/one :unique :db.unique/identity}

   #:db{:ident :proxy-type/district-admin-proxy}
   #:db{:ident :proxy-type/owner-proxy}




   #:db{:ident :theme/id :valueType :db.type/string :cardinality :db.cardinality/one :unique :db.unique/identity}
   #:db{:ident :theme/default-settings :valueType :db.type/string :cardinality :db.cardinality/one}
   #:db{:ident :theme/description :valueType :db.type/string :cardinality :db.cardinality/one}
   #:db{:ident :theme/files :valueType :db.type/ref :cardinality :db.cardinality/many}
   #:db{:ident :theme/installs-count :valueType :db.type/long :cardinality :db.cardinality/one}
   #:db{:ident :theme/name :valueType :db.type/string :cardinality :db.cardinality/one :unique :db.unique/identity}
   #:db{:ident :theme/owner :valueType :db.type/string :cardinality :db.cardinality/one}
   #:db{:ident :theme/preview-images :valueType :db.type/ref :cardinality :db.cardinality/many}

   #:db{:ident :ui-component/uuid :valueType :db.type/uuid :cardinality :db.cardinality/one :unique :db.unique/identity}
   #:db{:ident :ui-component/children :valueType :db.type/ref :cardinality :db.cardinality/many}
   #:db{:ident :ui-component/files :valueType :db.type/ref :cardinality :db.cardinality/many}
   #:db{:ident :ui-component/name :valueType :db.type/string :cardinality :db.cardinality/one}
   #:db{:ident :ui-component/settings :valueType :db.type/string :cardinality :db.cardinality/one}
   #:db{:ident :ui-component/type :valueType :db.type/string :cardinality :db.cardinality/one}

   #:db{:ident :wizard/id :valueType :db.type/string :cardinality :db.cardinality/one :unique :db.unique/identity}
   #:db{:ident :wizard/completions-count :valueType :db.type/long :cardinality :db.cardinality/one}
   #:db{:ident :wizard/description :valueType :db.type/string :cardinality :db.cardinality/one}
   #:db{:ident :wizard/logo :valueType :db.type/ref :cardinality :db.cardinality/one}
   #:db{:ident :wizard/name :valueType :db.type/string :cardinality :db.cardinality/one :unique :db.unique/identity}
   #:db{:ident :wizard/owner :valueType :db.type/string :cardinality :db.cardinality/one}
   #:db{:ident :wizard/preview-images :valueType :db.type/ref :cardinality :db.cardinality/many}])