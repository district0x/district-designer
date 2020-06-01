(ns tokens.shared.spec.ipfs-events
  (:require
    [cljs.spec.alpha :as s]
    [district-designer.shared.spec.ipfs-events :refer [ipfs-hash? address? edn?]]))

(def ipfs-events
  #{:tokens/update-token-contract
    :tokens/remove-token-contract
    :tokens/add-district-token-contract
    :tokens/remove-district-token-contract
    :tokens/add-token-contract-misconfig-report
    :tokens/resolve-token-contract-misconfig-report})

(s/def :token-contract/uuid uuid?)
(s/def :token-contract/metadata-format string?)
(s/def :token-contract/metadata-format-settings edn?)
(s/def :token-contract/reported-misconfig-comment string?)

(s/def :tokens/add-erc20-token-factory :district-designer/add-smart-contract)
(s/def :tokens/add-erc721-token-factory :district-designer/add-smart-contract)
(s/def :tokens/add-erc1155-token-factory :district-designer/add-smart-contract)
(s/def :tokens/add-token-factory-events :district-designer/add-smart-contract)

(s/def :tokens/update-token-contract
  (s/merge
    :district-designer.shared.spec.ipfs-events/event-base
    (s/keys :req [:token-contract/uuid]
            :opt [:token-contract/metadata-format
                  :token-contract/metadata-format-settings])))


(s/def :tokens/remove-token-contract
  (s/merge
    :district-designer.shared.spec.ipfs-events/event-base
    (s/keys :req [:token-contract/uuid])))


(s/def :tokens/add-district-token-contract
  (s/merge
    :district-designer.shared.spec.ipfs-events/event-base
    (s/keys :req [:district/uuid
                  :token-contract/uuid])))


(s/def :tokens/remove-district-token-contract :tokens/add-district-token-contract)


(s/def :tokens/add-token-contract-misconfig-report
  (s/merge
    :district-designer.shared.spec.ipfs-events/event-base
    (s/keys :req [:token-contract/uuid
                  :token-contract/reported-misconfig-comment])))


(s/def :tokens/resolve-token-contract-misconfig-report
  (s/merge
    :district-designer.shared.spec.ipfs-events/event-base
    (s/keys :req [:token-contract/uuid])))