(ns server-tests.marketplace.spec-tests
  (:require
    [cljs.spec.alpha :as s]
    [cljs.test :refer-macros [deftest is testing run-tests use-fixtures async]]
    [marketplace.shared.spec.ipfs-events]
    [marketplace.shared.spec.smart-contract-events]))

(deftest ipfs-events-spec-tests
  (is (s/valid? :marketplace/update-offer-group
                {:event :marketplace/update-offer-group
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :offer-group/address "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :offer-group/name "abc"
                 :offer-group/offer-field-configs [{:field-config/uuid #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"
                                                    :field-config/name "abc"
                                                    :field-config/namespace "abc"
                                                    :field-config/type "abc"
                                                    :field-config/settings "{:a 1 :b 2}"}]

                 :offer-group/response-field-configs [{:field-config/uuid #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"
                                                       :field-config/name "abc"
                                                       :field-config/namespace "abc"
                                                       :field-config/type "abc"
                                                       :field-config/settings "{:a 1 :b 2}"}]

                 :offer-group/global-enabled? true
                 :offer-group/global-logo {:file/hash "QmW2WQi7j6c7UgJTarActp7tDNikE4B2qXtFCfLPdsgaTQ"
                                           :file/name "abc"}
                 :offer-group/global-description "abc"}))

  (is (s/valid? :marketplace/add-district-offer-group
                {:event :marketplace/add-district-offer-group
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :district/uuid #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"
                 :offer-group/address "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"}))

  (is (s/valid? :marketplace/remove-district-offer-group
                {:event :marketplace/remove-district-offer-group
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :district/uuid #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"
                 :offer-group/address "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"}))

  (is (s/valid? :marketplace/update-offer
                {:event :marketplace/update-offer
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :offer/address "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"}))

  (is (s/valid? :marketplace/add-message
                {:event :marketplace/add-message
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :offer-response/uuid #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"
                 :message/uuid #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"
                 :message/receiver "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :message/text "abc"
                 :message/files [{:file/hash "QmW2WQi7j6c7UgJTarActp7tDNikE4B2qXtFCfLPdsgaTQ"
                                  :file/name "abc"}]}))

  (is (s/valid? :marketplace/add-feedback
                {:event :marketplace/add-feedback
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :offer-response/uuid #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"
                 :feedback/uuid #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"
                 :feedback/rating 4.5
                 :feedback/text "abc"})))


