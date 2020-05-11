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

                  ^{:type UserRole
                    :cardinality [1 n]}
                  user-roles

                  ^{:type UserRole}
                  admin-user-role

                  ^{:type PermissionUserRole
                    :cardinality [1 n]}
                  permissions-user-roles]


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
                    :cardinality [1 n]}
                  pages

                  ^{:type UserProfile
                    :cardinality [0 n]}
                  user-profiles

                  ^{:type OfferGroup
                    :cardinality [0 n]}
                  offers-groups

                  ^{:type TokenContract
                    :cardinality [0 n]}
                  token-contracts]

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
                  global-description]


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
                  abi]

                 
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

                  ^{:type Message}
                  raising-message

                  ^{:type Message}
                  resolving-message

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
                 [^{:type String
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
                  controller

                  ^{:type String}
                  implementation

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
                  nft-tokens]

                 
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
                  global-description]


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