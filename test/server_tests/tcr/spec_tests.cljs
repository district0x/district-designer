(ns server-tests.tcr.spec-tests
  (:require
    [cljs.spec.alpha :as s]
    [cljs.test :refer-macros [deftest is testing run-tests use-fixtures async]]
    [tcr.shared.spec.ipfs-events]
    [tcr.shared.spec.smart-contract-events]))


(deftest ipfs-events-spec-tests
  (is (s/valid? :event/event
                {:event/type :tcr/update-tcr
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :tcr/address "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :tcr/name "abc"
                 :tcr/reg-entry-field-configs [{:field-config/uuid #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"
                                                :field-config/name "abc"
                                                :field-config/namespace "abc"
                                                :field-config/type "abc"
                                                :field-config/settings "{:a 1 :b 2}"}]
                 :tcr/global-enabled? true
                 :tcr/global-logo {:file/hash "QmW2WQi7j6c7UgJTarActp7tDNikE4B2qXtFCfLPdsgaTQ"
                                   :file/name "abc"}
                 :tcr/global-description "abc"}))

  (is (s/valid? :event/event
                {:event/type :tcr/add-district-tcr
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :district/address "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :tcr/address "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"})))


(deftest smart-contract-events-spec-tests
  (is (s/valid? :event/event
                {:event/type :tcr/tcr-created
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :district "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :tcr "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :tcr-target {:contract-address "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                              :ipfs-abi "QmW2WQi7j6c7UgJTarActp7tDNikE4B2qXtFCfLPdsgaTQ"}
                 :tcr-version 1
                 :voting-token {:voting-token-type :voting-token-type/erc-1155
                                :token-address "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                                :token-id 15}
                 :tcr-type :tcr-type/challengeable-anytime
                 :reg-entry-representation {:category :tcr-reg-entry-representation-category/erc-721
                                            :token-name "Name"
                                            :token-symbol "Symbol"
                                            :base-metadata-uri "uri"}
                 :permission-user-roles {:create-reg-entry-user-roles [#uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"]
                                         :create-param-change-entry-user-roles [#uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"]}
                 :reg-entry-parameters {:challenge-dispensation 1
                                        :challenge-period-duration 1
                                        :deposit 1
                                        :vote-commit-period-duration 1
                                        :vote-quorum 1
                                        :vote-reveal-period-duration 1}
                 :param-change-entry-parameters {:challenge-dispensation 1
                                                 :challenge-period-duration 1
                                                 :deposit 1
                                                 :vote-commit-period-duration 1
                                                 :vote-quorum 1
                                                 :vote-reveal-period-duration 1}
                 :ipfs-data {:event/type :tcr/tcr-created
                             :tcr/name "Some Name"
                             :tcr/reg-entry-field-configs [{:field-config/uuid #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"
                                                            :field-config/name "Some Field"
                                                            :field-config/namespace "Marketplace"
                                                            :field-config/type "Text"
                                                            :field-config/settings "{:a 1 :b 2}"}]

                             :tcr/global-enabled? true
                             :tcr/global-logo {:file/hash "QmW2WQi7j6c7UgJTarActp7tDNikE4B2qXtFCfLPdsgaTQ"}
                             :tcr/global-description "Some"}}))


  (is (s/valid? :event/event
                {:event/type :tcr/registry-entry-created
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :tcr "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :reg-entry "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :reg-entry-target {:contract-address "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                                    :ipfs-abi "QmW2WQi7j6c7UgJTarActp7tDNikE4B2qXtFCfLPdsgaTQ"}
                 :reg-entry-version 1
                 :creator "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :token-amount 1
                 :token-meta-ipfs-data "QmW2WQi7j6c7UgJTarActp7tDNikE4B2qXtFCfLPdsgaTQ"
                 :ipfs-data {:reg-entry/field-909659f5-560c-4640-9d67-7a1977da92b5 "Some"}}))


  (is (s/valid? :event/event
                {:event/type :tcr/param-change-entry-created
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :tcr "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :param-change-entry "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :param-change-entry-target {:contract-address "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                                             :ipfs-abi "QmW2WQi7j6c7UgJTarActp7tDNikE4B2qXtFCfLPdsgaTQ"}
                 :param-change-entry-version 1
                 :creator "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :entries-group :entries-group/registry-entries
                 :parameter-key "deposit"
                 :parameter-value 10
                 :original-parameter-value 1
                 :ipfs-data {:param-change-entry/comment "some"}}))


  (is (s/valid? :event/event
                {:event/type :tcr/challenge-created
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :entry "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :entries-group :entries-group/registry-entries
                 :challenge-index 0
                 :challenger "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :commit-period-end 1
                 :reveal-period-end 1
                 :reward-pool 1
                 :ipfs-data {:challenge/comment "some"}}))


  (is (s/valid? :event/event
                {:event/type :tcr/registry-entry-token-minted
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :reg-entry "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :token-id 1}))


  (is (s/valid? :event/event
                {:event/type :tcr/param-change-entry-applied
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :param-change-entry "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"}))


  (is (s/valid? :event/event
                {:event/type :tcr/challenger-reward-claimed
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :entry "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :entries-group :entries-group/param-change-entries
                 :challenger "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :amount 1}))


  (is (s/valid? :event/event
                {:event/type :tcr/creator-reward-claimed
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :entry "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :entries-group :entries-group/param-change-entries
                 :creator "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :amount 1}))


  (is (s/valid? :event/event
                {:event/type :tcr/votes-reclaimed
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :entry "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :entries-group :entries-group/param-change-entries
                 :voter "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :amount 1}))


  (is (s/valid? :event/event
                {:event/type :tcr/vote-committed
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :entry "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :entries-group :entries-group/param-change-entries
                 :voter "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :amount 1}))


  (is (s/valid? :event/event
                {:event/type :tcr/vote-revealed
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :entry "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :entries-group :entries-group/param-change-entries
                 :voter "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :vote-option :tcr-vote-option/include
                 :amount 1}))


  (is (s/valid? :event/event
                {:event/type :tcr/vote-reward-claimed
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :entry "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :entries-group :entries-group/param-change-entries
                 :voter "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :amount 1}))


  (is (s/valid? :event/event
                {:event/type :tcr/proxy-targets-updated
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :tcr-target {:contract-address "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                              :ipfs-abi "QmW2WQi7j6c7UgJTarActp7tDNikE4B2qXtFCfLPdsgaTQ"}
                 :reg-entry-target {:contract-address "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                                    :ipfs-abi "QmW2WQi7j6c7UgJTarActp7tDNikE4B2qXtFCfLPdsgaTQ"}
                 :param-change-entry-target {:contract-address "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                                             :ipfs-abi "QmW2WQi7j6c7UgJTarActp7tDNikE4B2qXtFCfLPdsgaTQ"}}))


  (is (s/valid? :event/event
                {:event/type :tcr/tcr-proxy-targets-updated
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :tcr "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :reg-entry-target {:contract-address "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                                    :ipfs-abi "QmW2WQi7j6c7UgJTarActp7tDNikE4B2qXtFCfLPdsgaTQ"}
                 :param-change-entry-target {:contract-address "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                                             :ipfs-abi "QmW2WQi7j6c7UgJTarActp7tDNikE4B2qXtFCfLPdsgaTQ"}})))