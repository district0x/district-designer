(ns server-tests.users.spec-tests
  (:require
    [cljs.spec.alpha :as s]
    [cljs.test :refer-macros [deftest is testing run-tests use-fixtures async]]
    [users.shared.spec.ipfs-events]))


(deftest ipfs-events-spec-tests
  (is (s/valid? :event/event
                {:event/type :users/add-direct-message
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :message/uuid #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"
                 :message/receiver "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :message/text "abc"
                 :message/files [{:file/hash "QmW2WQi7j6c7UgJTarActp7tDNikE4B2qXtFCfLPdsgaTQ"
                                  :file/name "abc"}]}))


  (is (s/valid? :event/event
                {:event/type :users/add-user-profile
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :district/address "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :user-profile/uuid #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"
                 :user-profile/name "abc"
                 :user-profile/district-origin "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :user-profile/fields [{:field/uuid #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"
                                        :field/name "abc"
                                        :field/namespace "abc"
                                        :field/type "abc"
                                        :field/settings "{:a 1 :b 2}"}]
                 :user-profile/global-enabled? true
                 :user-profile/global-logo {:file/hash "QmW2WQi7j6c7UgJTarActp7tDNikE4B2qXtFCfLPdsgaTQ"
                                            :file/name "abc"}
                 :user-profile/global-description "abc"}))


  (is (s/valid? :event/event
                {:event/type :users/update-user-profile
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :user-profile/uuid #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"
                 :user-profile/name "abc"
                 :user-profile/fields [{:field/uuid #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"
                                        :field/name "abc"
                                        :field/namespace "abc"
                                        :field/type "abc"
                                        :field/settings "{:a 1 :b 2}"}]
                 :user-profile/global-enabled? true
                 :user-profile/global-logo {:file/hash "QmW2WQi7j6c7UgJTarActp7tDNikE4B2qXtFCfLPdsgaTQ"
                                            :file/name "abc"}
                 :user-profile/global-description "abc"}))


  (is (s/valid? :event/event
                {:event/type :users/add-district-user-profile
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :district/address "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :user-profile/uuid #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"}))


  (is (s/valid? :event/event
                {:event/type :users/remove-district-user-profile
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :district/address "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :user-profile/uuid #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"}))


  (is (s/valid? :event/event
                {:event/type :users/update-user
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :user/field-values [{:field-value/uuid #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"
                                      :field/uuid #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"
                                      :field-value/type :string
                                      :field-value/value "Some Name"}]})))