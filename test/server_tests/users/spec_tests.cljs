(ns server-tests.users.spec-tests
  (:require
    [cljs.spec.alpha :as s]
    [cljs.test :refer-macros [deftest is testing run-tests use-fixtures async]]
    [users.shared.spec.ipfs-events]))


(deftest ipfs-events-spec-tests
  (is (s/valid? :users/add-direct-message
                {:event :users/add-direct-message
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :message/uuid #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"
                 :message/receiver "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :message/text "abc"
                 :message/files [{:file/hash "QmW2WQi7j6c7UgJTarActp7tDNikE4B2qXtFCfLPdsgaTQ"
                                  :file/name "abc"}]}))


  (is (s/valid? :users/add-user-profile
                {:event :users/add-user-profile
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
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
                 :user-profile/global-description "abc"}))


  (is (s/valid? :users/update-user-profile
                {:event :users/update-user-profile
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
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
                 :user-profile/global-description "abc"}))


  (is (s/valid? :users/add-district-user-profile
                {:event :users/add-district-user-profile
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :district/uuid #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"
                 :user-profile/uuid #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"}))


  (is (s/valid? :users/remove-district-user-profile
                {:event :users/remove-district-user-profile
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :district/uuid #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"
                 :user-profile/uuid #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"}))


  (is (s/valid? :users/update-user
                {:event :users/remove-district-user-profile
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :user/field-909659f5-560c-4640-9d67-7a1977da92b5 "abc"})))