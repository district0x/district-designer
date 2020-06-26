(ns server-tests.marketplace.spec-tests
  (:require
    [cljs.spec.alpha :as s]
    [cljs.test :refer-macros [deftest is testing run-tests use-fixtures async]]
    [marketplace.shared.spec.ipfs-events]
    [marketplace.shared.spec.smart-contract-events]))

(deftest ipfs-events-spec-tests
  (is (s/valid? :event/event
                {:event/type :marketplace/update-offer-group
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

  (is (s/valid? :event/event
                {:event/type :marketplace/add-district-offer-group
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :district/address "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :offer-group/address "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"}))

  (is (s/valid? :event/event
                {:event/type :marketplace/remove-district-offer-group
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :district/address "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :offer-group/address "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"}))

  (is (s/valid? :event/event
                {:event/type :marketplace/update-offer
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :offer/address "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"}))

  (is (s/valid? :event/event
                {:event/type :marketplace/add-message
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :offer-response/index 1
                 :message/uuid #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"
                 :message/receiver "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :message/text "abc"
                 :message/files [{:file/hash "QmW2WQi7j6c7UgJTarActp7tDNikE4B2qXtFCfLPdsgaTQ"
                                  :file/name "abc"}]}))

  (is (s/valid? :event/event
                {:event/type :marketplace/add-feedback
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :offer-response/index 0
                 :feedback/uuid #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"
                 :feedback/rating 4.5
                 :feedback/text "abc"})))


(deftest smart-contract-events-spec-tests
  (is (s/valid? :event/event
                {:event/type :marketplace/offer-group-created
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :district "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :offer-group "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :offer-group-target {:contract-address "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                                      :ipfs-abi "QmW2WQi7j6c7UgJTarActp7tDNikE4B2qXtFCfLPdsgaTQ"}
                 :offer-group-version 1
                 :offerable-assets [{:trade-asset-category :trade-asset-category/erc-20
                                     :token-address "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"}]
                 :requestable-assets [{:trade-asset-category :trade-asset-category/deliverable}]
                 :allowed-offer-types [:offer-type/deliverable-auction]
                 :permission-user-roles {:create-offer-user-roles [#uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"]
                                         :offer-response-user-roles [#uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"]
                                         :resolve-dispute-user-roles [#uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"]}
                 :ipfs-data {:offer-group/name "Some Name"
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


  (is (s/valid? :event/event
                {:event/type :marketplace/offer-group-updated
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :offer-group "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :offerable-assets [{:trade-asset-category :trade-asset-category/erc-20
                                     :token-address "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"}]
                 :requestable-assets [{:trade-asset-category :trade-asset-category/deliverable}]
                 :allowed-offer-types [:offer-type/deliverable-auction]
                 :permission-user-roles {:create-offer-user-roles [#uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"]
                                         :offer-response-user-roles [#uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"]
                                         :resolve-dispute-user-roles [#uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"]}
                 :ipfs-data {:offer-group/name "Some Name"}}))


  (is (s/valid? :event/event
                {:event/type :marketplace/offer-created
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :district "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :offer "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :offer-target {:contract-address "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                                :ipfs-abi "QmW2WQi7j6c7UgJTarActp7tDNikE4B2qXtFCfLPdsgaTQ"}
                 :offer-version 1
                 :offer-type :offer-type/higest-bid-auction
                 :offerer "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :offered-value {:trade-asset {:trade-asset-category :trade-asset-category/erc-20
                                               :token-address "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"}
                                 :erc20-value {:value 10}}
                 :offer-request {:highest-bid-auction-offer-request
                                 {:token-type :token-type/erc-20
                                  :token-address "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                                  :token-id 0
                                  :min-price 10
                                  :min-bid-step 2
                                  :duration 100
                                  :extension-trigger-duration 5
                                  :extension-duration 10}}
                 :available-supply {:trade-asset {:trade-asset-category :trade-asset-category/erc-20
                                                  :token-address "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"}
                                    :erc20-value {:value 10}}
                 :allowed-respondents ["0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"]
                 :ipfs-data {:offer/field-909659f5-560c-4640-9d67-7a1977da92b5 "Some"}}))


  (is (s/valid? :event/event
                {:event/type :marketplace/offer-response-created
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :district "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :offer "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :respondent "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :offer-response-index 0
                 :response-value {:trade-asset {:trade-asset-category :trade-asset-category/erc-1155
                                                :token-address "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"}
                                  :erc1155-value {:token-ids [1]
                                                  :values [10]}}
                 :offerer-received-value {:trade-asset {:trade-asset-category :trade-asset-category/erc-1155
                                                        :token-address "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"}
                                          :erc1155-value {:token-ids [1]
                                                          :values [10]}}
                 :respondent-received-value {:trade-asset {:trade-asset-category :trade-asset-category/erc-1155
                                                           :token-address "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"}
                                             :erc1155-value {:token-ids [1]
                                                             :values [10]}}
                 :available-supply {:trade-asset {:trade-asset-category :trade-asset-category/erc-1155
                                                  :token-address "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"}
                                    :erc1155-value {:token-ids [1]
                                                    :values [10]}}
                 :ipfs-data {:offer-response/field-909659f5-560c-4640-9d67-7a1977da92b5 "Some"}}))


  (is (s/valid? :event/event
                {:event/type :marketplace/offer-response-accepted
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :offer "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :offer-response-index 0
                 :offerer-received-value {:trade-asset {:trade-asset-category :trade-asset-category/erc-721
                                                        :token-address "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"}
                                          :erc721-value {:token-id 1}}
                 :respondent-received-value {:trade-asset {:trade-asset-category :trade-asset-category/erc-721
                                                           :token-address "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"}
                                             :erc721-value {:token-id 1}}
                 :available-supply {:trade-asset {:trade-asset-category :trade-asset-category/erc-721
                                                  :token-address "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"}
                                    :erc721-value {:token-id 1}}
                 :ipfs-data {:messages [{:message/uuid #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"
                                         :message/receiver "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                                         :message/text "abc"}]}}))


  (is (s/valid? :event/event
                {:event/type :marketplace/supply-withdrawn
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :offer "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"}))


  (is (s/valid? :event/event
                {:event/type :marketplace/offer-response-canceled
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :offer "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :offer-response-index 0}))


  (is (s/valid? :event/event
                {:event/type :marketplace/dispute-raised
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :offer "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :offer-response-index 0
                 :raised-by "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :ipfs-data {:messages [{:message/uuid #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"
                                         :message/receiver "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                                         :message/text "abc"}]}}))


  (is (s/valid? :event/event
                {:event/type :marketplace/dispute-resolved
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :offer "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :offer-response-index 0
                 :respondent-received-value {:trade-asset {:trade-asset-category :trade-asset-category/erc-721
                                                           :token-address "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"}
                                             :erc721-value {:token-id 1}}
                 :offerer-received-value {:trade-asset {:trade-asset-category :trade-asset-category/erc-721
                                                        :token-address "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"}
                                          :erc721-value {:token-id 2}}
                 :available-supply {:trade-asset {:trade-asset-category :trade-asset-category/erc-721
                                                  :token-address "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"}
                                    :erc721-value {:token-id 1}}
                 :resolved-by "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :ipfs-data {:messages [{:message/uuid #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"
                                         :message/receiver "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                                         :message/text "abc"}]}}))


  (is (s/valid? :event/event
                {:event/type :marketplace/sponsorship-added
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :offer "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :sponsor "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :sponsorship {:trade-asset {:trade-asset-category :trade-asset-category/erc-721
                                             :token-address "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"}
                               :erc721-value {:token-id 1}}
                 :available-supply {:trade-asset {:trade-asset-category :trade-asset-category/erc-721
                                                  :token-address "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"}
                                    :erc721-value {:token-id 1}}}))


  (is (s/valid? :event/event
                {:event/type :marketplace/sponsorship-withdrawn
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :offer "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :sponsor "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :withdrawal {:trade-asset {:trade-asset-category :trade-asset-category/erc-721
                                            :token-address "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"}
                              :erc721-value {:token-id 1}}
                 :available-supply {:trade-asset {:trade-asset-category :trade-asset-category/erc-721
                                                  :token-address "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"}
                                    :erc721-value {:token-id 1}}}))


  (is (s/valid? :event/event
                {:event/type :marketplace/proxy-targets-updated
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :offer-group-target {:contract-address "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                                      :ipfs-abi "QmW2WQi7j6c7UgJTarActp7tDNikE4B2qXtFCfLPdsgaTQ"}
                 :fixed-prices-offer-target {:contract-address "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                                             :ipfs-abi "QmW2WQi7j6c7UgJTarActp7tDNikE4B2qXtFCfLPdsgaTQ"}
                 :dynamic-price-offer-target {:contract-address "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                                              :ipfs-abi "QmW2WQi7j6c7UgJTarActp7tDNikE4B2qXtFCfLPdsgaTQ"}
                 :highest-bid-auction-offer-target {:contract-address "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                                                    :ipfs-abi "QmW2WQi7j6c7UgJTarActp7tDNikE4B2qXtFCfLPdsgaTQ"}
                 :multi-token-auction-offer-target {:contract-address "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                                                    :ipfs-abi "QmW2WQi7j6c7UgJTarActp7tDNikE4B2qXtFCfLPdsgaTQ"}
                 :deliverable-auction-offer-target {:contract-address "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                                                    :ipfs-abi "QmW2WQi7j6c7UgJTarActp7tDNikE4B2qXtFCfLPdsgaTQ"}}))


  (is (s/valid? :event/event
                {:event/type :marketplace/offer-group-base-contracts-updated
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :offer-group "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :fixed-prices-offer-target {:contract-address "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                                             :ipfs-abi "QmW2WQi7j6c7UgJTarActp7tDNikE4B2qXtFCfLPdsgaTQ"}
                 :dynamic-price-offer-target {:contract-address "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                                              :ipfs-abi "QmW2WQi7j6c7UgJTarActp7tDNikE4B2qXtFCfLPdsgaTQ"}
                 :highest-bid-auction-offer-target {:contract-address "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                                                    :ipfs-abi "QmW2WQi7j6c7UgJTarActp7tDNikE4B2qXtFCfLPdsgaTQ"}
                 :multi-token-auction-offer-target {:contract-address "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                                                    :ipfs-abi "QmW2WQi7j6c7UgJTarActp7tDNikE4B2qXtFCfLPdsgaTQ"}
                 :deliverable-auction-offer-target {:contract-address "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                                                    :ipfs-abi "QmW2WQi7j6c7UgJTarActp7tDNikE4B2qXtFCfLPdsgaTQ"}})))
