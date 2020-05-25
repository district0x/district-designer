(ns tokens.shared.spec
  (:require
    [cljs.spec.alpha :as s]
    [district-designer.shared.spec :refer [ipfs-hash? address? edn?]]))

(s/def :token-contract/uuid uuid?)
(s/def :token-contract/metadata-format string?)
(s/def :token-contract/metadata-format-settings string?)
(s/def :token-contract/reported-misconfig-comment string?)

(s/def :tokens/add-erc20-token-factory :district-designer/add-dd-proxy-factory)
(s/def :tokens/add-erc721-token-factory :district-designer/add-dd-proxy-factory)
(s/def :tokens/add-erc1155-token-factory :district-designer/add-dd-proxy-factory)
(s/def :tokens/add-token-factory-events :district-designer/add-dd-proxy-factory)

(s/def :tokens/update-token-contract
  (s/keys :req-un [:district-designer.server.spec/event
                   :district-designer.server.spec/sender]
          :req [:token-contract/uuid]
          :opt [:token-contract/metadata-format
                :token-contract/metadata-format-settings]))


(s/def :tokens/remove-token-contract
  (s/keys :req-un [:district-designer.server.spec/event
                   :district-designer.server.spec/sender]
          :req [:token-contract/uuid]))


(s/def :tokens/add-district-token-contract
  (s/keys :req-un [:district-designer.server.spec/event
                   :district-designer.server.spec/sender]
          :req [:district/uuid
                :token-contract/uuid]))


(s/def :tokens/remove-district-token-contract :tokens/add-district-token-contract)


(s/def :tokens/add-token-contract-misconfig-report
  (s/keys :req-un [:district-designer.server.spec/event
                   :district-designer.server.spec/sender]
          :req [:token-contract/uuid
                :token-contract/reported-misconfig-comment]))


(s/def :tokens/resolve-token-contract-misconfig-report
  (s/keys :req-un [:district-designer.server.spec/event
                   :district-designer.server.spec/sender]
          :req [:token-contract/uuid]))




(comment
  (s/valid? :tokens/add-erc20-token-factory
            {:event :tokens/add-erc20-token-factory
             :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
             :smart-contract/address "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
             :smart-contract/abi {:file/hash "QmW2WQi7j6c7UgJTarActp7tDNikE4B2qXtFCfLPdsgaTQ"
                                  :file/name "abc"}})


  (s/valid? :tokens/update-token-contract
            {:event :tokens/update-token-contract
             :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
             :token-contract/uuid #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"
             :token-contract/metadata-format "abc"
             :token-contract/metadata-format-settings "{:a 1 :b 2}"})


  (s/valid? :tokens/remove-token-contract
            {:event :tokens/remove-token-contract
             :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
             :token-contract/uuid #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"})


  (s/valid? :tokens/add-district-token-contract
            {:event :tokens/remove-token-contract
             :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
             :token-contract/uuid #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"
             :district/uuid #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"})


  (s/valid? :tokens/remove-district-token-contract
            {:event :tokens/remove-token-contract
             :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
             :token-contract/uuid #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"
             :district/uuid #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"})


  (s/valid? :tokens/add-token-contract-misconfig-report
            {:event :tokens/add-token-contract-misconfig-report
             :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
             :token-contract/uuid #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"
             :token-contract/reported-misconfig-comment "abc"})


  (s/valid? :tokens/resolve-token-contract-misconfig-report
            {:event :tokens/resolve-token-contract-misconfig-report
             :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
             :token-contract/uuid #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"})

  )