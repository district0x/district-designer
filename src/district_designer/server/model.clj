(ns district-designer.server.model
  (:require
    [hodur-engine.core :as hodur]
    [hodur-graphviz-schema.core :as hodur-graphviz]))

(def meta-db (hodur/init-schema
               '[^:graphviz/tag
                 default

                 DistrictDesigner
                 [^{:type String
                    :datomic/unique :db.unique/identity}
                  address

                  ^{:type String}
                  base-address

                  ^{:type Integer}
                  version

                  ^{:type File}
                  abi

                  ^{:type UserRole
                    :cardinality [1 n]}
                  user-roles

                  ^{:type UserRole}
                  admin-user-role

                  ^{:type PermissionUserRole
                    :cardinality [1 n]}
                  permissions-user-roles]


                 DDProxyFactory
                 [^{:type String
                    :datomic/unique :db.unique/identity}
                  address

                  ^{:type File}
                  abi]


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

                  ^{:type Boolean}
                  emergency?

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

                  ^{:type File
                    :cardinality [0 n]}
                  files

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
                  ipfs-hash

                  ^{:type String}
                  name

                  ^{:type Boolean}
                  directory?

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
                  admin

                  ^{:type String}
                  default-settings

                  ^{:type File
                    :cardinality [1 n]}
                  preview-images

                  ^{:type File
                    :cardinality [1 n]}
                  styles

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
                  admin

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
                  admin

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


                 OfferGroupFactory
                 [^{:type String
                    :datomic/unique :db.unique/identity}
                  address

                  ^{:type Integer}
                  version

                  ^{:type File}
                  abi

                  ^{:type File}
                  offer-group-abi

                  ^{:type File}
                  offer-abi]


                 OfferGroup
                 [^{:type ID
                    :datomic/unique :db.unique/identity}
                  uuid

                  ^{:type String}
                  name

                  ^{:type String}
                  address

                  ^{:type Integer}
                  version

                  ^{:type File}
                  abi

                  ^{:type File}
                  offer-abi

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

                  ^{:type DateTime}
                  updated-on

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

                  ^{:type Boolean}
                  encrypted?

                  ^{:type DateTime}
                  created-on]


                 ^:enum
                 TokenType
                 [ERC20 ERC721 ERC1155]


                 TokenFactory
                 [^{:type String
                    :datomic/unique :db.unique/identity}
                  address

                  ^{:type Integer}
                  version

                  ^{:type TokenType}
                  token-type

                  ^{:type File}
                  abi]


                 TokenFactoryEventsContract
                 [^{:type String
                    :datomic/unique :db.unique/identity}
                  address

                  ^{:type Integer}
                  version

                  ^{:type File}
                  abi]


                 TokenContract
                 [^{:type ID
                    :datomic/unique :db.unique/identity}
                  uuid

                  ^{:type String
                    :datomic/unique :db.unique/identity}
                  address

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

                  ^{:type File}
                  abi

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

                  ^{:type String
                    :datomic/unique :db.unique/identity}
                  address

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
                  global-imports-count
                  ]


                 TCRRegEntry
                 [^{:type ID
                    :datomic/unique :db.unique/identity}
                  uuid

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

                  ^{:type UnknownType}
                  field-uuid

                  ^{:type String}
                  db

                  ^{:type String}
                  key

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
  [{:module :district-designer
    :action :genesis
    :sender "<addr>"
    :permissions [{:permission/id "dd_system_administration"
                   :permission/name "District Designer System Administration"
                   :permission/description "<str>"}]
    :user-roles [{:user-role/uuid "<uuid>"
                  :user-role/name "<str>"}]
    :district-designer/address "<addr>"
    :district-designer/version "<int>"
    :district-designer/base-address "<addr>"
    :district-designer/abi "<ipfs>"
    :dd-proxy-factory/address "<addr>"
    :dd-proxy-factory/abi "<ipfs>"
    :dd-proxy-factory/version "<int>"}

   {:module :district-designer
    :action (or :add-module :update-module)
    :sender "<addr>"
    :module/id "<id>"
    :module/name "<str>"
    :module/logo "<ipfs>"
    :module/admin "<addr>"
    :module/description "<str>"
    :module/preview-images ["<ipfs>"]}

   {:module :district-designer
    :action :remove-module
    :sender "<addr>"
    :module/id "<id>"}

   {:module :district-designer
    :action (or :add-wizard :update-wizard)
    :sender "<addr>"
    :wizard/id "<str>"
    :wizard/name "<str>"
    :wizard/logo "<ipfs>"
    :wizard/admin "<addr>"
    :wizard/description "<str>"
    :wizard/preview-images ["<ipfs>"]}

   {:module :district-designer
    :action :remove-wizard
    :sender "<addr>"
    :wizard/id "<id>"}

   {:module :district-designer
    :action (or :add-theme :update-theme)
    :sender "<addr>"
    :theme/id "<str>"
    :theme/name "<str>"
    :theme/admin "<addr>"
    :theme/description "<str>"
    :theme/default-settings "<edn>"
    :theme/styles ["<ipfs>"]}

   {:module :district-designer
    :action :remove-theme
    :sender "<addr>"
    :theme/id "tokens"}

   {:module :district
    :action (or :add-district :update-district)
    :sender "<addr>"
    :district/uuid "<uuid>"
    :district/name "<str>"
    :district/subdomain "<str>"
    :district/title "<str>"
    :district/description "<str>"
    :district/logo "<ipfs>"
    :district/cover-image "<ipfs>"
    :district/favicon "<ipfs>"
    :district/ga-tracking-id "<str>"}


   {:module :district
    :action (or :add-user-roles :update-user-roles)
    :sender "<addr>"
    :district "<uuid>"
    :user-roles [{:user-role/uuid "<uuid>"
                  :user-role/name "<str>"}]}


   {:module :district
    :action :add-files
    :sender "<addr>"
    :district "<uuid>"
    :files [{:file/ipfs-hash "<ipfs>"
             :file/name "<str>"
             :file/directory? "<bool>"
             :file/encrypted? "<bool>"
             :file/decryptable-by "<addr>"}]}


   {:module :district
    :action :remove-files
    :sender "<addr>"
    :district "<uuid>"
    :files ["<ipfs>"]}


   {:module :district
    :action :update-theme
    :sender "<addr>"
    :district "<uuid>"
    :district/theme "<uuid>"
    :district/theme-settings "<edn>"}


   {:module :district
    :action :update-styles
    :sender "<addr>"
    :district "<uuid>"
    :district/less-file "<ipfs>"
    :district/css-file "<ipfs>"}


   {:module :district
    :action :add-modules
    :sender "<addr>"
    :district "<uuid>"
    :modules ["<uuid>"]}


   {:module :district
    :action :remove-modules
    :sender "<addr>"
    :district "<uuid>"
    :modules ["<uuid>"]}


   {:module :district
    :action (or :add-pages :remove-pages)
    :sender "<addr>"
    :district "<uuid>"
    :pages ["<uuid>"]}


   {:module :district
    :action :add-ui-components
    :sender "<addr>"
    :district "<uuid>"
    :ui-components [{:ui-component/uuid "<uuid>"
                     :ui-component/name "<str>"
                     :ui-component/type "<str>"
                     :ui-component/children ["<uuid>"]
                     :ui-component/settings "<edn>"}]}


   {:module :district
    :action :update-ui-component
    :sender "<addr>"
    :ui-component/uuid "<uuid>"
    :ui-component/name "<str>"
    :ui-component/type "<str>"
    :ui-component/children ["<uuid>"]
    :ui-component/settings "<edn>"}


   {:module :district
    :action :remove-ui-components
    :sender "<addr>"
    :district "<uuid>"
    :ui-components ["<uuid>"]}


   {:module :district
    :action (or :add-database-views :add-statistics-views)
    :sender "<addr>"
    :district "<uuid>"
    :data-views [{:data-view/uuid "<uuid>"
                  :data-view/name "<str>"
                  :data-view/settings "<edn>"}]}


   {:module :district
    :action (or :update-database-view :update-statistics-view)
    :sender "<addr>"
    :data-view/uuid "<uuid>"
    :data-view/name "<str>"
    :data-view/settings "<edn>"}


   {:module :district
    :action (or :remove-database-views :remove-statistics-views)
    :sender "<addr>"
    :district "<uuid>"
    :data-views ["<uuid>"]}


   {:module :tokens
    :action (or :add-token-factory :update-token-factory)
    :sender "<addr>"
    :token-factory/address "<addr>"
    :token-factory/version "<int>"
    :token-factory/token-type "<token-type>"
    :token-factory/abi "<ipfs>"}


   {:module :tokens
    :action (or :add-token-factory-events-contract :update-token-factory-events-contract)
    :sender "<addr>"
    :token-factory-events-contract/address "<addr>"
    :token-factory-events-contract/version "<int>"
    :token-factory-events-contract/abi "<ipfs>"}


   {:module :tokens
    :action (or :add-token-contract :update-token-contract)
    :sender "<addr>"
    :district "<uuid>"
    :token-contract/uuid "<uuid>"
    :token-contract/type "<token-type>"
    :token-contract/metadata-format "<str>"
    :token-contract/metadata-format-settings "<edn>"
    :token-contract/abi "<ipfs>"}


   {:module :tokens
    :action :remove-token-contract
    :sender "<addr>"
    :district "<uuid>"
    :token-contract/uuid "<uuid>"}


   {:module :tokens
    :action (or :add-district-token-contracts :remove-district-token-contracts)
    :sender "<addr>"
    :district "<uuid>"
    :token-contracts ["<uuid>"]}


   {:module :tokens
    :action :add-token-contract-misconfig-report
    :sender "<addr>"
    :district "<uuid>"
    :token-contract/uuid "<uuid>"
    :token-contract/reported-misconfig-comment "<str>"}


   {:module :tokens
    :action :resolve-token-contract-misconfig-report
    :sender "<addr>"
    :token-contract/uuid "<uuid>"}


   {:module :users
    :action (or :add-user-profile :update-user-profile)
    :sender "<addr>"
    :district "<uuid>"
    :user-profile/uuid "<uuid>"
    :user-profile/name "<str>"
    :user-profile/field-configs [{:field-config/uuid "<uuid>"
                                  :field-config/name "<str>"
                                  :field-config/namespace "<str>"
                                  :field-config/type "<str>"
                                  :field-config/settings "<edn>"}]
    :user-profile/global-enabled? "<bool>"
    :user-profile/global-logo "<ipfs>"
    :user-profile/global-description "<str>"}


   {:module :users
    :action :remove-user-profile
    :sender "<addr>"
    :district "<uuid>"
    :user-profile "<uuid>"}


   {:module :users
    :action (or :add-district-user-profiles :remove-district-user-profiles)
    :sender "<addr>"
    :district "<uuid>"
    :user-profiles ["<uuid>"]}


   {:module :users
    :action :update-user
    :sender "<addr>"
    :user/field-909659f5-560c-4640-9d67-7a1977da92b5 "<str>"}


   {:module :marketplace
    :action (or :add-offer-group-factory :update-offer-group-factory)
    :sender "<addr>"
    :offer-group-factory/address "<addr>"
    :offer-group-factory/version "<int>"
    :offer-group-factory/abi "<ipfs>"
    :offer-group-factory/offer-group-abi "<ipfs>"
    :offer-group-factory/offer-abi "<ipfs>"}


   {:module :marketplace
    :action (or :add-offer-group :update-offer-group)
    :sender "<addr>"
    :district "<uuid>"
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
    :offer-group/global-logo "<ipfs>"
    :offer-group/global-description "<str>"}


   {:module :marketplace
    :action :remove-offer-group
    :sender "<addr>"
    :district "<uuid>"
    :offer-group "<uuid>"}


   {:module :marketplace
    :action (or :add-district-offer-groups :remove-district-offer-groups)
    :sender "<addr>"
    :district "<uuid>"
    :offer-groups ["<uuid>"]}


   {:module :marketplace
    :action (or :add-offer :update-offer)
    :sender "<addr>"
    :offer-group "<uuid>"
    :offer/uuid "<uuid>"
    :offer/field-909659f5-560c-4640-9d67-7a1977da92b5 "<str>"}


   {:module :marketplace
    :action :add-offer-response
    :sender "<addr>"
    :offer "<uuid>"
    :offer-response/uuid "<uuid>"
    :offer-response/field-909659f5-560c-4640-9d67-7a1977da92b5 "<str>"}


   {:module :marketplace
    :action :add-messages
    :sender "<addr>"
    :offer-response "<uuid>"
    :messages [{:message/uuid "<uuid>"
                :message/receiver "<address>"
                :message/type "<str>"
                :message/text "<str>"
                :message/encrypted? "<bool>"}]}


   {:module :marketplace
    :action (or :add-feedback :update-feedback)
    :sender "<addr>"
    :offer-response "<uuid>"
    :feedback/uuid "<uuid>"
    :feedback/rating "<float>"
    :feedback/text "<str>"}

   ])