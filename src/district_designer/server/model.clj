(ns district-designer.server.model
  (:require
    [hodur-engine.core :as hodur]
    [hodur-graphviz-schema.core :as hodur-graphviz]))

(def meta-db (hodur/init-schema
               '[^:graphviz/tag
                 default

                 ^:enum
                 ProxyType
                 [OWNER_PROXY DISTRICT_ADMIN_PROXY]

                 SmartContract
                 [^{:type String
                    :datomic/unique :db.unique/identity}
                  address

                  ^{:type String
                    :datomic/index true}
                  name

                  ^{:type Integer
                    :datomic/index true}
                  version

                  ^{:type File}
                  abi

                  ^{:type Boolean}
                  proxy?

                  ^{:type ProxyType}
                  proxy-type

                  ^{:type String}
                  target

                  ^{:type String}
                  owner

                  ^{:type District}
                  district]


                 SmartContractEvents
                 [^{:type SmartContract}
                  smart-contract

                  ^{:type String
                    :datomic/index true}
                  event-key

                  ^{:type Integer}
                  last-log-index

                  ^{:type Integer}
                  last-block-number]


                 IpfsEvents
                 [^{:type String
                    :datomic/unique :db.unique/identity}
                  last-hash]


                 District
                 [^{:type ID
                    :datomic/unique :db.unique/identity}
                  uuid

                  ^{:type String}
                  name

                  ^{:type String
                    :datomic/unique :db.unique/identity}
                  subdomain

                  ^{:type String}
                  title

                  ^{:type String}
                  description

                  ^{:type File}
                  logo

                  ^{:type File}
                  cover-image

                  ^{:type File}
                  favicon

                  ^{:type String}
                  ga-tracking-id

                  ^{:type DateTime}
                  created-on

                  ^{:type DateTime}
                  updated-on

                  ^{:type String}
                  treasury

                  ^{:type UserRole
                    :cardinality [1 n]}
                  user-roles

                  ^{:type UserRole}
                  admin-user-role

                  ^{:type PermissionUserRole
                    :cardinality [1 n]}
                  permissions-user-roles


                  ^{:type Theme}
                  theme

                  ^{:type String}
                  theme-settings

                  ^{:type File}
                  less-file

                  ^{:type File}
                  css-file

                  ^{:type Module
                    :cardinality [0 n]}
                  modules

                  ^{:type UIComponent
                    :cardinality [0 n]}
                  pages

                  ^{:type UIComponent
                    :cardinality [0 n]}
                  ui-components

                  ^{:type UserProfile
                    :cardinality [0 n]}
                  user-profiles

                  ^{:type OfferGroup
                    :cardinality [0 n]}
                  offers-groups

                  ^{:type TokenContract
                    :cardinality [0 n]}
                  token-contracts

                  ^{:type DataView
                    :cardinality [0 n]}
                  database-views

                  ^{:type DataView
                    :cardinality [0 n]}
                  statistics-views]

                 UserRole
                 [^{:type ID
                    :datomic/unique :db.unique/identity}
                  uuid

                  ^{:type String}
                  name

                  ^{:type String
                    :cardinality [0 n]}
                  addresses]


                 Permission
                 [^{:type String
                    :datomic/unique :db.unique/identity}
                  id

                  ^{:type String}
                  name

                  ^{:type String}
                  description]


                 PermissionUserRoles
                 [^{:type Permission}
                  permission

                  ^{:type UserRole
                    :cardinality [0 n]}
                  user-roles]


                 File
                 [^{:type String
                    :datomic/unique :db.unique/identity}
                  hash

                  ^{:type String}
                  name

                  ^{:type Boolean}
                  encrypted?

                  ^{:type String}
                  decryptable-by]


                 Theme
                 [^{:type String
                    :datomic/unique :db.unique/identity}
                  id

                  ^{:type String
                    :datomic/unique :db.unique/identity}
                  name

                  ^{:type String}
                  description

                  ^{:type String}
                  owner

                  ^{:type String}
                  default-settings

                  ^{:type File
                    :cardinality [1 n]}
                  preview-images

                  ^{:type File
                    :cardinality [1 n]}
                  files

                  ^{:type Integer}
                  installs-count]


                 Module
                 [^{:type String
                    :datomic/unique :db.unique/identity}
                  id

                  ^{:type String
                    :datomic/unique :db.unique/identity}
                  name

                  ^{:type File}
                  logo

                  ^{:type String}
                  owner

                  ^{:type String}
                  description

                  ^{:type File
                    :cardinality [1 n]}
                  preview-images

                  ^{:type Integer}
                  installs-count]


                 Wizard
                 [^{:type String
                    :datomic/unique :db.unique/identity}
                  id

                  ^{:type String
                    :datomic/unique :db.unique/identity}
                  name

                  ^{:type File}
                  logo

                  ^{:type String}
                  owner

                  ^{:type String}
                  description

                  ^{:type File
                    :cardinality [1 n]}
                  preview-images

                  ^{:type Integer}
                  completions-count]


                 UIComponent
                 [^{:type ID
                    :datomic/unique :db.unique/identity}
                  uuid

                  ^{:type String}
                  name

                  ^{:type String}
                  type

                  ^{:type UIComponent
                    :cardinality [0 n]}
                  children

                  ^{:type String}
                  settings]


                 DataView
                 [^{:type ID
                    :datomic/unique :db.unique/identity}
                  uuid

                  ^{:type String}
                  name

                  ^{:type String}
                  settings]


                 FieldConfig
                 [^{:type ID
                    :datomic/unique :db.unique/identity}
                  uuid

                  ^{:type String}
                  name

                  ^{:type String}
                  namespace

                  ^{:type String}
                  type

                  ^{:type String}
                  settings]


                 UserProfile
                 [^{:type ID
                    :datomic/unique :db.unique/identity}
                  uuid

                  ^{:type String}
                  name

                  ^{:type FieldConfig
                    :cardinality [1 n]}
                  field-configs

                  ^{:type Boolean}
                  global-enabled?

                  ^{:type File}
                  global-logo

                  ^{:type String}
                  global-description

                  ^{:type Integer}
                  global-imports-count]


                 User
                 [^{:type String
                    :datomic/unique :db.unique/identity}
                  address

                  ^{:type Float}
                  global-rating

                  ^{:type Integer
                    :default 0}
                  global-ratings-count

                  ^{:type UnknownType}
                  field-uuid]


                 ^:enum
                 TradeAssetCategory
                 [ETH ERC20 ERC721 ERC1155 DELIVERABLE]

                 ^:enum
                 OfferType
                 [FIXED_PRICE DYNAMIC_PRICE HIGEST_BID_WINS OFFERER_PICKS_WINNER MULTIPLE_FIXED_PRICES]

                 TradeAsset
                 [^{:type TokenContract}
                  token-contract

                  ^{:type TradeAssetCategory}
                  category]



                 OfferGroup
                 [^{:type ID
                    :datomic/unique :db.unique/identity}
                  uuid

                  ^{:type String}
                  name

                  ^{:type SmartContract}
                  smart-contract

                  ^{:type TradeAsset
                    :cardinality [0 n]}
                  assets-to-offer

                  ^{:type TradeAsset
                    :cardinality [0 n]}
                  assets-to-request

                  ^{:type OfferType}
                  offer-type

                  ^{:type FieldConfig
                    :cardinality [1 n]}
                  offer-field-configs

                  ^{:type FieldConfig
                    :cardinality [0 n]}
                  response-field-configs

                  ^{:type Integer
                    :datomic/type :db.type/bigint
                    :default 0}
                  fee-create-offer

                  ^{:type Integer
                    :datomic/type :db.type/bigint
                    :default 0}
                  fee-offer-response

                  ^{:type PermissionUserRoles}
                  permission-create-offer

                  ^{:type PermissionUserRoles}
                  permission-offer-response

                  ^{:type Boolean}
                  global-enabled?

                  ^{:type File}
                  global-logo

                  ^{:type String}
                  global-description

                  ^{:type Integer}
                  global-imports-count

                  ^{:type OfferGroupUserRating
                    :cardinality [0 n]}
                  user-ratings

                  ^{:type String
                    :cardinality [0 n]}
                  dispute-resolvers]


                 OfferGroupUserRating
                 [^{:type User}
                  user

                  ^{:type Float}
                  rating

                  ^{:type Integer
                    :default 0}
                  ratings-count]


                 Offer
                 [^{:type ID
                    :datomic/unique :db.unique/identity}
                  uuid

                  ^{:type SmartContract}
                  smart-contract

                  ^{:type OfferGroup}
                  offers-group

                  ^{:type UnknownType}
                  field-uuid

                  ^{:type OfferType}
                  type

                  ^{:type TradeValue}
                  offered-value

                  ^{:type TradeValue
                    :cardinality [0 n]}
                  requested-values

                  ^{:type TradeAuction}
                  requested-auction

                  ^{:type OfferResponse}
                  auction-highest-bid-response

                  ^{:type OfferResponse
                    :cardinality [0 n]}
                  offer-responses

                  ^{:type DateTime}
                  created-on

                  ^{:type User}
                  offerer

                  ^{:type Boolean}
                  closed?

                  ^{:type Boolean}
                  close-after-first-trade?]


                 OfferResponse
                 [^{:type ID
                    :datomic/unique :db.unique/identity}
                  uuid

                  ^{:type User}
                  respondent

                  ^{:type DateTime}
                  created-on

                  ^{:type DateTime}
                  updated-on

                  ^{:type DateTime}
                  canceled-on

                  ^{:type DateTime}
                  traded-on

                  ^{:type UnknownType}
                  field-uuid

                  ^{:type TradeValue}
                  offerer-traded-value

                  ^{:type TradeValue}
                  respondent-traded-value

                  ^{:type Feedback}
                  offerer-feedback

                  ^{:type Feedback}
                  respondent-feedback

                  ^{:type Message
                    :cardinality [0 n]}
                  messages

                  ^{:type String
                    :cardinality [0 n]}
                  dispute-resolvers

                  ^{:type OfferDispute}
                  dispute]


                 OfferDispute
                 [^{:type DateTime}
                  raised-on

                  ^{:type DateTime}
                  resolved-on

                  ^{:type String}
                  resolved-by

                  ^{:type TradeValue}
                  resolved-value-for-offerer

                  ^{:type TradeValue}
                  resolved-value-for-respondent]


                 TradeValue
                 [^{:type TradeAsset}
                  asset

                  ^{:type Integer
                    :datomic/type :db.type/bigint}
                  amount

                  ^{:type NFTToken}
                  nft-token

                  ^{:type String}
                  textual-repr]


                 TradeAuction
                 [^{:type TradeAsset}
                  asset

                  ^{:type Integer
                    :datomic/type :db.type/bigint}
                  start-amount

                  ^{:type Integer
                    :datomic/type :db.type/bigint}
                  end-amount

                  ^{:type Integer
                    :datomic/type :db.type/bigint}
                  min-amount

                  ^{:type Integer
                    :datomic/type :db.type/bigint}
                  min-bid-step

                  ^{:type Integer}
                  duration]


                 Feedback
                 [^{:type ID
                    :datomic/unique :db.unique/identity}
                  uuid

                  ^{:type User}
                  sender

                  ^{:type User}
                  receiver

                  ^{:type Float}
                  rating

                  ^{:type DateTime}
                  created-on

                  ^{:type DateTime}
                  updated-on

                  ^{:type String}
                  text]


                 Message
                 [^{:type ID
                    :datomic/unique :db.unique/identity}
                  uuid

                  ^{:type User}
                  sender

                  ^{:type User}
                  receiver

                  ^{:type String}
                  type

                  ^{:type String}
                  text

                  ^{:type File
                    :cardinality [0 n]}
                  files

                  ^{:type DateTime}
                  created-on]


                 ^:enum
                 TokenType
                 [ERC20 ERC721 ERC1155]


                 TokenContract
                 [^{:type ID
                    :datomic/unique :db.unique/identity}
                  uuid

                  ^{:type SmartContract}
                  smart-contract

                  ^{:type String}
                  name

                  ^{:type TokenType}
                  type

                  ^{:type String}
                  symbol

                  ^{:type Integer}
                  decimals

                  ^{:type String}
                  owner

                  ^{:type String}
                  added-by

                  ^{:type String}
                  metadata-format

                  ^{:type String}
                  metadata-format-settings

                  ^{:type Integer
                    :datomic/type :db.type/bigint}
                  total-supply

                  ^{:type NFTToken
                    :cardinality [0 n]}
                  nft-tokens

                  ^{:type DateTime}
                  reported-misconfig-on

                  ^{:type String}
                  reported-misconfig-comment

                  ^{:type String}
                  reported-misconfig-by

                  ^{:type DateTime}
                  reported-misconfig-resolved-on

                  ^{:type String}
                  reported-misconfig-resolved-by]


                 NFTToken
                 [^{:type ID
                    :datomic/unique :db.unique/identity}
                  uuid

                  ^{:type Integer}
                  id

                  ^{:type Integer
                    :datomic/type :db.type/bigint}
                  total-supply

                  ^{:type UnknownType}
                  field-uuid]


                 ^:enum
                 TCRType
                 [INITIAL_CHALLENGE_PERIOD CHALLENGEABLE_ANYTIME]

                 ^:enum
                 TCRRegEntryRepresentationCategory
                 [ERC721 ERC1155 NO_TOKEN]


                 TCR
                 [^{:type ID
                    :datomic/unique :db.unique/identity}
                  uuid

                  ^{:type SmartContract}
                  smart-contract

                  ^{:type TCRType}
                  type

                  ^{:type TokenContract}
                  voting-token-contract

                  ^{:type TCRRegEntryRepresentationCategory}
                  reg-entry-representation-category

                  ^{:type TokenContract}
                  reg-entry-representation-token-contract

                  ^{:type FieldConfig
                    :cardinality [1 n]}
                  reg-entry-field-configs

                  ^{:type TCRRegEntry
                    :cardinality [0 n]}
                  reg-entries

                  ^{:type TCRParamChangeEntry
                    :cardinality [0 n]}
                  param-change-entries

                  ^{:type PermissionUserRoles}
                  permission-submit-reg-entry

                  ^{:type PermissionUserRoles}
                  permission-submit-param-change

                  ^{:type TCRParameters}
                  reg-entry-parameters

                  ^{:type TCRParameters}
                  param-change-entry-parameters

                  ^{:type DateTime}
                  created-on

                  ^{:type Boolean}
                  global-enabled?

                  ^{:type File}
                  global-logo

                  ^{:type String}
                  global-description

                  ^{:type Integer}
                  global-imports-count]


                 TCRRegEntry
                 [^{:type ID
                    :datomic/unique :db.unique/identity}
                  uuid

                  ^{:type SmartContract}
                  smart-contract

                  ^{:type UnknownType}
                  field-uuid

                  ^{:type TCRChallenge
                    :cardinality [0 n]}
                  challenges

                  ^{:type DateTime}
                  created-on]


                 TCRParamChangeEntry
                 [^{:type ID
                    :datomic/unique :db.unique/identity}
                  uuid

                  ^{:type SmartContract}
                  smart-contract

                  ^{:type String}
                  db

                  ^{:type String}
                  key

                  ^{:type String}
                  comment

                  ^{:type Integer
                    :datomic/type :db.type/bigint}
                  value

                  ^{:type Integer
                    :datomic/type :db.type/bigint}
                  original-value

                  ^{:type DateTime}
                  created-on

                  ^{:type DateTime}
                  applied-on

                  ^{:type DateTime}
                  creator-reward-claimed-on

                  ^{:type TCRChallenge
                    :cardinality [0 n]}
                  challenges]


                 TCRParameters
                 [^{:type Integer
                    :datomic/type :db.type/bigint}
                  deposit

                  ^{:type Integer}
                  challenge-period-duration

                  ^{:type Integer}
                  vote-commit-period-duration

                  ^{:type Integer}
                  vote-reveal-period-duration

                  ^{:type Integer}
                  challenge-deposit-dispensation

                  ^{:type Integer}
                  vote-quorum]


                 TCRChallenge
                 [^{:type ID
                    :datomic/unique :db.unique/identity}
                  uuid

                  ^{:type User}
                  challenger

                  ^{:type DateTime}
                  created-on

                  ^{:type DateTime}
                  challenge-period-end

                  ^{:type DateTime}
                  vote-commit-period-end

                  ^{:type DateTime}
                  vote-reveal-period-end

                  ^{:type Integer
                    :datomic/type :db.type/bigint}
                  votes-include

                  ^{:type Integer
                    :datomic/type :db.type/bigint}
                  votes-exclude

                  ^{:type Integer
                    :datomic/type :db.type/bigint}
                  votes-total

                  ^{:type TCRVote
                    :cardinality [0 n]}
                  votes

                  ^{:type Integer
                    :datomic/type :db.type/bigint}
                  reward-pool

                  ^{:type Integer
                    :datomic/type :db.type/bigint}
                  challenger-reward-amount

                  ^{:type DateTime}
                  challenger-reward-claimed-on

                  ^{:type String}
                  comment]


                 ^:enum
                 TCRVoteOption
                 [INCLUDE EXCLUDE NO_VOTE]


                 TCRVote
                 [^{:type ID
                    :datomic/unique :db.unique/identity}
                  uuid

                  ^{:type User}
                  voter

                  ^{:type TCRVoteOption}
                  option

                  ^{:type Integer
                    :datomic/type :db.type/bigint}
                  amount

                  ^{:type DateTime}
                  created-on

                  ^{:type DateTime}
                  revealed-on

                  ^{:type DateTime}
                  reward-claimed-on

                  ^{:type DateTime}
                  votes-reclaimed-on]]))

