(ns users.shared.spec
  (:require
    [cljs.spec.alpha :as s]
    [district-designer.shared.spec :refer [ipfs-hash? address? edn?]]))

(s/def :message/uuid uuid?)
(s/def :message/receiver address?)
(s/def :message/text string?)
(s/def :message/files (s/coll-of :file/file))

(s/def :user-profile/uuid uuid?)
(s/def :user-profile/name string?)
(s/def :user-profile/field-configs (s/coll-of :field-config/field-config))
(s/def :user-profile/global-enabled? boolean?)
(s/def :user-profile/global-logo :file/file)
(s/def :user-profile/global-description string?)

(s/def :users/add-direct-message
  (s/keys :req-un [:district-designer.server.spec/event
                   :district-designer.server.spec/sender]
          :req [:message/uuid
                :message/receiver
                :message/text]
          :opt [:message/files]))


(s/def :users/add-user-profile
  (s/keys :req-un [:district-designer.server.spec/event
                   :district-designer.server.spec/sender]
          :req [:district/uuid
                :user-profile/uuid
                :user-profile/name
                :user-profile/field-configs]
          :opt [:user-profile/global-enabled?
                :user-profile/global-logo
                :user-profile/global-description]))


(s/def :users/update-user-profile
  (s/keys :req-un [:district-designer.server.spec/event
                   :district-designer.server.spec/sender]
          :req [:user-profile/uuid]
          :opt [:user-profile/name
                :user-profile/field-configs
                :user-profile/global-enabled?
                :user-profile/global-logo
                :user-profile/global-description]))


(s/def :users/remove-user-profile
  (s/keys :req-un [:district-designer.server.spec/event
                   :district-designer.server.spec/sender]
          :req [:user-profile/uuid]))


(s/def :users/add-district-user-profile
  (s/keys :req-un [:district-designer.server.spec/event
                   :district-designer.server.spec/sender]
          :req [:district/uuid
                :user-profile/uuid]))


(s/def :users/remove-district-user-profile :users/add-district-user-profile)


(s/def :users/update-user
  (s/keys :req-un [:district-designer.server.spec/event
                   :district-designer.server.spec/sender]))


(comment
  (s/valid? :users/add-direct-message
            {:event :users/add-direct-message
             :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
             :message/uuid #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"
             :message/receiver "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
             :message/text "abc"
             :message/files [{:file/hash "QmW2WQi7j6c7UgJTarActp7tDNikE4B2qXtFCfLPdsgaTQ"
                              :file/name "abc"}]})


  (s/valid? :users/add-user-profile
            {:event :users/add-user-profile
             :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
             :district/uuid #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"
             :user-profile/uuid #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"
             :user-profile/name "abc"
             :user-profile/field-configs [{:field-config/uuid #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"
                                           :field-config/name "abc"
                                           :field-config/namespace "abc"
                                           :field-config/type "abc"
                                           :field-config/settings "{:a 1 :b 2}"}]
             :user-profile/global-enabled? true
             :user-profile/global-logo {:file/hash "QmW2WQi7j6c7UgJTarActp7tDNikE4B2qXtFCfLPdsgaTQ"
                                        :file/name "abc"}
             :user-profile/global-description "abc"})


  (s/valid? :users/update-user-profile
            {:event :users/update-user-profile
             :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
             :user-profile/uuid #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"
             :user-profile/name "abc"
             :user-profile/field-configs [{:field-config/uuid #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"
                                           :field-config/name "abc"
                                           :field-config/namespace "abc"
                                           :field-config/type "abc"
                                           :field-config/settings "{:a 1 :b 2}"}]
             :user-profile/global-enabled? true
             :user-profile/global-logo {:file/hash "QmW2WQi7j6c7UgJTarActp7tDNikE4B2qXtFCfLPdsgaTQ"
                                        :file/name "abc"}
             :user-profile/global-description "abc"})


  (s/valid? :users/add-district-user-profile
            {:event :users/add-district-user-profile
             :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
             :district/uuid #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"
             :user-profile/uuid #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"})


  (s/valid? :users/remove-district-user-profile
            {:event :users/remove-district-user-profile
             :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
             :district/uuid #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"
             :user-profile/uuid #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"})


  (s/valid? :users/update-user
            {:event :users/remove-district-user-profile
             :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
             :user/field-909659f5-560c-4640-9d67-7a1977da92b5 "abc"})

  )