(ns district-designer.shared.spec
  (:require [cljs.spec.alpha :as s]))

(defn address? [x]
  (and (string? x) (= 42 (count x))))

(defn ipfs-hash? [x]
  (and (string? x) (= 46 (count x))))

(defn edn? [x]
  (string? x))

(s/def ::event keyword?)
(s/def ::sender address?)

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


(s/def :tag-group/uuid uuid?)
(s/def :tag-group/name string?)
(s/def :tag-group/users-allowed-adding-tags? boolean?)
(s/def :tag-group/global-enabled? boolean?)
(s/def :tag-group/tags (s/coll-of string?))


(s/def :district-designer/add-dd-proxy-factory
  (s/keys :req-un [::event ::sender]
          :req [:smart-contract/address :smart-contract/abi]))

(s/def :district-designer/add-district-designer :district-designer/add-dd-proxy-factory)

(s/def :district-designer/add-module
  (s/keys :req-un [::event ::sender]
          :req [:module/id
                :module/name
                :module/logo
                :module/owner
                :module/description
                :module/preview-images]))


(s/def :district-designer/update-module
  (s/keys :req-un [::event ::sender]
          :req [:module/id]
          :opt [:module/name
                :module/logo
                :module/owner
                :module/description
                :module/preview-images]))

(s/def :district-designer/remove-module
  (s/keys :req-un [::event ::sender]
          :req [:module/id]))


(s/def :district-designer/add-wizard
  (s/keys :req-un [::event ::sender]
          :req [:wizard/id
                :wizard/name
                :wizard/logo
                :wizard/owner
                :wizard/description
                :wizard/preview-images]))


(s/def :district-designer/update-wizard
  (s/keys :req-un [::event ::sender]
          :req [:wizard/id]
          :opt [:wizard/name
                :wizard/logo
                :wizard/owner
                :wizard/description
                :wizard/preview-images]))

(s/def :district-designer/remove-wizard
  (s/keys :req-un [::event ::sender]
          :req [:wizard/id]))


(s/def :district-designer/add-theme
  (s/keys :req-un [::event ::sender]
          :req [:theme/id
                :theme/name
                :theme/owner
                :theme/description
                :theme/default-settings
                :theme/preview-images
                :theme/files]))


(s/def :district-designer/update-theme
  (s/keys :req-un [::event ::sender]
          :req [:theme/id]
          :opt [:theme/name
                :theme/owner
                :theme/description
                :theme/default-settings
                :theme/preview-images
                :theme/files]))

(s/def :district-designer/remove-theme
  (s/keys :req-un [::event ::sender]
          :req [:theme/id]))


(s/def :district-designer/add-tag-group
  (s/keys :req-un [::event ::sender]
          :req [:tag-group/uuid
                :tag-group/name
                :tag-group/users-allowed-adding-tags?
                :tag-group/global-enabled?]))


(s/def :district-designer/update-tag-group
  (s/keys :req-un [::event ::sender]
          :req [:tag-group/uuid]
          :opt [:tag-group/name
                :tag-group/users-allowed-adding-tags?
                :tag-group/global-enabled?]))


(s/def :district-designer/add-tags
  (s/keys :req-un [::event ::sender :tag-group/tags]
          :req [:tag-group/uuid]))


(s/def :district-designer/remove-tags :district-designer/add-tags)


(s/def :district/update-theme
  (s/keys :req-un [::event ::sender]
          :req [:district/uuid]
          :opt [:district/theme
                :district/theme-settings]))


(s/def :district/update-styles
  (s/keys :req-un [::event ::sender]
          :req [:district/uuid]
          :opt [:district/less-file
                :district/css-file]))


(s/def :district/update-styles
  (s/keys :req-un [::event ::sender]
          :req [:district/uuid]
          :opt [:district/less-file
                :district/css-file]))


(s/def :district/add-module
  (s/keys :req-un [::event ::sender]
          :req [:district/uuid :module/id]))

(s/def :district/remove-module :district/add-module)