(def graphviz-schema (hodur-graphviz/schema meta-db))


(defn spit-diagram []
  (spit "./diagrams/database.dot" graphviz-schema))

(comment
  graphviz-schema
  (spit-diagram))


(def events
  [{:event :events-batch
    :sender "<addr>"
    :events [{:event "<str>"
              :action "<str>"}]}

   {:event :district-designer/add-dd-proxy-factory
    :sender "<addr>"
    :smart-contract/address "<addr>"
    :smart-contract/abi {:file/hash "<ipfs>"
                         :file/name "<str>"}}


   {:event :district-designer/add-district-designer
    :sender "<addr>"
    :smart-contract/address "<addr>"
    :smart-contract/abi {:file/hash "<ipfs>"
                         :file/name "<str>"}}

   {:event (or :district-designer/add-module :district-designer/update-module)
    :sender "<addr>"
    :module/id "<id>"
    :module/name "<str>"
    :module/logo "<ipfs>"
    :module/owner "<addr>"
    :module/description "<str>"
    :module/preview-images [{:file/hash "<ipfs>"
                             :file/name "<str>"}]}

   {:event :district-designer/remove-module
    :sender "<addr>"
    :module/id "<id>"}

   {:event (or :district-designer/add-wizard :district-designer/update-wizard)
    :sender "<addr>"
    :wizard/id "<str>"
    :wizard/name "<str>"
    :wizard/logo {:file/hash "<ipfs>"
                  :file/name "<str>"}
    :wizard/owner "<addr>"
    :wizard/description "<str>"
    :wizard/preview-images [{:file/hash "<ipfs>"
                             :file/name "<str>"}]}

   {:event :district-designer/remove-wizard
    :sender "<addr>"
    :wizard/id "<id>"}

   {:event (or :district-designer/add-theme :district-designer/update-theme)
    :sender "<addr>"
    :theme/id "<str>"
    :theme/name "<str>"
    :theme/owner "<addr>"
    :theme/description "<str>"
    :theme/default-settings "<edn>"
    :theme/files [{:file/hash "<ipfs>"
                   :file/name "<str>"}]}

   {:event :district-designer/remove-theme
    :sender "<addr>"
    :theme/id "tokens"}


   {:event :district/update-theme
    :sender "<addr>"
    :district/uuid "<uuid>"
    :district/theme "<uuid>"
    :district/theme-settings "<edn>"}


   {:event :district/update-styles
    :sender "<addr>"
    :district/uuid "<uuid>"
    :district/less-file {:file/hash "<ipfs>"
                         :file/name "<str>"}
    :district/css-file {:file/hash "<ipfs>"
                        :file/name "<str>"}}


   {:event :district/add-modules
    :sender "<addr>"
    :district/uuid "<uuid>"
    :modules ["<uuid>"]}


   {:event :district/remove-modules
    :sender "<addr>"
    :district/uuid "<uuid>"
    :modules ["<uuid>"]}


   {:event (or :district/add-pages :district/remove-pages)
    :sender "<addr>"
    :district/uuid "<uuid>"
    :pages ["<uuid>"]}


   {:event :district/add-ui-component
    :sender "<addr>"
    :district/uuid "<uuid>"
    :ui-component/uuid "<uuid>"
    :ui-component/name "<str>"
    :ui-component/type "<str>"
    :ui-component/children ["<uuid>"]
    :ui-component/settings "<edn>"}


   {:event :district/update-ui-component
    :sender "<addr>"
    :ui-component/uuid "<uuid>"
    :ui-component/name "<str>"
    :ui-component/type "<str>"
    :ui-component/children ["<uuid>"]
    :ui-component/settings "<edn>"}


   {:event :district/remove-ui-components
    :sender "<addr>"
    :district/uuid "<uuid>"
    :ui-component/uuid "<uuid>"}


   {:event (or :district/add-database-view :district/add-statistics-view)
    :sender "<addr>"
    :district/uuid "<uuid>"
    :data-view/uuid "<uuid>"
    :data-view/name "<str>"
    :data-view/settings "<edn>"}


   {:event (or :district/update-database-view :district/update-statistics-view)
    :sender "<addr>"
    :data-view/uuid "<uuid>"
    :data-view/name "<str>"
    :data-view/settings "<edn>"}


   {:event (or :district/remove-database-view :district/remove-statistics-view)
    :sender "<addr>"
    :district/uuid "<uuid>"
    :data-view/uuid "<uuid>"}


   {:event :tokens/add-erc20-token-factory
    :sender "<addr>"
    :smart-contract/address "<addr>"
    :smart-contract/abi {:file/hash "<ipfs>"
                         :file/name "<str>"}}


   {:event :tokens/add-erc721-token-factory
    :sender "<addr>"
    :smart-contract/address "<addr>"
    :smart-contract/abi {:file/hash "<ipfs>"
                         :file/name "<str>"}}


   {:event :tokens/add-erc1155-token-factory
    :sender "<addr>"
    :smart-contract/address "<addr>"
    :smart-contract/abi {:file/hash "<ipfs>"
                         :file/name "<str>"}}


   {:event :tokens/add-token-factory-events
    :sender "<addr>"
    :smart-contract/address "<addr>"
    :smart-contract/abi {:file/hash "<ipfs>"
                         :file/name "<str>"}}


   {:event :token/update-token-contract
    :sender "<addr>"
    :district/uuid "<uuid>"
    :token-contract/uuid "<uuid>"
    :token-contract/metadata-format "<str>"
    :token-contract/metadata-format-settings "<edn>"}


   {:event :tokens/remove-token-contract
    :sender "<addr>"
    :district/uuid "<uuid>"
    :token-contract/uuid "<uuid>"}


   {:event (or :tokens/add-district-token-contracts :tokens/remove-district-token-contracts)
    :sender "<addr>"
    :district/uuid "<uuid>"
    :token-contracts ["<uuid>"]}


   {:event :tokens/add-token-contract-misconfig-report
    :sender "<addr>"
    :district/uuid "<uuid>"
    :token-contract/uuid "<uuid>"
    :token-contract/reported-misconfig-comment "<str>"}


   {:event :tokens/resolve-token-contract-misconfig-report
    :sender "<addr>"
    :token-contract/uuid "<uuid>"}


   {:event :users/add-direct-message
    :sender "<addr>"
    :district/uuid "<uuid>"
    :message/uuid "<uuid>"
    :message/receiver "<address>"
    :message/text "<str>"
    :message/files [{:file/hash "<ipfs>"
                     :file/name "<str>"}]}


   {:event (or :users/add-user-profile :users/update-user-profile)
    :sender "<addr>"
    :district/uuid "<uuid>"
    :user-profile/uuid "<uuid>"
    :user-profile/name "<str>"
    :user-profile/field-configs [{:field-config/uuid "<uuid>"
                                  :field-config/name "<str>"
                                  :field-config/namespace "<str>"
                                  :field-config/type "<str>"
                                  :field-config/settings "<edn>"}]
    :user-profile/global-enabled? "<bool>"
    :user-profile/global-logo {:file/hash "<ipfs>"
                               :file/name "<str>"}
    :user-profile/global-description "<str>"}


   {:event :users/remove-user-profile
    :sender "<addr>"
    :district/uuid "<uuid>"
    :user-profile/uuid "<uuid>"}


   {:event (or :users/add-district-user-profiles :users/remove-district-user-profiles)
    :sender "<addr>"
    :district/uuid "<uuid>"
    :user-profiles ["<uuid>"]}


   {:event :users/update-user
    :sender "<addr>"
    :user/field-909659f5-560c-4640-9d67-7a1977da92b5 "<any>"}


   {:event :marketplace/add-offer-group-factory
    :sender "<addr>"
    :smart-contract/address "<addr>"
    :smart-contract/abi {:file/hash "<ipfs>"
                         :file/name "<str>"}}


   {:event :marketplace/update-offer-group
    :sender "<addr>"
    :district/uuid "<uuid>"
    :offer-group/uuid "<uuid>"
    :offer-group/name "<str>"
    :offer-group/offer-field-configs [{:field-config/uuid "<uuid>"
                                       :field-config/name "<str>"
                                       :field-config/namespace "<str>"
                                       :field-config/type "<str>"
                                       :field-config/settings "<edn>"}]

    :offer-group/response-field-configs [{:field-config/uuid "<uuid>"
                                          :field-config/name "<str>"
                                          :field-config/namespace "<str>"
                                          :field-config/type "<str>"
                                          :field-config/settings "<edn>"}]

    :offer-group/global-enabled? "<bool>"
    :offer-group/global-logo {:file/hash "<ipfs>"
                              :file/name "<str>"}
    :offer-group/global-description "<str>"}


   {:event :marketplace/remove-offer-group
    :sender "<addr>"
    :district/uuid "<uuid>"
    :offer-group/uuid "<uuid>"}


   {:event (or :marketplace/add-district-offer-groups :marketplace/remove-district-offer-groups)
    :sender "<addr>"
    :district/uuid "<uuid>"
    :offer-groups ["<uuid>"]}


   {:event :marketplace/update-offer
    :sender "<addr>"
    :offer-group/uuid "<uuid>"
    :offer/uuid "<uuid>"
    :offer/field-909659f5-560c-4640-9d67-7a1977da92b5 "<any>"}


   {:event :marketplace/add-message
    :sender "<addr>"
    :offer-response/uuid "<uuid>"
    :message/uuid "<uuid>"
    :message/receiver "<address>"
    :message/text "<str>"
    :message/files [{:file/hash "<ipfs>"
                     :file/name "<str>"}]}


   {:event (or :marketplace/add-feedback :marketplace/update-feedback)
    :sender "<addr>"
    :offer-response/uuid "<uuid>"
    :feedback/uuid "<uuid>"
    :feedback/rating "<float>"
    :feedback/text "<str>"}


   {:event :tcr/add-tcr-factory
    :sender "<addr>"
    :smart-contract/address "<addr>"
    :smart-contract/version "<int>"
    :smart-contract/abi {:file/hash "<ipfs>"
                         :file/name "<str>"}}


   {:event :tcr/update-tcr
    :sender "<addr>"
    :tcr/uuid "<uuid>"
    :tcr/reg-entry-field-configs [{:field-config/uuid "<uuid>"
                                   :field-config/name "<str>"
                                   :field-config/namespace "<str>"
                                   :field-config/type "<str>"
                                   :field-config/settings "<edn>"}]
    :tcr/global-enabled? "<bool>"
    :tcr/global-logo {:file/hash "<ipfs>"
                      :file/name "<str>"}
    :tcr/global-description "<str>"}


   {:event :tcr/remove-tcr
    :sender "<addr>"
    :tcr/uuid "<uuid>"}

   ])