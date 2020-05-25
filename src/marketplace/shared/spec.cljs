(ns marketplace.shared.spec
  (:require
    [cljs.spec.alpha :as s]
    [district-designer.shared.spec :refer [ipfs-hash? address? edn?]]
    [users.shared.spec]))

(defn rating? [x]
  (and (number? x) (>= x 0) (<= x 5)))

(s/def :offer-group/uuid uuid?)
(s/def :offer-group/name string?)
(s/def :offer-group/offer-field-configs (s/coll-of :field-config/field-config))
(s/def :offer-group/response-field-configs (s/coll-of :field-config/field-config))
(s/def :offer-group/global-enabled? boolean?)
(s/def :offer-group/global-logo :file/file)
(s/def :offer-group/global-description string?)

(s/def :offer/uuid uuid?)
(s/def :offer-response/uuid uuid?)

(s/def :feedback/uuid uuid?)
(s/def :feedback/rating rating?)
(s/def :feedback/text string?)

(s/def :marketplace/add-offer-group-factory :district-designer/add-dd-proxy-factory)


(s/def :marketplace/update-offer-group
  (s/keys :req-un [:district-designer.server.spec/event
                   :district-designer.server.spec/sender]
          :req [:offer-group/uuid]
          :opt [:offer-group/name
                :offer-group/offer-field-configs
                :offer-group/response-field-configs
                :offer-group/global-enabled?
                :offer-group/global-logo
                :offer-group/global-description]))


(s/def :marketplace/add-district-offer-group
  (s/keys :req-un [:district-designer.server.spec/event
                   :district-designer.server.spec/sender]
          :req [:district/uuid
                :offer-group/uuid]))


(s/def :marketplace/remove-district-offer-group :marketplace/add-district-offer-group)


(s/def :marketplace/update-offer
  (s/keys :req-un [:district-designer.server.spec/event
                   :district-designer.server.spec/sender]
          :req [:offer/uuid]))


(s/def :marketplace/add-message
  (s/keys :req-un [:district-designer.server.spec/event
                   :district-designer.server.spec/sender]
          :req [:offer-response/uuid
                :message/uuid
                :message/receiver
                :message/text]
          :opt [:message/files]))


(s/def :marketplace/add-feedback
  (s/keys :req-un [:district-designer.server.spec/event
                   :district-designer.server.spec/sender]
          :req [:offer-response/uuid
                :feedback/uuid
                :feedback/rating
                :feedback/text]))


(s/def :marketplace/update-feedback :marketplace/add-feedback)


(comment
  (s/valid? :marketplace/update-offer-group
            {:event :marketplace/update-offer-group
             :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
             :offer-group/uuid #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"
             :offer-group/name "abc"
             :offer-group/offer-field-configs [{:field-config/uuid #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"
                                                :field-config/name "abc"
                                                :field-config/namespace "abc"
                                                :field-config/type "abc"
                                                :field-config/settings "{:a 1 :b 2}"}]

             :offer-group/response-field-configs [{:field-config/uuid #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"
                                                   :field-config/name "abc"
                                                   :field-config/namespace "abc"
                                                   :field-config/type "abc"
                                                   :field-config/settings "{:a 1 :b 2}"}]

             :offer-group/global-enabled? true
             :offer-group/global-logo {:file/hash "QmW2WQi7j6c7UgJTarActp7tDNikE4B2qXtFCfLPdsgaTQ"
                                       :file/name "abc"}
             :offer-group/global-description "abc"})


  (s/valid? :marketplace/add-district-offer-group
            {:event :marketplace/add-district-offer-group
             :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
             :district/uuid #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"
             :offer-group/uuid #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"})


  (s/valid? :marketplace/remove-district-offer-group
            {:event :marketplace/remove-district-offer-group
             :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
             :district/uuid #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"
             :offer-group/uuid #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"})


  (s/valid? :marketplace/update-offer
            {:event :marketplace/update-offer
             :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
             :offer/uuid #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"})


  (s/valid? :marketplace/add-message
            {:event :marketplace/add-message
             :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
             :offer-response/uuid #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"
             :message/uuid #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"
             :message/receiver "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
             :message/text "abc"
             :message/files [{:file/hash "QmW2WQi7j6c7UgJTarActp7tDNikE4B2qXtFCfLPdsgaTQ"
                              :file/name "abc"}]})


  (s/valid? :marketplace/add-feedback
            {:event :marketplace/add-feedback
             :sender "0xEA674fdDe714fd979de3EdF0F56AA9716B898ec8"
             :offer-response/uuid #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"
             :feedback/uuid #uuid "e151c39c-9b81-4efd-a9ac-860956e008a8"
             :feedback/rating 4.5
             :feedback/text "abc"})




  )