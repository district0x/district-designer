(ns district-designer.shared.spec.smart-contract-events
  (:require
    [cljs.spec.alpha :as s]
    [district-designer.shared.spec.ipfs-events :refer [ipfs-hash? address? edn? event-type]]))


(s/def ::address address?)
(s/def ::district ::address)
(s/def ::permission-id string?)
(s/def ::permission-ids (s/coll-of ::permission-id))
(s/def ::user-role-id uuid?)
(s/def ::user-role-ids (s/coll-of ::user-role-id))
(s/def ::permission (s/keys :req-un [::permission-id ::user-role-ids]))
(s/def ::permissions (s/coll-of ::permission))
(s/def ::addresses (s/coll-of ::address))
(s/def ::treasury ::address)
(s/def ::is-removed boolean?)
(s/def ::user-role (s/keys :req-un [::user-role-id ::addresses ::is-removed]))
(s/def ::user-roles (s/coll-of ::user-role))
(s/def ::admin-user-role-id ::user-role-id)

(s/def ::wizard-base (s/keys :req [:wizard/code]))

(s/def :user-role/uuid uuid?)
(s/def :user-role/name string?)
(s/def ::user-role-names (s/coll-of (s/keys :req [:user-role/uuid :user-role/name])))


(s/def :district-created/ipfs-data
  (s/merge
    ::wizard-base
    (s/keys :req-un [::user-role-names])))


(defmethod event-type :district-designer/district-created [_]
  (s/merge
    :district-designer.shared.spec.ipfs-events/event-base
    (s/keys :req-un [::district
                     ::permissions
                     ::user-roles
                     ::admin-user-role-id
                     ::treasury
                     :district-created/ipfs-data])))


(defmethod event-type :district-designer/permissions-updated [_]
  (s/merge
    :district-designer.shared.spec.ipfs-events/event-base
    (s/keys :req-un [::district
                     ::permissions])))


(s/def :user-roles-updated/ipfs-data
  (s/keys :req-un [::user-role-names]))


(defmethod event-type :district-designer/user-roles-updated [_]
  (s/merge
    :district-designer.shared.spec.ipfs-events/event-base
    (s/keys :req-un [::user-roles
                     :user-roles-updated/ipfs-data])))


(defmethod event-type :district-designer/district-treasury-updated [_]
  (s/merge
    :district-designer.shared.spec.ipfs-events/event-base
    (s/keys :req-un [::district
                     ::treasury])))


(s/def ::module-id :module/code)
(s/def ::is-emergency boolean?)


(defmethod event-type :district-designer/emergency-updated [_]
  (s/merge
    :district-designer.shared.spec.ipfs-events/event-base
    (s/keys :req-un [::module-id
                     ::is-emergency])))

(s/def ::contract-address ::address)
(s/def ::ipfs-abi ipfs-hash?)
(s/def :proxy-factory/proxy-target (s/keys :req-un [::contract-address ::ipfs-abi]))
(s/def ::district-target :proxy-factory/proxy-target)


(defmethod event-type :district-designer/proxy-targets-updated
  [_]
  (s/merge
    :district-designer.shared.spec.ipfs-events/event-base
    (s/keys :req-un [::district-target])))


(s/def ::proxy ::address)
(s/def ::old-target :proxy-factory/proxy-target)
(s/def ::new-target :proxy-factory/proxy-target)


(defmethod event-type :proxy-factory/proxy-target-updated [_]
  (s/merge
    :district-designer.shared.spec.ipfs-events/event-base
    (s/keys :req-un [::proxy
                     ::old-target
                     ::new-target])))