(ns district-designer.shared.spec.ipfs-events
  (:require [cljs.spec.alpha :as s]))

(def ipfs-events
  #{:district-designer/events-batch
    :district-designer/add-dd-proxy-factory
    :district-designer/add-district-designer
    :district-designer/add-module
    :district-designer/update-module
    :district-designer/remove-module
    :district-designer/add-wizard
    :district-designer/update-wizard
    :district-designer/remove-wizard
    :district-designer/add-theme
    :district-designer/update-theme
    :district-designer/remove-theme
    :district-designer/add-permission
    :district-designer/update-permission
    :district-designer/remove-permission
    :district-designer/add-tag-group
    :district-designer/update-tag-group
    :district-designer/remove-tag-group
    :district-designer/add-tags
    :district-designer/remove-tags
    :district/update-theme
    :district/update-styles
    :district/add-module
    :district/remove-module
    :district/add-ui-component
    :district/update-ui-component
    :district/remove-ui-component
    :district/add-database-view
    :district/update-datadatabase-view
    :district/remove-datadatabase-view
    :district/add-statistics-view
    :district/update-statistics-view
    :district/remove-statistics-view})


(defn address? [x]
  (and (string? x) (= 42 (count x))))

(defn ipfs-hash? [x]
  (and (string? x) (= 46 (count x))))

(defn edn? [x]
  (string? x))

(s/def :event/type keyword?)
(defmulti event-type :event/type)
(s/def ::events (s/coll-of (s/keys :req-un [::timestamp]
                                   :req [:event/type])))
(s/def ::sender address?)
(s/def ::timestamp pos-int?)
(s/def ::version pos-int?)


(s/def ::event-base (s/keys :req-un [::sender ::timestamp]
                            :req [:event/type]))

(s/def :file/name string?)
(s/def :file/hash ipfs-hash?)
(s/def :file/file (s/keys :req [:file/hash] :opt [:file/name]))

(s/def :smart-contract/address address?)
(s/def :smart-contract/abi :file/file)

(s/def :module/id string?)
(s/def :module/name string?)
(s/def :module/logo ipfs-hash?)
(s/def :module/owner address?)
(s/def :module/description string?)
(s/def :module/preview-images (s/coll-of :file/file))

(s/def :wizard/id string?)
(s/def :wizard/name string?)
(s/def :wizard/logo ipfs-hash?)
(s/def :wizard/owner address?)
(s/def :wizard/description string?)
(s/def :wizard/preview-images (s/coll-of :file/file))

(s/def :theme/id string?)
(s/def :theme/name string?)
(s/def :theme/owner address?)
(s/def :theme/description string?)
(s/def :theme/default-settings edn?)
(s/def :theme/preview-images (s/coll-of :file/file))
(s/def :theme/files (s/coll-of :file/file))

(s/def :district/uuid uuid?)
(s/def :district/less-file :file/file)
(s/def :district/css-file :file/file)
(s/def :district/theme :theme/id)
(s/def :district/theme-settings :theme/default-settings)

(s/def :ui-component/uuid uuid?)
(s/def :ui-component/name string?)
(s/def :ui-component/type string?)
(s/def :ui-component/children (s/coll-of :ui-component/uuid))
(s/def :ui-component/settings edn?)
(s/def :ui-component/files (s/coll-of :file/file))

(s/def :data-view/uuid uuid?)
(s/def :data-view/name string?)
(s/def :data-view/settings edn?)

(s/def :field-config/uuid uuid?)
(s/def :field-config/name string?)
(s/def :field-config/namespace string?)
(s/def :field-config/type string?)
(s/def :field-config/settings edn?)
(s/def :field-config/field-config (s/keys :req [:field-config/uuid
                                                :field-config/name
                                                :field-config/namespace
                                                :field-config/type
                                                :field-config/settings]))

(s/def :global/enabled? boolean?)
(s/def :global/logo :file/file)
(s/def :global/description string?)


(s/def :tag-group/uuid uuid?)
(s/def :tag-group/name string?)
(s/def :tag-group/users-allowed-adding-tags? boolean?)
(s/def :tag-group/global-enabled? :global/enabled?)
(s/def :tag-group/tags (s/coll-of string?))


;; IPFS Events

(defmethod event-type :district-designer/events-batch [_]
  (s/merge
    ::event-base
    (s/keys :req-un [::events])))

(s/def :district-designer/add-smart-contract
  (s/merge
    ::event-base
    (s/keys :req [:smart-contract/address :smart-contract/abi])))

(defmethod event-type :district-designer/add-dd-proxy-factory [_]
  :district-designer/add-smart-contract)

(defmethod event-type :district-designer/add-district-designer [_]
  :district-designer/add-smart-contract)

(defmethod event-type :district-designer/add-module [_]
  (s/merge
    ::event-base
    (s/keys :req [:module/id
                  :module/name
                  :module/logo
                  :module/owner
                  :module/description
                  :module/preview-images])))


(defmethod event-type :district-designer/update-module [_]
  (s/merge
    ::event-base
    (s/keys :req [:module/id]
            :opt [:module/name
                  :module/logo
                  :module/owner
                  :module/description
                  :module/preview-images])))

