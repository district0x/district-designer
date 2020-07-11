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
                 :offer-group/offer-fields [{:field/uuid #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"
                                             :field/name "abc"
                                             :field/namespace "abc"
                                             :field/type "abc"
                                             :field/settings "{:a 1 :b 2}"}]

                 :offer-group/response-fields [{:field/uuid #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"
                                                :field/name "abc"
                                                :field/namespace "abc"
                                                :field/type "abc"
                                                :field/settings "{:a 1 :b 2}"}]

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
                 :offer/address "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :offer/field-values [{:field-value/uuid #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"
                                       :field/uuid #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"
                                       :field-value/type :string
                                       :field-value/value "Some Name"}]}))

  (is (s/valid? :event/event
                {:event/type :marketplace/add-offer-response-message
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :offer/address "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
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
                 :offer/address "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
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
                 :offerable-token-contracts [{:token-type :erc20
                                              :token-address "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"}]
                 :requestable-token-contracts [{:token-type :deliverable
                                                :token-address "0xABCDabcdABcDabcDaBCDAbcdABcdAbCdABcDABCd"}]
                 :allowed-offer-types [:deliverable-auction]
                 :permission-user-roles {:create-offer-user-roles [#uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"]
                                         :offer-response-user-roles [#uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"]
                                         :resolve-dispute-user-roles [#uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"]}
                 :ipfs-data {:offer-group/name "Some Name"
                             :offer-group/offer-fields [{:field/uuid #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"
                                                         :field/name "Some Field"
                                                         :field/namespace "Marketplace"
                                                         :field/type "Text"
                                                         :field/settings "{:a 1 :b 2}"}]
                             :offer-group/response-fields [{:field/uuid #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"
                                                            :field/name "Some Field"
                                                            :field/namespace "Marketplace"
                                                            :field/type "Text"
                                                            :field/settings "{:a 1 :b 2}"}]
                             :offer-group/global-enabled? true
                             :offer-group/global-logo {:file/hash "QmW2WQi7j6c7UgJTarActp7tDNikE4B2qXtFCfLPdsgaTQ"}
                             :offer-group/global-description "Some"}}))


  (is (s/valid? :event/event
                {:event/type :marketplace/offer-group-updated
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :offer-group "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :offerable-token-contracts [{:token-type :erc20
                                              :token-address "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"}]
                 :requestable-token-contracts [{:token-type :deliverable
                                                :token-address "0xABCDabcdABcDabcDaBCDAbcdABcdAbCdABcDABCd"}]
                 :allowed-offer-types [:deliverable-auction]
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
                 :offer-type :higest-bid-auction
                 :offerer "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :offered-values [{:token {:token-contract
                                           {:token-type :erc20
                                            :token-address "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"}
                                           :token-id 0}
                                   :value 10}]
                 :offer-request {:highest-bid-auction-offer-request
                                 {:token {:token-contract
                                          {:token-type :erc20
                                           :token-address "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"}
                                          :token-id 0}
                                  :min-price 10
                                  :min-bid-step 2
                                  :duration 100
                                  :extension-trigger-duration 5
                                  :extension-duration 10}}
                 :available-values [{:token {:token-contract
                                             {:token-type :erc20
                                              :token-address "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"}
                                             :token-id 0}
                                     :value 10}]
                 :allowed-respondents ["0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"]
                 :ipfs-data {:offer/field-values [{:field-value/uuid #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"
                                                   :field/uuid #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"
                                                   :field-value/type :string
                                                   :field-value/value "Some Name"}]}}))


  (is (s/valid? :event/event
                {:event/type :marketplace/offer-response-created
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :district "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :offer "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :respondent "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :offer-response-index 0
                 :response-values [{:token {:token-contract
                                            {:token-type :erc1155
                                             :token-address "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"}
                                            :token-id 1}
                                    :value 1}
                                   {:token {:token-contract
                                            {:token-type :erc1155
                                             :token-address "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"}
                                            :token-id 2}
                                    :value 4}]
                 :offerer-received-values [{:token {:token-contract
                                                    {:token-type :erc20
                                                     :token-address "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"}
                                                    :token-id 0}
                                            :value 10}]
                 :respondent-received-values [{:token {:token-contract
                                                       {:token-type :erc20
                                                        :token-address "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"}
                                                       :token-id 0}
                                               :value 10}]
                 :available-values [{:token {:token-contract
                                             {:token-type :erc20
                                              :token-address "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"}
                                             :token-id 0}
                                     :value 10}]
                 :ipfs-data {:offer-response/field-values [{:field-value/uuid #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"
                                                            :field/uuid #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"
                                                            :field-value/type :string
                                                            :field-value/value "Some Name"}]}}))


  (is (s/valid? :event/event
                {:event/type :marketplace/offer-response-accepted
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :offer "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :offer-response-index 0
                 :offerer-received-values [{:token {:token-contract
                                                    {:token-type :erc721
                                                     :token-address "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"}
                                                    :token-id 9}
                                            :value 1}]
                 :respondent-received-values [{:token {:token-contract
                                                       {:token-type :erc721
                                                        :token-address "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"}
                                                       :token-id 9}
                                               :value 1}]
                 :available-values [{:token {:token-contract
                                             {:token-type :erc721
                                              :token-address "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"}
                                             :token-id 9}
                                     :value 1}]
                 :ipfs-data {:offer-response/messages
                             [{:message/uuid #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"
                               :message/receiver "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                               :message/text "abc"}]}}))


  (is (s/valid? :event/event
                {:event/type :marketplace/available-values-withdrawn
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :offer "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :withdrawn-values [{:token {:token-contract
                                             {:token-type :erc721
                                              :token-address "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"}
                                             :token-id 9}
                                     :value 1}]}))


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
                 :ipfs-data {:offer-response/messages
                             [{:message/uuid #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"
                               :message/receiver "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                               :message/text "abc"}]}}))


  (is (s/valid? :event/event
                {:event/type :marketplace/dispute-resolved
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :offer "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :offer-response-index 0
                 :respondent-received-values [{:token {:token-contract
                                                       {:token-type :erc721
                                                        :token-address "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"}
                                                       :token-id 9}
                                               :value 1}]
                 :offerer-received-values [{:token {:token-contract
                                                    {:token-type :erc721
                                                     :token-address "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"}
                                                    :token-id 9}
                                            :value 1}]
                 :available-values [{:token {:token-contract
                                             {:token-type :erc721
                                              :token-address "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"}
                                             :token-id 9}
                                     :value 1}]
                 :resolved-by "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :ipfs-data {:offer-response/messages
                             [{:message/uuid #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"
                               :message/receiver "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                               :message/text "abc"}]}}))


  (is (s/valid? :event/event
                {:event/type :marketplace/sponsorship-added
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :offer "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :sponsor "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :sponsored-values [{:token {:token-contract
                                             {:token-type :erc721
                                              :token-address "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"}
                                             :token-id 9}
                                     :value 1}]
                 :available-values [{:token {:token-contract
                                             {:token-type :erc721
                                              :token-address "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"}
                                             :token-id 9}
                                     :value 1}]}))


  (is (s/valid? :event/event
                {:event/type :marketplace/sponsorship-withdrawn
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :offer "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :sponsor "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :withdrawn-values [{:token {:token-contract
                                             {:token-type :erc721
                                              :token-address "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"}
                                             :token-id 9}
                                     :value 1}]
                 :available-values [{:token {:token-contract
                                             {:token-type :erc721
                                              :token-address "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"}
                                             :token-id 9}
                                     :value 1}]}))


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
                {:event/type :marketplace/offer-group-proxy-targets-updated
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
