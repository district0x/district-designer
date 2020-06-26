(ns tcr.shared.spec.smart-contract-events
  (:require
    [cljs.spec.alpha :as s]
    [district-designer.shared.spec.ipfs-events :refer [ipfs-hash? address? edn? event-type]]
    [district-designer.shared.spec.smart-contract-events]
    [tcr.shared.spec.ipfs-events]
    [tokens.shared.spec.smart-contract-events]))


(s/def ::tcr address?)
(s/def ::tcr-target :proxy-factory/proxy-target)
(s/def ::tcr-version :district-designer.shared.spec.ipfs-events/version)
(s/def ::voting-token-type #{:voting-token-type/erc-20
                             :voting-token-type/erc-1155})
(s/def ::token-address address?)
(s/def ::token-id nat-int?)
(s/def ::voting-token (s/keys :req-un [::voting-token-type ::token-address]
                              :opt-un [::token-id]))
(s/def ::tcr-type #{:tcr-type/challengeable-anytime
                    :tcr-type/challengeable-once})
(s/def :reg-entry-representation/category #{:tcr-reg-entry-representation-category/erc-1155
                                            :tcr-reg-entry-representation-category/erc-721
                                            :tcr-reg-entry-representation-category/no-token})


(s/def ::reg-entry-representation (s/keys :req-un [:reg-entry-representation/category
                                                   :tokens.shared.spec.smart-contract-events/token-name
                                                   :tokens.shared.spec.smart-contract-events/token-symbol
                                                   :tokens.shared.spec.smart-contract-events/base-metadata-uri]))


(s/def ::create-reg-entry-user-roles (s/coll-of :user-role/uuid))
(s/def ::create-param-change-entry-user-roles (s/coll-of :user-role/uuid))
(s/def ::permission-user-roles (s/keys :req-un [::create-reg-entry-user-roles
                                                ::create-param-change-entry-user-roles]))

(defn percentage? [x]
  (and (number? x) (>= x 0) (<= x 100)))

(s/def :tcr-parameters/challenge-dispensation percentage?)
(s/def :tcr-parameters/challenge-period-duration nat-int?)
(s/def :tcr-parameters/deposit pos-int?)
(s/def :tcr-parameters/vote-commit-period-duration pos-int?)
(s/def :tcr-parameters/vote-quorum percentage?)
(s/def :tcr-parameters/vote-reveal-period-duration pos-int?)

(s/def ::tcr-parameters (s/keys :req-un [:tcr-parameters/challenge-dispensation
                                         :tcr-parameters/challenge-period-duration
                                         :tcr-parameters/deposit
                                         :tcr-parameters/vote-commit-period-duration
                                         :tcr-parameters/vote-quorum
                                         :tcr-parameters/vote-reveal-period-duration]))

(s/def ::reg-entry-parameters ::tcr-parameters)
(s/def ::param-change-entry-parameters ::tcr-parameters)

(s/def :tcr/name string?)
(s/def :tcr/reg-entry-field-configs (s/coll-of :field-config/field-config))
(s/def :tcr/global-enabled? :global/enabled?)
(s/def :tcr/global-logo :global/logo)
(s/def :tcr/global-description :global/description)

(s/def :tcr-created/ipfs-data
  (s/keys :req [:tcr/name
                :tcr/reg-entry-field-configs]
          :opt [:tcr/global-enabled?
                :tcr/global-logo
                :tcr/global-description]))


(defmethod event-type :tcr/tcr-created [_]
  (s/merge
    :district-designer.shared.spec.ipfs-events/event-base
    (s/keys :req-un [:district-designer.shared.spec.smart-contract-events/district
                     ::tcr
                     ::tcr-target
                     ::tcr-version
                     ::voting-token
                     ::tcr-type
                     ::reg-entry-representation
                     ::permission-user-roles
                     ::reg-entry-parameters
                     ::param-change-entry-parameters
                     :tcr-created/ipfs-data])))


(s/def ::reg-entry address?)
(s/def ::reg-entry-base-contract address?)
(s/def ::reg-entry-target :proxy-factory/proxy-target)
(s/def ::creator address?)
(s/def ::token-amount pos-int?)
(s/def ::token-meta-ipfs-data ipfs-hash?)
(s/def ::reg-entry-version :district-designer.shared.spec.ipfs-events/version)


(s/def :registry-entry-created/ipfs-data
  (s/keys))


(defmethod event-type :tcr/registry-entry-created [_]
  (s/merge
    :district-designer.shared.spec.ipfs-events/event-base
    (s/keys :req-un [::tcr
                     ::reg-entry
                     ::reg-entry-target
                     ::reg-entry-version
                     ::creator
                     ::token-amount
                     ::token-meta-ipfs-data
                     :registry-entry-created/ipfs-data])))

