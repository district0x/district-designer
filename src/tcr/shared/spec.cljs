(ns tcr.shared.spec
  (:require
    [cljs.spec.alpha :as s]
    [district-designer.shared.spec :refer [ipfs-hash? address? edn?]]))


(s/def :tcr/uuid uuid?)
(s/def :tcr/name string?)
(s/def :tcr/reg-entry-field-configs (s/coll-of :field-config/field-config))
(s/def :tcr/global-enabled? boolean?)
(s/def :tcr/global-logo :file/file)
(s/def :tcr/global-description string?)

(s/def :tcr/add-tcr-factory :district-designer/add-dd-proxy-factory)

(s/def :tcr/update-tcr
  (s/keys :req-un [:district-designer.server.spec/event
                   :district-designer.server.spec/sender]
          :req [:tcr/uuid]
          :opt [:tcr/name
                :tcr/reg-entry-field-configs
                :tcr/global-enabled?
                :tcr/global-logo
                :tcr/global-description]))


(s/def :tcr/add-district-tcr
  (s/keys :req-un [:district-designer.server.spec/event
                   :district-designer.server.spec/sender]
          :req [:district/uuid
                :tcr/uuid]))

(s/def :tcr/remove-district-tcr :tcr/add-district-tcr)


(comment
  (s/valid? :tcr/update-tcr
            {:event :tcr/update-tcr
             :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
             :tcr/uuid #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"
             :tcr/name "abc"
             :tcr/reg-entry-field-configs [{:field-config/uuid #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"
                                            :field-config/name "abc"
                                            :field-config/namespace "abc"
                                            :field-config/type "abc"
                                            :field-config/settings "{:a 1 :b 2}"}]
             :tcr/global-enabled? true
             :tcr/global-logo {:file/hash "QmW2WQi7j6c7UgJTarActp7tDNikE4B2qXtFCfLPdsgaTQ"
                               :file/name "abc"}
             :tcr/global-description "abc"})


  (s/valid? :tcr/add-district-tcr
            {:event :tcr/add-district-tcr
             :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
             :district/uuid #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"
             :tcr/uuid #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"}))