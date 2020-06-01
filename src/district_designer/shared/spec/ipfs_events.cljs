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

(s/def ::events (s/coll-of (s/keys :req-un [::event ::timestamp])))
(s/def ::event keyword?)
(s/def ::sender address?)
(s/def ::timestamp pos-int?)
(s/def ::version pos-int?)

(s/def ::event-base (s/keys :req-un [::event ::sender ::timestamp]))

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

(s/def :district-designer/events-batch
  (s/keys :req-un [::event ::sender ::events]))

(s/def :district-designer/add-smart-contract
  (s/merge
    ::event-base
    (s/keys :req [:smart-contract/address :smart-contract/abi])))

(s/def :district-designer/add-dd-proxy-factory :district-designer/add-smart-contract)
(s/def :district-designer/add-district-designer :district-designer/add-smart-contract)

(s/def :district-designer/add-module
  (s/merge
    ::event-base
    (s/keys :req [:module/id
                  :module/name
                  :module/logo
                  :module/owner
                  :module/description
                  :module/preview-images])))


(s/def :district-designer/update-module
  (s/merge
    ::event-base
    (s/keys :req [:module/id]
            :opt [:module/name
                  :module/logo
                  :module/owner
                  :module/description
                  :module/preview-images])))

(s/def :district-designer/remove-module
  (s/merge
    ::event-base
    (s/keys :req [:module/id])))


(s/def :district-designer/add-wizard
  (s/merge
    ::event-base
    (s/keys :req [:wizard/id
                  :wizard/name
                  :wizard/logo
                  :wizard/owner
                  :wizard/description
                  :wizard/preview-images])))


(s/def :district-designer/update-wizard
  (s/merge
    ::event-base
    (s/keys :req [:wizard/id]
            :opt [:wizard/name
                  :wizard/logo
                  :wizard/owner
                  :wizard/description
                  :wizard/preview-images])))

(s/def :district-designer/remove-wizard
  (s/merge
    ::event-base
    (s/keys :req [:wizard/id])))


(s/def :district-designer/add-theme
  (s/merge
    ::event-base
    (s/keys :req [:theme/id
                  :theme/name
                  :theme/owner
                  :theme/description
                  :theme/default-settings
                  :theme/preview-images
                  :theme/files])))


(s/def :district-designer/update-theme
  (s/merge
    ::event-base
    (s/keys :req [:theme/id]
            :opt [:theme/name
                  :theme/owner
                  :theme/description
                  :theme/default-settings
                  :theme/preview-images
                  :theme/files])))

(s/def :district-designer/remove-theme
  (s/merge
    ::event-base
    (s/keys :req [:theme/id])))

(s/def :permission/id string?)
(s/def :permission/name string?)
(s/def :permission/description string?)

(s/def :district-designer/add-permission
  (s/merge
    ::event-base
    (s/keys :req [:permission/id
                  :permission/name
                  :permission/description])))


(s/def :district-designer/update-permission
  (s/merge
    ::event-base
    (s/keys :req [:permission/id]
            :opt [:permission/name
                  :permission/description])))

(s/def :district-designer/remove-permission
  (s/merge
    ::event-base
    (s/keys :req [:permission/id])))


(s/def :district-designer/add-tag-group
  (s/merge
    ::event-base
    (s/keys :req [:tag-group/uuid
                  :tag-group/name
                  :tag-group/users-allowed-adding-tags?
                  :tag-group/global-enabled?])))


(s/def :district-designer/update-tag-group
  (s/merge
    ::event-base
    (s/keys :req [:tag-group/uuid]
            :opt [:tag-group/name
                  :tag-group/users-allowed-adding-tags?
                  :tag-group/global-enabled?])))


(s/def :district-designer/remove-tag-group
  (s/merge
    ::event-base
    (s/keys :req [:tag-group/uuid])))


(s/def :district-designer/add-tags
  (s/merge
    ::event-base
    (s/keys :req [:tag-group/uuid :tag-group/tags])))


(s/def :district-designer/remove-tags :district-designer/add-tags)


(s/def :district/update-theme
  (s/merge
    ::event-base
    (s/keys :req [:district/uuid]
            :opt [:district/theme
                  :district/theme-settings])))


(s/def :district/update-styles
  (s/merge
    ::event-base
    (s/keys :req [:district/uuid]
            :opt [:district/less-file
                  :district/css-file])))


(s/def :district/add-module
  (s/merge
    ::event-base
    (s/keys :req [:district/uuid :module/id])))

(s/def :district/remove-module :district/add-module)


(s/def :district/add-ui-component
  (s/merge
    ::event-base
    (s/keys :req [:district/uuid
                  :ui-component/uuid
                  :ui-component/name
                  :ui-component/type
                  :ui-component/children
                  :ui-component/settings
                  :ui-component/files])))


(s/def :district/update-ui-component
  (s/merge
    ::event-base
    (s/keys :req [:district/uuid
                  :ui-component/uuid]
            :opt [:ui-component/name
                  :ui-component/type
                  :ui-component/children
                  :ui-component/settings
                  :ui-component/files])))


(s/def :district/remove-ui-component
  (s/merge
    ::event-base
    (s/keys :req [:district/uuid
                  :ui-component/uuid])))


(s/def :district/add-database-view
  (s/merge
    ::event-base
    (s/keys :req [:district/uuid
                  :data-view/uuid
                  :data-view/name
                  :data-view/settings])))


(s/def :district/update-datadatabase-view
  (s/merge
    ::event-base
    (s/keys :req [:district/uuid
                  :data-view/uuid]
            :opt [:data-view/name
                  :data-view/settings])))


(s/def :district/remove-datadatabase-view
  (s/merge
    ::event-base
    (s/keys :req [:district/uuid
                  :data-view/uuid])))

(s/def :district/add-statistics-view :district/add-database-view)
(s/def :district/update-statistics-view :district/update-datadatabase-view)
(s/def :district/remove-statistics-view :district/remove-datadatabase-view)