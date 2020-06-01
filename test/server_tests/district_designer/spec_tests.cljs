(ns server-tests.district-designer.spec-tests
  (:require
    [cljs.spec.alpha :as s]
    [cljs.test :refer-macros [deftest is testing run-tests use-fixtures async]]
    [district-designer.shared.spec.ipfs_events]
    [district-designer.shared.spec.smart-contract-events]))


(deftest ipfs-events-spec-tests
  (is (s/valid? :district-designer/events-batch
                {:event :district-designer/add-dd-proxy-factory
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :events [{:event :district-designer/add-dd-proxy-factory
                           :timestamp 1590913803
                           :smart-contract/address "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                           :smart-contract/abi {:file/hash "QmW2WQi7j6c7UgJTarActp7tDNikE4B2qXtFCfLPdsgaTQ"
                                                :file/name "abc"}}
                          {:event :district-designer/add-district-designer
                           :timestamp 1590913803
                           :smart-contract/address "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                           :smart-contract/abi {:file/hash "QmW2WQi7j6c7UgJTarActp7tDNikE4B2qXtFCfLPdsgaTQ"
                                                :file/name "abc"}}]}))

  (is (s/valid? :district-designer/add-dd-proxy-factory
                {:event :district-designer/add-dd-proxy-factory
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :smart-contract/address "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :smart-contract/abi {:file/hash "QmW2WQi7j6c7UgJTarActp7tDNikE4B2qXtFCfLPdsgaTQ"
                                      :file/name "abc"}}))

  (is (s/valid? :district-designer/add-district-designer
                {:event :district-designer/add-district-designer
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :smart-contract/address "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :smart-contract/abi {:file/hash "QmW2WQi7j6c7UgJTarActp7tDNikE4B2qXtFCfLPdsgaTQ"
                                      :file/name "abc"}}))

  (is (s/valid? :district-designer/add-module
                {:event :district-designer/add-module
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :module/id "district_designer"
                 :module/name "District Designer"
                 :module/logo "QmW2WQi7j6c7UgJTarActp7tDNikE4B2qXtFCfLPdsgaTQ"
                 :module/owner "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :module/description "abc"
                 :module/preview-images [{:file/hash "QmW2WQi7j6c7UgJTarActp7tDNikE4B2qXtFCfLPdsgaTQ"}]}))

  (is (s/valid? :district-designer/update-module
                {:event :district-designer/update-module
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :module/id "district_designer"
                 :module/name "District Designer"}))

  (is (s/valid? :district-designer/remove-module
                {:event :district-designer/remove-module
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :module/id "district_designer"}))

  (is (s/valid? :district-designer/add-wizard
                {:event :district-designer/add-wizard
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :wizard/id "collectibles_marketplace"
                 :wizard/name "Collectibles Marketplace"
                 :wizard/logo "QmW2WQi7j6c7UgJTarActp7tDNikE4B2qXtFCfLPdsgaTQ"
                 :wizard/owner "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :wizard/description "abc"
                 :wizard/preview-images [{:file/hash "QmW2WQi7j6c7UgJTarActp7tDNikE4B2qXtFCfLPdsgaTQ"}]}))

  (is (s/valid? :district-designer/update-wizard
                {:event :district-designer/update-wizard
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :wizard/id "collectibles_marketplace"
                 :wizard/name "Collectibles Marketplace"}))

  (is (s/valid? :district-designer/remove-wizard
                {:event :district-designer/remove-wizard
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :wizard/id "collectibles_marketplace"}))

  (is (s/valid? :district-designer/add-theme
                {:event :district-designer/add-theme
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :theme/id "some_theme"
                 :theme/name "Some Theme"
                 :theme/owner "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :theme/description "abc"
                 :theme/default-settings "{:a 1 :b 2}"
                 :theme/files [{:file/hash "QmW2WQi7j6c7UgJTarActp7tDNikE4B2qXtFCfLPdsgaTQ"}]
                 :theme/preview-images [{:file/hash "QmW2WQi7j6c7UgJTarActp7tDNikE4B2qXtFCfLPdsgaTQ"}]}))

  (is (s/valid? :district-designer/update-theme
                {:event :district-designer/update-theme
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :theme/id "some_theme"
                 :theme/name "Some Theme"}))

  (is (s/valid? :district-designer/remove-theme
                {:event :district-designer/remove-theme
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :theme/id "some_theme"}))

  (is (s/valid? :district-designer/add-permission
                {:event :district-designer/add-permission
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :permission/id "some_permission"
                 :permission/name "Some permission"
                 :permission/description "abc"}))

  (is (s/valid? :district-designer/update-permission
                {:event :district-designer/update-permission
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :permission/id "some_permission"
                 :permission/name "Some permission"}))

  (is (s/valid? :district-designer/remove-permission
                {:event :district-designer/remove-permission
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :permission/id "some_permission"}))

  (is (s/valid? :district-designer/add-tag-group
                {:event :district-designer/add-tag-group
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :tag-group/uuid #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"
                 :tag-group/name "abc"
                 :tag-group/users-allowed-adding-tags? true
                 :tag-group/global-enabled? true}))

  (is (s/valid? :district-designer/add-tags
                {:event :district-designer/add-tag-group
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :tag-group/uuid #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"
                 :tag-group/tags ["abc" "def"]}))

  (is (s/valid? :district/update-theme
                {:event :district/update-theme
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :district/uuid #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"
                 :district/theme "some_theme"
                 :district/theme-settings "{:a 1 :b 2}"}))

  (is (s/valid? :district/update-styles
                {:event :district/update-styles
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :district/uuid #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"
                 :district/less-file {:file/hash "QmW2WQi7j6c7UgJTarActp7tDNikE4B2qXtFCfLPdsgaTQ"
                                      :file/name "abc"}
                 :district/css-file {:file/hash "QmW2WQi7j6c7UgJTarActp7tDNikE4B2qXtFCfLPdsgaTQ"
                                     :file/name "abc"}}))

  (is (s/valid? :district/add-module
                {:event :district/add-module
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :district/uuid #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"
                 :module/id "marketplace"}))

  (is (s/valid? :district/remove-module
                {:event :district/remove-module
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :district/uuid #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"
                 :module/id "marketplace"}))

  (is (s/valid? :district/add-ui-component
                {:event :district/add-ui-component
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :district/uuid #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"
                 :ui-component/uuid #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"
                 :ui-component/name "abc"
                 :ui-component/type "abc"
                 :ui-component/children [#uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"]
                 :ui-component/settings "<str>"
                 :ui-component/files [{:file/hash "QmW2WQi7j6c7UgJTarActp7tDNikE4B2qXtFCfLPdsgaTQ"
                                       :file/name "abc"}]}))

  (is (s/valid? :district/update-ui-component
                {:event :district/update-ui-component
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :district/uuid #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"
                 :ui-component/uuid #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"
                 :ui-component/name "abc"
                 :ui-component/type "abc"
                 :ui-component/children [#uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"]
                 :ui-component/settings "{:a 1 :b 2}"
                 :ui-component/files [{:file/hash "QmW2WQi7j6c7UgJTarActp7tDNikE4B2qXtFCfLPdsgaTQ"
                                       :file/name "abc"}]}))

  (is (s/valid? :district/remove-ui-component
                {:event :district/remove-ui-component
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :district/uuid #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"
                 :ui-component/uuid #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"})))


(deftest smart-contract-events-spec-tests
  (is (s/valid? :district-designer/district-initialized
                {:event :district-designer/district-initialized
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :district #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"
                 :permissions [{:permission-id "some_permission"
                                :user-role-ids [#uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"]}]
                 :user-roles [{:user-role-id #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"
                               :addresses ["0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"]}]
                 :admin-user-role-id #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"
                 :treasury "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :ipfs-data {:event :district-designer/district-initialized
                             :user-role-names [{:user-role/uuid #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"
                                                :user-role/name "Admins"}]
                             :wizard/id "some_wizard"
                             :some-key "random_value"}}))


  (is (s/valid? :district-designer/permissions-updated
                {:event :district-designer/permissions-updated
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :district #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"
                 :permissions [{:permission-id "some_permission"
                                :user-role-ids [#uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"]}]}))


  (is (s/valid? :district-designer/user-roles-updated
                {:event :district-designer/user-roles-updated
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :district #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"
                 :user-roles [{:user-role-id #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"
                               :addresses ["0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"]}]
                 :ipfs-data {:event :district-designer/user-roles-updated
                             :user-role-names [{:user-role/uuid #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"
                                                :user-role/name "Admins"}]}}))


  (is (s/valid? :district-designer/district-treasury-updated
                {:event :district-designer/district-treasury-updated
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :district #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"
                 :treasury "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"}))


  (is (s/valid? :district-designer/emergency-updated
                {:event :district-designer/emergency-updated
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :module-id "some_module"
                 :is-emergency true}))

  (is (s/valid? :dd-proxy-factory/proxy-target-updated
                {:event :dd-proxy-factory/proxy-target-updated
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :proxy "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :old-target "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :new-target "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :ipfs-abi "QmW2WQi7j6c7UgJTarActp7tDNikE4B2qXtFCfLPdsgaTQ"})))