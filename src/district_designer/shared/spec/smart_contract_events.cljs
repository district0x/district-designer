(ns district-designer.shared.spec.smart-contract-events
  (:require
    [cljs.spec.alpha :as s]
    [district-designer.shared.spec.ipfs-events :refer [ipfs-hash? address? edn?]]))


(def smart-contract-events
  #{:district-designer/district-initialized
    :district-designer/permissions-updated
    :district-designer/user-roles-updated
    :district-designer/district-treasury-updated
    :district-designer/emergency-updated
    :dd-proxy-factory/proxy-target-updated})


(s/def ::district uuid?)
(s/def ::permission-id string?)
(s/def ::permission-ids (s/coll-of ::permission-id))
(s/def ::user-role-id uuid?)
(s/def ::user-role-ids (s/coll-of ::user-role-id))
(s/def ::permission (s/keys :req-un [::permission-id ::user-role-ids]))
(s/def ::permissions (s/coll-of ::permission))
(s/def ::address address?)
(s/def ::addresses (s/coll-of ::address))
(s/def ::treasury ::address)
(s/def ::user-role (s/keys :req-un [::user-role-id ::addresses]))
(s/def ::user-roles (s/coll-of ::user-role))
(s/def ::admin-user-role-id ::user-role-id)

(s/def :wizard/id string?)
(s/def ::ipfs-data-base (s/keys :req-un [::event]))
(s/def ::wizard-base (s/keys :req [:wizard/id]))

(s/def :user-role/uuid uuid?)
(s/def :user-role/name string?)
(s/def ::user-role-names (s/coll-of (s/keys :req [:user-role/uuid :user-role/name])))


(s/def :district-initialized/ipfs-data
  (s/merge
    ::ipfs-data-base
    ::wizard-base
    (s/keys :req-un [::user-role-names])))


(s/def :district-designer/district-initialized
  (s/merge
    :district-designer.shared.spec.ipfs-events/event-base
    (s/keys :req-un [::district
                     ::permissions
                     ::user-roles
                     ::admin-user-role-id
                     ::treasury
                     :district-initialized/ipfs-data])))


(s/def :district-designer/permissions-updated
  (s/merge
    :district-designer.shared.spec.ipfs-events/event-base
    (s/keys :req-un [::district
                     ::permissions])))


(s/def :user-roles-updated/ipfs-data
  (s/merge
    ::ipfs-data-base
    (s/keys :req-un [::user-role-names])))


(s/def :district-designer/user-roles-updated
  (s/merge
    :district-designer.shared.spec.ipfs-events/event-base
    (s/keys :req-un [::district
                     ::user-roles
                     :user-roles-updated/ipfs-data])))


(s/def :district-designer/district-treasury-updated
  (s/merge
    :district-designer.shared.spec.ipfs-events/event-base
    (s/keys :req-un [::district
                     ::treasury])))


(s/def ::module-id :module/id)
(s/def ::is-emergency boolean?)


(s/def :district-designer/emergency-updated
  (s/merge
    :district-designer.shared.spec.ipfs-events/event-base
    (s/keys :req-un [::module-id
                     ::is-emergency])))

(s/def ::proxy ::address)
(s/def ::old-target ::address)
(s/def ::new-target ::address)
(s/def ::ipfs-abi ipfs-hash?)

(s/def :dd-proxy-factory/proxy-target-updated
  (s/merge
    :district-designer.shared.spec.ipfs-events/event-base
    (s/keys :req-un [::proxy
                     ::old-target
                     ::new-target
                     ::ipfs-abi])))