(deftest smart-contract-events-spec-tests
  (is (s/valid? :marketplace/offer-group-created
                {:event :marketplace/offer-group-created
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :district #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"
                 :offer-group "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :offer-group-base-contract "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :offer-group-ipfs-abi "QmW2WQi7j6c7UgJTarActp7tDNikE4B2qXtFCfLPdsgaTQ"
                 :offer-group-version 1
                 :assets-to-offer [{:trade-asset-category :trade-asset-category/erc-20
                                    :token-address "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"}]
                 :assets-to-request [{:trade-asset-category :trade-asset-category/deliverable}]
                 :offer-type :offer-type/offerer-picks-winner
                 :fees {:create-offer-fee 1
                        :offer-response-fee 0}
                 :permission-user-roles {:create-offer-user-roles [#uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"]
                                         :offer-response-user-roles [#uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"]
                                         :resolve-dispute-user-roles [#uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"]}
                 :allow-multiple-trades true
                 :ipfs-data {:event :marketplace/offer-group-created
                             :offer-group/name "Some Name"
                             :offer-group/offer-field-configs [{:field-config/uuid #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"
                                                                :field-config/name "Some Field"
                                                                :field-config/namespace "Marketplace"
                                                                :field-config/type "Text"
                                                                :field-config/settings "{:a 1 :b 2}"}]
                             :offer-group/response-field-configs [{:field-config/uuid #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"
                                                                   :field-config/name "Some Field"
                                                                   :field-config/namespace "Marketplace"
                                                                   :field-config/type "Text"
                                                                   :field-config/settings "{:a 1 :b 2}"}]
                             :offer-group/global-enabled? true
                             :offer-group/global-logo {:file/hash "QmW2WQi7j6c7UgJTarActp7tDNikE4B2qXtFCfLPdsgaTQ"}
                             :offer-group/global-description "Some"}}))


  (is (s/valid? :marketplace/offer-group-updated
                {:event :marketplace/offer-group-updated
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :offer-group "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :added-assets-to-offer [{:trade-asset-category :trade-asset-category/erc-20
                                          :token-address "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"}]
                 :added-assets-to-request [{:trade-asset-category :trade-asset-category/deliverable}]
                 :fees {:create-offer-fee 1
                        :offer-response-fee 0}
                 :permission-user-roles {:create-offer-user-roles [#uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"]
                                         :offer-response-user-roles [#uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"]
                                         :resolve-dispute-user-roles [#uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"]}
                 :ipfs-data {:event :marketplace/offer-group-updated
                             :offer-group/name "Some Name"}}))


  (is (s/valid? :marketplace/offer-created
                {:event :marketplace/offer-created
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :district #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"
                 :offer "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :offer-base-contract "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :offer-ipfs-abi "QmW2WQi7j6c7UgJTarActp7tDNikE4B2qXtFCfLPdsgaTQ"
                 :offer-version 1
                 :offerer "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :offered-value {:trade-asset-category :trade-asset-category/erc-20
                                 :token-address "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                                 :token-amount 10}
                 :offer-type :offer-type/offerer-picks-winner
                 :requested-values [{:trade-asset-category :trade-asset-category/erc-1155
                                     :token-address "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                                     :token-amount 1
                                     :token-id 12}]
                 :requested-auction {:trade-asset {:trade-asset-category :trade-asset-category/erc-1155
                                                   :token-address "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"}
                                     :trade-auction/start-amount 1
                                     :trade-auction/end-amount 10}
                 :ipfs-data {:event :marketplace/offer-created
                             :offer/field-909659f5-560c-4640-9d67-7a1977da92b5 "Some"}}))


  (is (s/valid? :marketplace/offer-response-created
                {:event :marketplace/offer-response-created
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :district #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"
                 :offer "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :respondent "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :offer-response #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"
                 :offerer-traded-value {:trade-asset-category :trade-asset-category/erc-1155
                                        :token-address "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                                        :token-amount 1
                                        :token-id 12}
                 :respondent-traded-value {:trade-asset-category :trade-asset-category/erc-20
                                           :token-address "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                                           :token-id 12}
                 :ipfs-data {:event :marketplace/offer-response-created
                             :offer-response/field-909659f5-560c-4640-9d67-7a1977da92b5 "Some"}}))


  (is (s/valid? :marketplace/offer-response-accepted
                {:event :marketplace/offer-response-accepted
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :offer-response #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"
                 :offerer-traded-value {:trade-asset-category :trade-asset-category/erc-1155
                                        :token-address "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                                        :token-amount 1
                                        :token-id 12}
                 :respondent-traded-value {:trade-asset-category :trade-asset-category/erc-20
                                           :token-address "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                                           :token-id 12}}))


  (is (s/valid? :marketplace/offer-closed
                {:event :marketplace/offer-closed
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :offer "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"}))


  (is (s/valid? :marketplace/offer-response-canceled
                {:event :marketplace/offer-response-canceled
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :offer-response #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"}))


  (is (s/valid? :marketplace/dispute-raised
                {:event :marketplace/dispute-raised
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :offer-response #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"
                 :raised-by "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"}))


  (is (s/valid? :marketplace/dispute-resolved
                {:event :marketplace/dispute-raised
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :offer-response #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"
                 :value-for-offerer {:trade-asset-category :trade-asset-category/erc-20
                                     :token-address "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                                     :token-id 12}
                 :value-for-respondent {:trade-asset-category :trade-asset-category/erc-20
                                        :token-address "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                                        :token-id 12}
                 :resolved-by "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"}))


  (is (s/valid? :marketplace/base-contracts-updated
                {:event :marketplace/base-contracts-updated
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :offer-group-base-contract "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :offer-group-ipfs-abi "QmW2WQi7j6c7UgJTarActp7tDNikE4B2qXtFCfLPdsgaTQ"
                 :offer-base-contract "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :offer-ipfs-abi "QmW2WQi7j6c7UgJTarActp7tDNikE4B2qXtFCfLPdsgaTQ"}))


  (is (s/valid? :marketplace/offer-group-base-contracts-updated
                {:event :marketplace/offer-group-base-contracts-updated
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :offer-group "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :offer-base-contract "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :offer-ipfs-abi "QmW2WQi7j6c7UgJTarActp7tDNikE4B2qXtFCfLPdsgaTQ"})))