(s/def ::param-change-entry ::reg-entry)
(s/def ::param-change-entry-target :proxy-factory/proxy-target)
(s/def ::param-change-entry-version :district-designer.shared.spec.ipfs-events/version)

(s/def ::entries-group #{:entries-group/registry-entries
                         :entries-group/param-change-entries})

(s/def :param-change-entry/parameter-key string?)
(s/def :param-change-entry/parameter-value nat-int?)
(s/def :param-change-entry/original-parameter-value :param-change-entry/parameter-value)
(s/def :param-change-entry/comment string?)


(s/def :param-change-entry-created/ipfs-data
  (s/keys :req [:param-change-entry/comment]))


(defmethod event-type :tcr/param-change-entry-created [_]
  (s/merge
    :district-designer.shared.spec.ipfs-events/event-base
    (s/keys :req-un [::tcr
                     ::param-change-entry
                     ::param-change-entry-target
                     ::param-change-entry-version
                     ::creator
                     ::entries-group
                     :param-change-entry/parameter-key
                     :param-change-entry/parameter-value
                     :param-change-entry/original-parameter-value
                     :param-change-entry-created/ipfs-data])))

(s/def ::entry ::reg-entry)
(s/def ::challenge-index nat-int?)
(s/def ::challenger address?)
(s/def ::commit-period-end pos-int?)
(s/def ::reveal-period-end pos-int?)
(s/def ::reward-pool pos-int?)
(s/def :challenge/comment string?)

(s/def :challenge-created/ipfs-data
  (s/keys :req [:challenge/comment]))


(defmethod event-type :tcr/challenge-created [_]
  (s/merge
    :district-designer.shared.spec.ipfs-events/event-base
    (s/keys :req-un [::entry
                     ::entries-group
                     ::challenge-index
                     ::challenger
                     ::commit-period-end
                     ::reveal-period-end
                     ::reward-pool
                     :challenge-created/ipfs-data])))


(defmethod event-type :tcr/registry-entry-token-minted [_]
  (s/merge
    :district-designer.shared.spec.ipfs-events/event-base
    (s/keys :req-un [::reg-entry
                     ::token-id])))


(defmethod event-type :tcr/param-change-entry-applied [_]
  (s/merge
    :district-designer.shared.spec.ipfs-events/event-base
    (s/keys :req-un [::param-change-entry])))

(s/def ::amount nat-int?)

(defmethod event-type :tcr/challenger-reward-claimed [_]
  (s/merge
    :district-designer.shared.spec.ipfs-events/event-base
    (s/keys :req-un [::entry
                     ::entries-group
                     ::challenger
                     ::amount])))


(defmethod event-type :tcr/creator-reward-claimed [_]
  (s/merge
    :district-designer.shared.spec.ipfs-events/event-base
    (s/keys :req-un [::entry
                     ::entries-group
                     ::creator
                     ::amount])))

(s/def ::voter address?)

(defmethod event-type :tcr/votes-reclaimed [_]
  (s/merge
    :district-designer.shared.spec.ipfs-events/event-base
    (s/keys :req-un [::entry
                     ::entries-group
                     ::voter
                     ::amount])))


(defmethod event-type :tcr/vote-committed [_]
  (s/merge
    :district-designer.shared.spec.ipfs-events/event-base
    (s/keys :req-un [::entry
                     ::entries-group
                     ::voter
                     ::amount])))

(s/def ::vote-option #{:tcr-vote-option/include
                       :tcr-vote-option/exclude
                       :tcr-vote-option/no-vote})

(defmethod event-type :tcr/vote-revealed [_]
  (s/merge
    :district-designer.shared.spec.ipfs-events/event-base
    (s/keys :req-un [::entry
                     ::entries-group
                     ::voter
                     ::vote-option
                     ::amount])))


(defmethod event-type :tcr/vote-reward-claimed [_]
  (s/merge
    :district-designer.shared.spec.ipfs-events/event-base
    (s/keys :req-un [::entry
                     ::entries-group
                     ::voter
                     ::amount])))

(defmethod event-type :tcr/proxy-targets-updated [_]
  (s/merge
    :district-designer.shared.spec.ipfs-events/event-base
    (s/keys :req-un [::tcr-target
                     ::reg-entry-target
                     ::param-change-entry-target])))


(defmethod event-type :tcr/tcr-proxy-targets-updated [_]
  (s/merge
    :district-designer.shared.spec.ipfs-events/event-base
    (s/keys :req-un [::tcr
                     ::reg-entry-target
                     ::param-change-entry-target])))




