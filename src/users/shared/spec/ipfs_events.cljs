(ns users.shared.spec.ipfs-events
  (:require
    [cljs.spec.alpha :as s]
    [district-designer.shared.spec.ipfs-events :refer [ipfs-hash? address? edn? event-type]]))


(s/def :message/uuid uuid?)
(s/def :message/receiver address?)
(s/def :message/text string?)
(s/def :message/files (s/coll-of :file/file))
(s/def ::message (s/keys :req [:message/uuid
                               :message/receiver
                               :message/text]
                         :opt [:message/files]))
(s/def ::messages (s/coll-of ::message))

(s/def :user-profile/uuid uuid?)
(s/def :user-profile/name string?)
(s/def :user-profile/field-configs (s/coll-of :field-config/field-config))
(s/def :user-profile/global-enabled? :global/enabled?)
(s/def :user-profile/global-logo :global/logo)
(s/def :user-profile/global-description :global/description)

(defmethod event-type :users/add-direct-message [_]
  (s/merge
    :district-designer.shared.spec.ipfs-events/event-base
    (s/keys :req [:message/uuid
                  :message/receiver
                  :message/text]
            :opt [:message/files])))


(defmethod event-type :users/add-user-profile [_]
  (s/merge
    :district-designer.shared.spec.ipfs-events/event-base
    (s/keys :req [:district/address
                  :user-profile/uuid
                  :user-profile/name
                  :user-profile/field-configs]
            :opt [:user-profile/global-enabled?
                  :user-profile/global-logo
                  :user-profile/global-description])))


(defmethod event-type :users/update-user-profile [_]
  (s/merge
    :district-designer.shared.spec.ipfs-events/event-base
    (s/keys :req [:user-profile/uuid]
            :opt [:user-profile/name
                  :user-profile/field-configs
                  :user-profile/global-enabled?
                  :user-profile/global-logo
                  :user-profile/global-description])))


(defmethod event-type :users/remove-user-profile [_]
  (s/merge
    :district-designer.shared.spec.ipfs-events/event-base
    (s/keys :req [:user-profile/uuid])))


(defmethod event-type :users/add-district-user-profile [_]
  (s/merge
    :district-designer.shared.spec.ipfs-events/event-base
    (s/keys :req [:district/address
                  :user-profile/uuid])))


(defmethod event-type :users/remove-district-user-profile [_]
  (s/merge
    :district-designer.shared.spec.ipfs-events/event-base
    (s/keys :req [:district/address
                  :user-profile/uuid])))


(defmethod event-type :users/update-user [_]
  (s/merge
    :district-designer.shared.spec.ipfs-events/event-base))