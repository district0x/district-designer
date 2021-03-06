(ns server-tests.tokens.spec-tests
  (:require
    [cljs.spec.alpha :as s]
    [cljs.test :refer-macros [deftest is testing run-tests use-fixtures async]]
    [tokens.shared.spec.ipfs-events]
    [tokens.shared.spec.smart-contract-events]))


(deftest ipfs-events-spec-tests
  (is (s/valid? :event/event
                {:event/type :tokens/add-token-factory
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :smart-contract/address "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :smart-contract/abi {:file/hash "QmW2WQi7j6c7UgJTarActp7tDNikE4B2qXtFCfLPdsgaTQ"
                                      :file/name "abc"}}))

  (is (s/valid? :event/event
                {:event/type :tokens/update-token-contract
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :token-contract/address "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :token-contract/fields [{:field/uuid #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"
                                          :field/name "abc"
                                          :field/namespace "abc"
                                          :field/type "abc"
                                          :field/settings "{:a 1 :b 2}"}]
                 :token-contract/metadata-format "abc"
                 :token-contract/metadata-format-settings "{:a 1 :b 2}"}))

  (is (s/valid? :event/event
                {:event/type :tokens/remove-token-contract
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :token-contract/address "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"}))

  (is (s/valid? :event/event
                {:event/type :tokens/remove-token-contract
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :token-contract/address "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :district/address "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"}))

  (is (s/valid? :event/event
                {:event/type :tokens/remove-token-contract
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :token-contract/address "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :district/address "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"}))

  (is (s/valid? :event/event
                {:event/type :tokens/add-token-contract-misconfig-report
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :token-contract/address "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :token-contract/reported-misconfig-comment "abc"}))

  (is (s/valid? :event/event
                {:event/type :tokens/resolve-token-contract-misconfig-report
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :token-contract/address "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"})))


(deftest smart-contract-events-spec-tests
  (is (s/valid? :event/event
                {:event/type :tokens/token-created
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :district "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :token "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :token-type :erc20
                 :token-ipfs-abi "QmW2WQi7j6c7UgJTarActp7tDNikE4B2qXtFCfLPdsgaTQ"
                 :token-version 1
                 :token-name "Some Name"
                 :token-symbol "SOME"
                 :base-uri "Some"
                 :decimal-units 18
                 :owner "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :ipfs-data {:token-contract/fields [{:field/uuid #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"
                                                      :field/name "abc"
                                                      :field/namespace "abc"
                                                      :field/type "abc"
                                                      :field/settings "{:a 1 :b 2}"}]
                             :token-contract/metadata-format "abc"
                             :token-contract/metadata-format-settings "{:a 1 :b 2}"}}))

  (is (s/valid? :event/event
                {:event/type :tokens/factory-updated
                 :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :timestamp 1590913803
                 :new-factory "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
                 :token-type :erc1155})))