(ns users.shared.spec.ipfs-events
  (:require
    [cljs.spec.alpha :as s]
    [district-designer.shared.spec.ipfs-events :refer [ipfs-hash? address? edn?]]))

(def ipfs-events
  #{:users/add-direct-message
    :users/add-user-profile
    :users/update-user-profile
    :users/remove-user-profile
    :users/add-district-user-profile
    :users/remove-district-user-profile
    :users/update-user})

(s/def :message/uuid uuid?)
(s/def :message/receiver address?)
(s/def :message/text string?)
(s/def :message/files (s/coll-of :file/file))

(s/def :user-profile/uuid uuid?)
(s/def :user-profile/name string?)
(s/def :user-profile/field-configs (s/coll-of :field-config/field-config))
(s/def :user-profile/global-enabled? :global/enabled?)
(s/def :user-profile/global-logo :global/logo)
(s/def :user-profile/global-description :global/description)

(s/def :users/add-direct-message
  (s/merge
    :district-designer.shared.spec.ipfs-events/event-base
    (s/keys :req [:message/uuid
                  :message/receiver
                  :message/text]
            :opt [:message/files])))


(s/def :users/add-user-profile
  (s/merge
    :district-designer.shared.spec.ipfs-events/event-base
    (s/keys :req [:district/uuid
                  :user-profile/uuid
                  :user-profile/name
                  :user-profile/field-configs]
            :opt [:user-profile/global-enabled?
                  :user-profile/global-logo
                  :user-profile/global-description])))


(s/def :users/update-user-profile
  (s/merge
    :district-designer.shared.spec.ipfs-events/event-base
    (s/keys :req [:user-profile/uuid]
            :opt [:user-profile/name
                  :user-profile/field-configs
                  :user-profile/global-enabled?
                  :user-profile/global-logo
                  :user-profile/global-description])))


(s/def :users/remove-user-profile
  (s/merge
    :district-designer.shared.spec.ipfs-events/event-base
    (s/keys :req [:user-profile/uuid])))


(s/def :users/add-district-user-profile
  (s/merge
    :district-designer.shared.spec.ipfs-events/event-base
    (s/keys :req [:district/uuid
                  :user-profile/uuid])))


(s/def :users/remove-district-user-profile :users/add-district-user-profile)


(s/def :users/update-user
  (s/merge
    :district-designer.shared.spec.ipfs-events/event-base))