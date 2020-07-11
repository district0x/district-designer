(ns district-designer.shared.spec.ipfs-events
  (:require [cljs.spec.alpha :as s]))


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
(s/def :file/encrypted? boolean?)
(s/def :file/decryptable-by address?)
(s/def :file/file (s/keys :req [:file/hash] :opt [:file/name :file/encrypted? :file/decryptable-by]))

(s/def :smart-contract/address address?)
(s/def :smart-contract/abi :file/file)
(s/def :smart-contract/proxy? boolean?)
(s/def :smart-contract/proxy-target address?)
(s/def :smart-contract/proxy-type #{:owner-proxy :district-admin-proxy})
(s/def :smart-contract/owner address?)

(s/def :module/code string?)
(s/def :module/name string?)
(s/def :module/logo ipfs-hash?)
(s/def :module/owner address?)
(s/def :module/description string?)
(s/def :module/preview-images (s/coll-of :file/file))

(s/def :wizard/code string?)
(s/def :wizard/name string?)
(s/def :wizard/logo ipfs-hash?)
(s/def :wizard/owner address?)
(s/def :wizard/description string?)
(s/def :wizard/preview-images (s/coll-of :file/file))

(s/def :theme/code string?)
(s/def :theme/name string?)
(s/def :theme/owner address?)
(s/def :theme/description string?)
(s/def :theme/default-settings edn?)
(s/def :theme/preview-images (s/coll-of :file/file))
(s/def :theme/files (s/coll-of :file/file))

(s/def :district/address address?)
(s/def :district-theme/settings :theme/default-settings)
(s/def :district-theme/css :file/file)
(s/def :district-theme/less :file/file)
(s/def :district-theme/active? boolean?)

(s/def :ui-component/uuid uuid?)
(s/def :ui-component/name string?)
(s/def :ui-component/type string?)
(s/def :ui-component/children (s/coll-of :ui-component/uuid))
(s/def :ui-component/settings edn?)
(s/def :ui-component/files (s/coll-of :file/file))

(s/def :global/enabled? boolean?)
(s/def :global/logo :file/file)
(s/def :global/description string?)

(s/def :tag-group/uuid uuid?)
(s/def :tag-group/name string?)
(s/def :tag-group/district-origin address?)
(s/def :tag-group/users-allowed-adding-tags? boolean?)
(s/def :tag-group/global-enabled? :global/enabled?)
(s/def :tag-group/tags (s/coll-of string?))

(s/def :field/uuid uuid?)
(s/def :field/name string?)
(s/def :field/namespace string?)
(s/def :field/type string?)
(s/def :field/settings edn?)
(s/def :field/field (s/keys :req [:field/uuid
                                  :field/name
                                  :field/namespace
                                  :field/type
                                  :field/settings]))

(s/def :field-value/uuid uuid?)
(s/def :field-value/type #{:string :int :float :boolean :date :file :tags})
(s/def :field-value/value any?)

(s/def :field-value/field-value (s/keys :req [:field-value/uuid
                                              :field/uuid
                                              :field-value/type
                                              :field-value/value]))


(defmethod event-type :district-designer/events-batch [_]
  (s/merge
    ::event-base
    (s/keys :req-un [::events])))

(s/def :district-designer/add-smart-contract
  (s/merge
    ::event-base
    (s/keys :req [:smart-contract/address
                  :smart-contract/abi]
            :opt [:smart-contract/proxy?
                  :smart-contract/proxy-target
                  :smart-contract/proxy-type
                  :smart-contract/owner])))


(defmethod event-type :district-designer/add-proxy-factory [_]
  :district-designer/add-smart-contract)

(defmethod event-type :district-designer/add-district-factory [_]
  :district-designer/add-smart-contract)

(defmethod event-type :district-designer/add-module [_]
  (s/merge
    ::event-base
    (s/keys :req [:module/code
                  :module/name
                  :module/logo
                  :module/owner
                  :module/description
                  :module/preview-images])))


(defmethod event-type :district-designer/update-module [_]
  (s/merge
    ::event-base
    (s/keys :req [:module/code]
            :opt [:module/name
                  :module/logo
                  :module/owner
                  :module/description
                  :module/preview-images])))

(defmethod event-type :district-designer/remove-module [_]
  (s/merge
    ::event-base
    (s/keys :req [:module/code])))


(defmethod event-type :district-designer/add-wizard [_]
  (s/merge
    ::event-base
    (s/keys :req [:wizard/code
                  :wizard/name
                  :wizard/logo
                  :wizard/owner
                  :wizard/description
                  :wizard/preview-images])))


(defmethod event-type :district-designer/update-wizard [_]
  (s/merge
    ::event-base
    (s/keys :req [:wizard/code]
            :opt [:wizard/name
                  :wizard/logo
                  :wizard/owner
                  :wizard/description
                  :wizard/preview-images])))

(defmethod event-type :district-designer/remove-wizard [_]
  (s/merge
    ::event-base
    (s/keys :req [:wizard/code])))


(defmethod event-type :district-designer/add-theme [_]
  (s/merge
    ::event-base
    (s/keys :req [:theme/code
                  :theme/name
                  :theme/owner
                  :theme/description
                  :theme/default-settings
                  :theme/preview-images
                  :theme/files])))


(defmethod event-type :district-designer/update-theme [_]
  (s/merge
    ::event-base
    (s/keys :req [:theme/code]
            :opt [:theme/name
                  :theme/owner
                  :theme/description
                  :theme/default-settings
                  :theme/preview-images
                  :theme/files])))

(defmethod event-type :district-designer/remove-theme [_]
  (s/merge
    ::event-base
    (s/keys :req [:theme/code])))

(s/def :permission/code string?)
(s/def :permission/name string?)
(s/def :permission/description string?)

(defmethod event-type :district-designer/add-permission [_]
  (s/merge
    ::event-base
    (s/keys :req [:permission/code
                  :permission/name
                  :permission/description])))


(defmethod event-type :district-designer/update-permission [_]
  (s/merge
    ::event-base
    (s/keys :req [:permission/code]
            :opt [:permission/name
                  :permission/description])))

(defmethod event-type :district-designer/remove-permission [_]
  (s/merge
    ::event-base
    (s/keys :req [:permission/code])))


(defmethod event-type :district-designer/add-tag-group [_]
  (s/merge
    ::event-base
    (s/keys :req [:tag-group/uuid
                  :tag-group/name
                  :tag-group/district-origin
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
  (s/merge
    ::event-base
    (s/keys :req [:tag-group/uuid :tag-group/tags])))


(defmethod event-type :district-designer/district-update-theme [_]
  (s/merge
    ::event-base
    (s/keys :req [:district/address
                  :theme/code]
            :opt [:district-theme/settings
                  :district-theme/css
                  :district-theme/less
                  :district-theme/active?])))


(defmethod event-type :district-designer/district-activate-module [_]
  (s/merge
    ::event-base
    (s/keys :req [:district/address :module/code])))

(defmethod event-type :district-designer/district-deactivate-module [_]
  (s/merge
    ::event-base
    (s/keys :req [:district/address :module/code])))


(defmethod event-type :district-designer/district-add-ui-component [_]
  (s/merge
    ::event-base
    (s/keys :req [:district/address
                  :ui-component/uuid
                  :ui-component/name
                  :ui-component/type
                  :ui-component/children
                  :ui-component/settings
                  :ui-component/files])))


(defmethod event-type :district-designer/district-update-ui-component [_]
  (s/merge
    ::event-base
    (s/keys :req [:district/address
                  :ui-component/uuid]
            :opt [:ui-component/name
                  :ui-component/type
                  :ui-component/children
                  :ui-component/settings
                  :ui-component/files])))


(defmethod event-type :district-designer/district-remove-ui-component [_]
  (s/merge
    ::event-base
    (s/keys :req [:district/address
                  :ui-component/uuid])))