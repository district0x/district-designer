(ns tokens.shared.spec.ipfs-events
  (:require
    [cljs.spec.alpha :as s]
    [district-designer.shared.spec.ipfs-events :refer [ipfs-hash? address? edn? event-type]]))

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

(defmethod event-type :tokens/add-erc20-token-factory [_]
  :district-designer/add-smart-contract)

(defmethod event-type :tokens/add-erc721-token-factory [_]
  :district-designer/add-smart-contract)

(defmethod event-type :tokens/add-erc1155-token-factory [_]
  :district-designer/add-smart-contract)

(defmethod event-type :tokens/add-token-factory-events [_]
  :district-designer/add-smart-contract)

(defmethod event-type :tokens/update-token-contract [_]
  (s/merge
    :district-designer.shared.spec.ipfs-events/event-base
    (s/keys :req [:token-contract/uuid]
            :opt [:token-contract/metadata-format
                  :token-contract/metadata-format-settings])))


(defmethod event-type :tokens/remove-token-contract [_]
  (s/merge
    :district-designer.shared.spec.ipfs-events/event-base
    (s/keys :req [:token-contract/uuid])))


(defmethod event-type :tokens/add-district-token-contract [_]
  (s/merge
    :district-designer.shared.spec.ipfs-events/event-base
    (s/keys :req [:district/uuid
                  :token-contract/uuid])))


(defmethod event-type :tokens/remove-district-token-contract [_]
  :tokens/add-district-token-contract)


(defmethod event-type :tokens/add-token-contract-misconfig-report [_]
  (s/merge
    :district-designer.shared.spec.ipfs-events/event-base
    (s/keys :req [:token-contract/uuid
                  :token-contract/reported-misconfig-comment])))


(defmethod event-type :tokens/resolve-token-contract-misconfig-report [_]
  (s/merge
    :district-designer.shared.spec.ipfs-events/event-base
    (s/keys :req [:token-contract/uuid])))