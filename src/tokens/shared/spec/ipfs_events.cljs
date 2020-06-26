(ns tokens.shared.spec.ipfs-events
  (:require
    [cljs.spec.alpha :as s]
    [district-designer.shared.spec.ipfs-events :refer [ipfs-hash? address? edn? event-type]]))


(s/def :token-contract/address address?)
(s/def :token-contract/metadata-format string?)
(s/def :token-contract/metadata-format-settings edn?)
(s/def :token-contract/reported-misconfig-comment string?)

(defmethod event-type :tokens/add-token-factory [_]
  :district-designer/add-smart-contract)

(defmethod event-type :tokens/update-token-contract [_]
  (s/merge
    :district-designer.shared.spec.ipfs-events/event-base
    (s/keys :req [:token-contract/address]
            :opt [:token-contract/metadata-format
                  :token-contract/metadata-format-settings])))


(defmethod event-type :tokens/remove-token-contract [_]
  (s/merge
    :district-designer.shared.spec.ipfs-events/event-base
    (s/keys :req [:token-contract/address])))


(defmethod event-type :tokens/add-district-token-contract [_]
  (s/merge
    :district-designer.shared.spec.ipfs-events/event-base
    (s/keys :req [:district/address
                  :token-contract/address])))


(defmethod event-type :tokens/remove-district-token-contract [_]
  :tokens/add-district-token-contract)


(defmethod event-type :tokens/add-token-contract-misconfig-report [_]
  (s/merge
    :district-designer.shared.spec.ipfs-events/event-base
    (s/keys :req [:token-contract/address
                  :token-contract/reported-misconfig-comment])))


(defmethod event-type :tokens/resolve-token-contract-misconfig-report [_]
  (s/merge
    :district-designer.shared.spec.ipfs-events/event-base
    (s/keys :req [:token-contract/address])))