(s/def :district/add-page
  (s/keys :req-un [::event ::sender]
          :req [:district/uuid :ui-component/uuid]))

(s/def :district/remove-page :district/add-page)

(s/def :district/add-ui-component
  (s/keys :req-un [::event ::sender]
          :req [:district/uuid
                :ui-component/uuid
                :ui-component/name
                :ui-component/type
                :ui-component/children
                :ui-component/settings
                :ui-component/files]))


(s/def :district/update-ui-component
  (s/keys :req-un [::event ::sender]
          :req [:district/uuid
                :ui-component/uuid]
          :opt [:ui-component/name
                :ui-component/type
                :ui-component/children
                :ui-component/settings
                :ui-component/files]))


(s/def :district/remove-ui-component
  (s/keys :req-un [::event ::sender]
          :req [:district/uuid
                :ui-component/uuid]))


(s/def :district/add-database-view
  (s/keys :req-un [::event ::sender]
          :req [:district/uuid
                :data-view/uuid
                :data-view/name
                :data-view/settings]))


(s/def :district/update-datadatabase-view
  (s/keys :req-un [::event ::sender]
          :req [:district/uuid
                :data-view/uuid]
          :opt [:data-view/name
                :data-view/settings]))


(s/def :district/remove-datadatabase-view
  (s/keys :req-un [::event ::sender]
          :req [:district/uuid
                :data-view/uuid]))

(s/def :district/add-statistics-view :district/add-database-view)
(s/def :district/update-statistics-view :district/update-datadatabase-view)
(s/def :district/remove-statistics-view :district/remove-datadatabase-view)

