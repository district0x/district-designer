(ns server-tests.district-designer.spec-tests
  (:require
    [cljs.spec.alpha :as s]
    [cljs.test :refer-macros [deftest is testing run-tests use-fixtures async]]
    [district-designer.shared.spec.ipfs-events :refer [event-type]]
    [district-designer.shared.spec.smart-contract-events]))

(s/def :event/event (s/multi-spec event-type :event/type))


(deftest ipfs-events-spec-tests
  (is (s/valid? :event/event
                {:event/type :district-designer/events-batch
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :events [{:event/type :district-designer/add-proxy-factory
                           :timestamp 1590913803
                           :smart-contract/address "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                           :smart-contract/abi {:file/hash "QmW2WQi7j6c7UgJTarActp7tDNikE4B2qXtFCfLPdsgaTQ"
                                                :file/name "abc"}}
                          {:event/type :district-designer/add-district-factory
                           :timestamp 1590913803
                           :smart-contract/address "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                           :smart-contract/abi {:file/hash "QmW2WQi7j6c7UgJTarActp7tDNikE4B2qXtFCfLPdsgaTQ"
                                                :file/name "abc"}
                           :smart-contract/proxy? true
                           :smart-contract/proxy-target "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                           :smart-contract/proxy-type :owner-proxy
                           :smart-contract/owner "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"}]}))


  (is (s/valid? :event/event
                {:event/type :district-designer/add-module
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :module/code "district_designer"
                 :module/name "District Designer"
                 :module/logo "QmW2WQi7j6c7UgJTarActp7tDNikE4B2qXtFCfLPdsgaTQ"
                 :module/owner "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :module/description "abc"
                 :module/preview-images [{:file/hash "QmW2WQi7j6c7UgJTarActp7tDNikE4B2qXtFCfLPdsgaTQ"}]}))

  (is (s/valid? :event/event
                {:event/type :district-designer/update-module
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :module/code "district_designer"
                 :module/name "District Designer"}))

  (is (s/valid? :event/event
                {:event/type :district-designer/remove-module
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :module/code "district_designer"}))

  (is (s/valid? :event/event
                {:event/type :district-designer/add-wizard
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :wizard/code "collectibles_marketplace"
                 :wizard/name "Collectibles Marketplace"
                 :wizard/logo "QmW2WQi7j6c7UgJTarActp7tDNikE4B2qXtFCfLPdsgaTQ"
                 :wizard/owner "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :wizard/description "abc"
                 :wizard/preview-images [{:file/hash "QmW2WQi7j6c7UgJTarActp7tDNikE4B2qXtFCfLPdsgaTQ"}]}))

  (is (s/valid? :event/event
                {:event/type :district-designer/update-wizard
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :wizard/code "collectibles_marketplace"
                 :wizard/name "Collectibles Marketplace"}))

  (is (s/valid? :event/event
                {:event/type :district-designer/remove-wizard
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :wizard/code "collectibles_marketplace"}))

  (is (s/valid? :event/event
                {:event/type :district-designer/add-theme
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :theme/code "some_theme"
                 :theme/name "Some Theme"
                 :theme/owner "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :theme/description "abc"
                 :theme/default-settings "{:a 1 :b 2}"
                 :theme/files [{:file/hash "QmW2WQi7j6c7UgJTarActp7tDNikE4B2qXtFCfLPdsgaTQ"}]
                 :theme/preview-images [{:file/hash "QmW2WQi7j6c7UgJTarActp7tDNikE4B2qXtFCfLPdsgaTQ"}]}))

  (is (s/valid? :event/event
                {:event/type :district-designer/update-theme
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :theme/code "some_theme"
                 :theme/name "Some Theme"}))

  (is (s/valid? :event/event
                {:event/type :district-designer/remove-theme
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :theme/code "some_theme"}))

  (is (s/valid? :event/event
                {:event/type :district-designer/add-permission
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :permission/code "some_permission"
                 :permission/name "Some permission"
                 :permission/description "abc"}))

  (is (s/valid? :event/event
                {:event/type :district-designer/update-permission
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :permission/code "some_permission"
                 :permission/name "Some permission"}))

  (is (s/valid? :event/event
                {:event/type :district-designer/remove-permission
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :permission/code "some_permission"}))

  (is (s/valid? :event/event
                {:event/type :district-designer/add-tag-group
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :tag-group/uuid #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"
                 :tag-group/name "abc"
                 :tag-group/district-origin "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :tag-group/users-allowed-adding-tags? true
                 :tag-group/global-enabled? true}))

  (is (s/valid? :event/event
                {:event/type :district-designer/add-tags
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :tag-group/uuid #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"
                 :tag-group/tags ["abc" "def"]}))

  (is (s/valid? :event/event
                {:event/type :district/update-theme
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :district/address "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :theme/code "some_theme"
                 :district-theme/settings "{:a 1 :b 2}"
                 :district-theme/css {:file/hash "QmW2WQi7j6c7UgJTarActp7tDNikE4B2qXtFCfLPdsgaTQ"}
                 :district-theme/less {:file/hash "QmW2WQi7j6c7UgJTarActp7tDNikE4B2qXtFCfLPdsgaTQ"}
                 :district-theme/active? true}))

  (is (s/valid? :event/event
                {:event/type :district/activate-module
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :district/address "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :module/code "marketplace"}))

  (is (s/valid? :event/event
                {:event/type :district/deactivate-module
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :district/address "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :module/code "marketplace"}))

  (is (s/valid? :event/event
                {:event/type :district/add-ui-component
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :district/address "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :ui-component/uuid #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"
                 :ui-component/name "abc"
                 :ui-component/type "abc"
                 :ui-component/children [#uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"]
                 :ui-component/settings "<str>"
                 :ui-component/files [{:file/hash "QmW2WQi7j6c7UgJTarActp7tDNikE4B2qXtFCfLPdsgaTQ"
                                       :file/name "abc"}]}))

  (is (s/valid? :event/event
                {:event/type :district/update-ui-component
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :district/address "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :ui-component/uuid #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"
                 :ui-component/name "abc"
                 :ui-component/type "abc"
                 :ui-component/children [#uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"]
                 :ui-component/settings "{:a 1 :b 2}"
                 :ui-component/files [{:file/hash "QmW2WQi7j6c7UgJTarActp7tDNikE4B2qXtFCfLPdsgaTQ"
                                       :file/name "abc"}]}))

  (is (s/valid? :event/event
                {:event/type :district/remove-ui-component
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :district/address "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :ui-component/uuid #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"})))


(deftest smart-contract-events-spec-tests
  (is (s/valid? :event/event
                {:event/type :district-designer/district-created
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :district "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :permissions [{:permission-id "some_permission"
                                :user-role-ids [#uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"]}]
                 :user-roles [{:user-role-id #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"
                               :addresses ["0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"]
                               :is-removed false}]
                 :admin-user-role-id #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"
                 :treasury "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :ipfs-data {:user-role-names [{:user-role/uuid #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"
                                                :user-role/name "Admins"}]
                             :wizard/code "some_wizard"
                             :some-key "random_value"}}))


  (is (s/valid? :event/event
                {:event/type :district-designer/permissions-updated
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :district "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :permissions [{:permission-id "some_permission"
                                :user-role-ids [#uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"]}]}))


  (is (s/valid? :event/event
                {:event/type :district-designer/user-roles-updated
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :district "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :user-roles [{:user-role-id #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"
                               :addresses ["0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"]
                               :is-removed false}]
                 :ipfs-data {:user-role-names [{:user-role/uuid #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"
                                                :user-role/name "Admins"}]}}))


  (is (s/valid? :event/event
                {:event/type :district-designer/district-treasury-updated
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :district "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :treasury "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"}))


  (is (s/valid? :event/event
                {:event/type :district-designer/emergency-updated
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :module-id "some_module"
                 :is-emergency true}))


  (is (s/valid? :event/event
                {:event/type :proxy-factory/proxy-target-updated
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :proxy "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :old-target {:contract-address "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                              :ipfs-abi "QmW2WQi7j6c7UgJTarActp7tDNikE4B2qXtFCfLPdsgaTQ"}
                 :new-target {:contract-address "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                              :ipfs-abi "QmW2WQi7j6c7UgJTarActp7tDNikE4B2qXtFCfLPdsgaTQ"}})))