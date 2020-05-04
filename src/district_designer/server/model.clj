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

                  ^{:type OfferConfig
                    :cardinality [0 n]}
                  offer-configs

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
                 OfferValueType
                 [ERC20 ERC721 ERC1155 DELIVERABLE]

                 ^:enum
                 OfferType
                 [FIXED_PRICE DYNAMIC_PRICE HIGEST_BID_WINS OFFERER_PICKS_WINNER
                  ONE_OF_FIXED_PRICES ALL_FIXED_PRICES]

                 OfferConfig
                 [^{:type ID
                    :datomic/unique :db.unique/identity}
                  uuid

                  ^{:type String}
                  name

                  ^{:type OfferValueType}
                  offered-value-type

                  ^{:type OfferValueType}
                  requested-value-type

                  ^{:type TokenContract
                    :cardinality [0 n]}
                  offered-value-tokens

                  ^{:type TokenContract
                    :cardinality [0 n]}
                  requested-value-tokens

                  ^{:type OfferType}
                  offer-type

                  ^{:type FieldConfig
                    :cardinality [1 n]}
                  offer-field-configs

                  ^{:type FieldConfig
                    :cardinality [0 n]}
                  response-field-configs

                  ^{:type FieldConfig
                    :cardinality [0 n]}
                  dispute-field-configs

                  ^{:type FieldConfig
                    :cardinality [0 n]}
                  feedback-field-configs

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

                  ^{:type PermissionUserRoles}
                  permission-resolve-dispute

                  ^{:type Boolean}
                  global-enabled?

                  ^{:type File}
                  global-logo

                  ^{:type String}
                  global-description

                  ^{:type OfferConfigUserRating
                    :cardinality [0 n]}
                  user-ratings]


                 OfferConfigUserRating
                 [^{:type ID
                    :datomic/unique :db.unique/identity}
                  uuid

                  ^{:type User}
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

                  ^{:type OfferConfig}
                  offer-config

                  ^{:type UnknownType}
                  field-uuid

                  ^{:type Trade
                    :cardinality [0 n]}
                  trades

                  ^{:type DateTime}
                  created-on

                  ^{:type User}
                  creator]


                 Trade
                 [^{:type ID
                    :datomic/unique :db.unique/identity}
                  uuid

                  ^{:type Offer}
                  offer

                  ^{:type User}
                  respondent

                  ^{:type DateTime}
                  created-on

                  ^{:type DateTime}
                  updated-on

                  ^{:type TradedValue
                    :cardinality [0 n]}
                  offerer-traded-values

                  ^{:type String}
                  offerer-traded-values-textual-repr

                  ^{:type DateTime}
                  offerer-traded-on

                  ^{:type Message}
                  offerer-feedback-message

                  ^{:type Float}
                  offerer-feedback-rating

                  ^{:type TradedValue
                    :cardinality [0 n]}
                  respondent-traded-values

                  ^{:type String}
                  respondent-traded-values-textual-repr

                  ^{:type DateTime}
                  respondent-traded-on

                  ^{:type Message}
                  respondent-feedback-message

                  ^{:type Float}
                  respondent-feedback-rating

                  ^{:type DateTime}
                  completed-on

                  ^{:type Message
                    :cardinality [0 n]}
                  messages

                  ^{:type Message}
                  raised-dispute-message

                  ^{:type Message}
                  resolved-dispute-message]


                 TradedValue
                 [^{:type TokenContract}
                  token-contract

                  ^{:type Integer
                    :datomic/type :db.type/bigint}
                  amount

                  ^{:type NFTToken}
                  nft-token]


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
                 Network
                 [MAINNET RINKEBY]

                 ^:enum
                 TokenContractType
                 [ERC20 ERC721 ERC1155]


                 TokenContract
                 [^{:type String
                    :datomic/unique :db.unique/identity}
                  address

                  ^{:type Network}
                  network

                  ^{:type String}
                  name

                  ^{:type TokenContractType}
                  name

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
                 [PERMANENT_REG_ENTRIES IMPERMANENT_REG_ENTRIES]

                 ^:enum
                 TCRRegEntryRepresentation
                 [ERC721 ERC1155 BASIC_SMART_CONTRACT]
                 
                 
                 TCR
                 [^{:type ID
                    :datomic/unique :db.unique/identity}
                  uuid

                  ^{:type TCRType}
                  type

                  ^{:type TokenContract}
                  voting-token-contract

                  ^{:type TCRRegEntryRepresentation}
                  reg-entry-representation

                  ^{:type TokenContract}
                  reg-entry-representation-token-contract

                  ^{:type FieldConfig
                    :cardinality [1 n]}
                  reg-entry-field-configs

                  ^{:type TCRRegEntry
                    :cardinality [0 n]}
                  reg-entries

                  ^{:type TCRParamChange
                    :cardinality [0 n]}
                  param-changes

                  ^{:type PermissionUserRoles}
                  permission-submit-reg-entry

                  ^{:type PermissionUserRoles}
                  permission-submit-param-change
                  
                  ^{:type DateTime}
                  created-on

                  ^{:type Boolean}
                  global-enabled?

                  ^{:type File}
                  global-logo

                  ^{:type String}
                  global-description

                  ^{:type Integer
                    :datomic/type :db.type/bigint}
                  reg-entry-deposit

                  ^{:type Integer}
                  reg-entry-challenge-period-duration

                  ^{:type Integer}
                  reg-entry-vote-commit-period-duration

                  ^{:type Integer}
                  reg-entry-vote-reveal-period-duration

                  ^{:type Integer}
                  reg-entry-stake-dispensation

                  ^{:type Integer}
                  reg-entry-vote-quorum

                  ^{:type Integer
                    :datomic/type :db.type/bigint}
                  param-change-deposit

                  ^{:type Integer}
                  param-change-challenge-period-duration

                  ^{:type Integer}
                  param-change-vote-commit-period-duration

                  ^{:type Integer}
                  param-change-vote-reveal-period-duration

                  ^{:type Integer}
                  param-change-stake-dispensation

                  ^{:type Integer}
                  param-change-vote-quorum]



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


                 TCRParamChange
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
                  previous-value

                  ^{:type DateTime}
                  created-on

                  ^{:type DateTime}
                  applied-on

                  ^{:type TCRChallenge
                    :cardinality [0 n]}
                  challenges]


                 TCRChallenge
                 [^{:type ID
                    :datomic/unique :db.unique/identity}
                  uuid

                  ^{:type User}
                  challenger

                  ^{:type Integer}
                  index

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
                  reward-amount

                  ^{:type DateTime}
                  claimed-reward-on

                  ^{:type String}
                  comment]


                 ^:enum
                 TCRVoteOption
                 [VOTE_INCLUDE VOTE_EXCLUDE NO_VOTE]


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
                  claimed-reward-on

                  ^{:type DateTime}
                  reclaimed-votes-on]]))

(def graphviz-schema (hodur-graphviz/schema meta-db))


(defn spit-diagram []
  (spit "./diagrams/database.dot" graphviz-schema))

(comment
  graphviz-schema
  (spit-diagram))