(comment
  (s/valid? :smart-contract/address "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8")
  (s/valid? :smart-contract/abi {:file/hash "QmW2WQi7j6c7UgJTarActp7tDNikE4B2qXtFCfLPdsgaTQ"
                                 :file/name "abc"})

  (s/valid? :district-designer/add-dd-proxy-factory
            {:event :district-designer/add-dd-proxy-factory
             :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
             :smart-contract/address "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
             :smart-contract/abi {:file/hash "QmW2WQi7j6c7UgJTarActp7tDNikE4B2qXtFCfLPdsgaTQ"
                                  :file/name "abc"}})


  (s/valid? :district-designer/add-district-designer
            {:event :district-designer/add-district-designer
             :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
             :smart-contract/address "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
             :smart-contract/abi {:file/hash "QmW2WQi7j6c7UgJTarActp7tDNikE4B2qXtFCfLPdsgaTQ"
                                  :file/name "abc"}})

  (s/valid? :district-designer/add-module
            {:event :district-designer/add-module
             :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
             :module/id "district_designer"
             :module/name "District Designer"
             :module/logo "QmW2WQi7j6c7UgJTarActp7tDNikE4B2qXtFCfLPdsgaTQ"
             :module/owner "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
             :module/description "abc"
             :module/preview-images [{:file/hash "QmW2WQi7j6c7UgJTarActp7tDNikE4B2qXtFCfLPdsgaTQ"}]})


  (s/valid? :district-designer/update-module
            {:event :district-designer/update-module
             :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
             :module/id "district_designer"
             :module/name "District Designer"})


  (s/valid? :district-designer/remove-module
            {:event :district-designer/remove-module
             :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
             :module/id "district_designer"})


  (s/valid? :district-designer/add-wizard
            {:event :district-designer/add-wizard
             :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
             :wizard/id "collectibles_marketplace"
             :wizard/name "Collectibles Marketplace"
             :wizard/logo "QmW2WQi7j6c7UgJTarActp7tDNikE4B2qXtFCfLPdsgaTQ"
             :wizard/owner "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
             :wizard/description "abc"
             :wizard/preview-images [{:file/hash "QmW2WQi7j6c7UgJTarActp7tDNikE4B2qXtFCfLPdsgaTQ"}]})


  (s/valid? :district-designer/update-wizard
            {:event :district-designer/update-wizard
             :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
             :wizard/id "collectibles_marketplace"
             :wizard/name "Collectibles Marketplace"})


  (s/valid? :district-designer/remove-wizard
            {:event :district-designer/remove-wizard
             :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
             :wizard/id "collectibles_marketplace"})

  (s/valid? :district-designer/add-theme
            {:event :district-designer/add-theme
             :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
             :theme/id "some_theme"
             :theme/name "Some Theme"
             :theme/owner "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
             :theme/description "abc"
             :theme/default-settings "{:a 1 :b 2}"
             :theme/files [{:file/hash "QmW2WQi7j6c7UgJTarActp7tDNikE4B2qXtFCfLPdsgaTQ"}]
             :theme/preview-images [{:file/hash "QmW2WQi7j6c7UgJTarActp7tDNikE4B2qXtFCfLPdsgaTQ"}]})


  (s/valid? :district-designer/update-theme
            {:event :district-designer/update-theme
             :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
             :theme/id "some_theme"
             :theme/name "Some Theme"})


  (s/valid? :district-designer/remove-theme
            {:event :district-designer/remove-theme
             :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
             :theme/id "some_theme"})


  (s/valid? :district-designer/add-tag-group
            {:event :district-designer/add-tag-group
             :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
             :tag-group/uuid #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"
             :tag-group/name "abc"
             :tag-group/users-allowed-adding-tags? true
             :tag-group/global-enabled? true})


  (s/valid? :district-designer/add-tags
            {:event :district-designer/add-tag-group
             :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
             :tag-group/uuid #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"
             :tags ["abc" "def"]})


  (s/valid? :district/update-theme
            {:event :district/update-theme
             :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
             :district/uuid #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"
             :district/theme "some_theme"
             :district/theme-settings "{:a 1 :b 2}"})


  (s/valid? :district/update-styles
            {:event :district/update-styles
             :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
             :district/uuid #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"
             :district/less-file {:file/hash "QmW2WQi7j6c7UgJTarActp7tDNikE4B2qXtFCfLPdsgaTQ"
                                  :file/name "abc"}
             :district/css-file {:file/hash "QmW2WQi7j6c7UgJTarActp7tDNikE4B2qXtFCfLPdsgaTQ"
                                 :file/name "abc"}})


  (s/valid? :district/add-module
            {:event :district/add-module
             :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
             :district/uuid #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"
             :module/id "marketplace"})


  (s/valid? :district/remove-module
            {:event :district/remove-module
             :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
             :district/uuid #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"
             :module/id "marketplace"})


  (s/valid? :district/add-page
            {:event :district/add-page
             :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
             :district/uuid #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"
             :ui-component/uuid #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"})


  (s/valid? :district/remove-page
            {:event :district/remove-page
             :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
             :district/uuid #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"
             :ui-component/uuid #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"})


  (s/valid? :district/add-ui-component
            {:event :district/add-ui-component
             :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
             :district/uuid #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"
             :ui-component/uuid #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"
             :ui-component/name "abc"
             :ui-component/type "abc"
             :ui-component/children [#uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"]
             :ui-component/settings "<str>"
             :ui-component/files [{:file/hash "QmW2WQi7j6c7UgJTarActp7tDNikE4B2qXtFCfLPdsgaTQ"
                                   :file/name "abc"}]})


  (s/valid? :district/update-ui-component
            {:event :district/update-ui-component
             :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
             :district/uuid #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"
             :ui-component/uuid #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"
             :ui-component/name "abc"
             :ui-component/type "abc"
             :ui-component/children [#uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"]
             :ui-component/settings "{:a 1 :b 2}"
             :ui-component/files [{:file/hash "QmW2WQi7j6c7UgJTarActp7tDNikE4B2qXtFCfLPdsgaTQ"
                                   :file/name "abc"}]})


  (s/valid? :district/remove-ui-component
            {:event :district/remove-ui-component
             :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
             :district/uuid #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"
             :ui-component/uuid #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"})




  )