(defmethod event-type :district-designer/remove-module [_]
  (s/merge
    ::event-base
    (s/keys :req [:module/id])))


(defmethod event-type :district-designer/add-wizard [_]
  (s/merge
    ::event-base
    (s/keys :req [:wizard/id
                  :wizard/name
                  :wizard/logo
                  :wizard/owner
                  :wizard/description
                  :wizard/preview-images])))


(defmethod event-type :district-designer/update-wizard [_]
  (s/merge
    ::event-base
    (s/keys :req [:wizard/id]
            :opt [:wizard/name
                  :wizard/logo
                  :wizard/owner
                  :wizard/description
                  :wizard/preview-images])))

(defmethod event-type :district-designer/remove-wizard [_]
  (s/merge
    ::event-base
    (s/keys :req [:wizard/id])))


(defmethod event-type :district-designer/add-theme [_]
  (s/merge
    ::event-base
    (s/keys :req [:theme/id
                  :theme/name
                  :theme/owner
                  :theme/description
                  :theme/default-settings
                  :theme/preview-images
                  :theme/files])))


(defmethod event-type :district-designer/update-theme [_]
  (s/merge
    ::event-base
    (s/keys :req [:theme/id]
            :opt [:theme/name
                  :theme/owner
                  :theme/description
                  :theme/default-settings
                  :theme/preview-images
                  :theme/files])))

(defmethod event-type :district-designer/remove-theme [_]
  (s/merge
    ::event-base
    (s/keys :req [:theme/id])))

(s/def :permission/id string?)
(s/def :permission/name string?)
(s/def :permission/description string?)

(defmethod event-type :district-designer/add-permission [_]
  (s/merge
    ::event-base
    (s/keys :req [:permission/id
                  :permission/name
                  :permission/description])))


(defmethod event-type :district-designer/update-permission [_]
  (s/merge
    ::event-base
    (s/keys :req [:permission/id]
            :opt [:permission/name
                  :permission/description])))

(defmethod event-type :district-designer/remove-permission [_]
  (s/merge
    ::event-base
    (s/keys :req [:permission/id])))


(defmethod event-type :district-designer/add-tag-group [_]
  (s/merge
    ::event-base
    (s/keys :req [:tag-group/uuid
                  :tag-group/name
                  :tag-group/users-allowed-adding-tags?
                  :tag-group/global-enabled?])))


(defmethod event-type :district-designer/update-tag-group [_]
  (s/merge
    ::event-base
    (s/keys :req [:tag-group/uuid]
            :opt [:tag-group/name
                  :tag-group/users-allowed-adding-tags?
                  :tag-group/global-enabled?])))


(defmethod event-type :district-designer/remove-tag-group [_]
  (s/merge
    ::event-base
    (s/keys :req [:tag-group/uuid])))


(defmethod event-type :district-designer/add-tags [_]
  (s/merge
    ::event-base
    (s/keys :req [:tag-group/uuid :tag-group/tags])))


(defmethod event-type :district-designer/remove-tags [_]
  :district-designer/add-tags)


(defmethod event-type :district/update-theme [_]
  (s/merge
    ::event-base
    (s/keys :req [:district/uuid]
            :opt [:district/theme
                  :district/theme-settings])))


(defmethod event-type :district/update-styles [_]
  (s/merge
    ::event-base
    (s/keys :req [:district/uuid]
            :opt [:district/less-file
                  :district/css-file])))


(defmethod event-type :district/add-module [_]
  (s/merge
    ::event-base
    (s/keys :req [:district/uuid :module/id])))

(defmethod event-type :district/remove-module [_]
  (s/merge
    ::event-base
    (s/keys :req [:district/uuid :module/id])))


(defmethod event-type :district/add-ui-component [_]
  (s/merge
    ::event-base
    (s/keys :req [:district/uuid
                  :ui-component/uuid
                  :ui-component/name
                  :ui-component/type
                  :ui-component/children
                  :ui-component/settings
                  :ui-component/files])))


(defmethod event-type :district/update-ui-component [_]
  (s/merge
    ::event-base
    (s/keys :req [:district/uuid
                  :ui-component/uuid]
            :opt [:ui-component/name
                  :ui-component/type
                  :ui-component/children
                  :ui-component/settings
                  :ui-component/files])))


(defmethod event-type :district/remove-ui-component [_]
  (s/merge
    ::event-base
    (s/keys :req [:district/uuid
                  :ui-component/uuid])))


(defmethod event-type :district/add-database-view [_]
  (s/merge
    ::event-base
    (s/keys :req [:district/uuid
                  :data-view/uuid
                  :data-view/name
                  :data-view/settings])))


(defmethod event-type :district/update-datadatabase-view [_]
  (s/merge
    ::event-base
    (s/keys :req [:district/uuid
                  :data-view/uuid]
            :opt [:data-view/name
                  :data-view/settings])))


(defmethod event-type :district/remove-datadatabase-view [_]
  (s/merge
    ::event-base
    (s/keys :req [:district/uuid
                  :data-view/uuid])))

(defmethod event-type :district/add-statistics-view [_]
  :district/add-database-view)

(defmethod event-type :district/update-statistics-view [_]
  :district/update-datadatabase-view)

(defmethod event-type :district/remove-statistics-view [_]
  :district/remove-datadatabase-view)