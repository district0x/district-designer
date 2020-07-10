(ns tokens.shared.spec.smart-contract-events
  (:require
    [cljs.spec.alpha :as s]
    [district-designer.shared.spec.ipfs-events :refer [ipfs-hash? address? edn? event-type]]
    [district-designer.shared.spec.smart-contract-events]
    [tokens.shared.spec.ipfs-events]))


(s/def ::token address?)
(s/def ::token-type #{:erc20
                      :erc721
                      :erc1155})
(s/def ::token-ipfs-abi ipfs-hash?)
(s/def ::token-version integer?)
(s/def ::token-name string?)
(s/def ::token-symbol string?)
(s/def ::base-uri string?)
(s/def ::decimal-units nat-int?)
(s/def ::owner address?)


(s/def :token-created/ipfs-data
  (s/keys :req [:token-contract/fields
                :token-contract/metadata-format
                :token-contract/metadata-format-settings]))


(defmethod event-type :tokens/token-created [_]
  (s/merge
    :district-designer.shared.spec.ipfs-events/event-base
    (s/keys :req-un [:district-designer.shared.spec.smart-contract-events/district
                     ::token
                     ::token-type
                     ::token-ipfs-abi
                     ::token-version
                     ::token-name
                     ::token-symbol
                     ::base-uri
                     ::decimal-units
                     ::owner
                     :token-created/ipfs-data])))


(s/def ::new-factory address?)

(defmethod event-type :tokens/factory-updated [_]
  (s/merge
    :district-designer.shared.spec.ipfs-events/event-base
    (s/keys :req-un [::new-factory
                     ::token-type])))