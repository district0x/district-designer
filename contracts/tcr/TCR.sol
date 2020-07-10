// SPDX-License-Identifier: MIT
pragma solidity >=0.4.22 <0.7.0;
pragma experimental ABIEncoderV2;

import "../district_designer/proxy/ProxyFactory.sol";
import "../tokens/ApproveAndCallFallback.sol";
import "../tokens/openzeppelin/ERC1155/ERC1155Receiver.sol"; // Replace with npm dependency once published

/**
 * @dev TCR (Token Curated Registry) contract
 * You can read about TCRs here https://education.district0x.io/general-topics/understanding-ethereum/token-curated-registry/
 * This contract is used through a proxy, therefore its address will never change.
 * No breaking changes will be introduced for events, so they all stay accessible from a single contract.
 */

contract TCR is UpdateTargetAndCallFallBack, ApproveAndCallFallBack, ERC1155Receiver {

  uint public constant version = 1;

  enum TCRType {
    /**
     * Type of TCR where there's initial challenge period, after which, if
     * a registry entry doesn't get challenged, it stays in the registry forever.
     */
    CHALLENGABLE_ONCE,
    /**
     * Type of TCR where a registry entry gets into the registry right after
     * the submission, but can be challenged anytime during its lifetime and possibly
     * removed from the registry.
     */
    CHALLENGEABLE_ANYTIME
  }

  enum RegistryEntryRepresentationCategory {
    ERC721,
    ERC1155,
    NO_TOKEN
  }

  enum EntriesGroup {
    REGISTRY_ENTRIES,
    PARAM_CHANGE_ENTRIES
  }

  enum EntryStatus {
    ChallengePeriod,
    CommitPeriod,
    RevealPeriod,
    Blacklisted,
    Whitelisted
  }

  enum VoteOption {
    Include,
    Exclude,
    NoVote
  }

  enum VotingTokenType {
    ERC20,
    ERC1155
  }

  struct VotingToken {
    address tokenAddress;
    VotingTokenType votingTokenType;
    uint tokenId; // only for ERC1155
  }

  struct VotingTokenValue {
    VotingToken votingToken;
    uint value;
  }

  struct RegistryEntryRepresentation {
    RegistryEntryRepresentationCategory category;
    string tokenName;
    string tokenSymbol;
    string baseUri;
  }

  struct PermissionUserRoles {
    bytes16[] createRegEntryUserRoles;
    bytes16[] createParamChangeEntryUserRoles;
  }

  struct Parameters {
    uint deposit;
    uint challengePeriodDuration;
    uint voteCommitPeriodDuration;
    uint voteRevealPeriodDuration;
    uint challengeDispensation;
    uint voteQuorum;
  }


  /**
   * @dev Contract initialization
   * It is manually called instead of native contructor,
   * because this contract is used through a proxy.
   * This function cannot be called twice.
   *
   * @param _regEntryTarget Contract for {RegistryEntry} proxies
   * @param _paramChangeEntryTarget Contract for {ParamChangeEntry} proxies
   * @param _district Address of a district
   * @param _votingToken Address of voting token for TCR
   * @param _tcrType Type of TCR
   * @param _regEntryRepr Registry entry representation
   * @param _permissionUserRoles Permisions for certain TCR operations
   * @param _regParameters TCR parameters for registry entries
   * @param _paramChangeParameters TCR parameters for param change entries
   * @param _ipfsData Hash of additional data stored on IPFS
   *
   * `msg.sender` is considered to be {TCRFactory}
   * If `_regEntryRepr` is a token, it deploys a new token. Owner of the token is this contract.
   *
   * Requirements:
   *
   * - `_regEntryTarget` cannot be empty
   * - `_paramChangeEntryTarget` cannot be empty
   * - `_district` cannot be zero address
   * - `_votingToken` cannot be zero address
   * - `_ipfsData` must be valid IPFS hash
   *
   * See spec :tcr/tcr-created for format of _ipfsData file
   * TODO: Needs implementation
   */
  function initialize(
    ProxyFactory.ProxyTarget memory _regEntryTarget,
    ProxyFactory.ProxyTarget memory _paramChangeEntryTarget,
    District _district,
    VotingToken memory _votingToken,
    TCRType _tcrType,
    RegistryEntryRepresentation memory _regEntryRepr,
    PermissionUserRoles memory _permissionUserRoles,
    Parameters memory _regParameters,
    Parameters memory _paramChangeParameters,
    bytes memory _ipfsData
  ) external {
  }


  /**
   * @dev Creates a new {RegistryEntry}
   *
   * @param _creator Address creating the registry entry
   * @param _trasferredValue Actually transferred value into this contract
   * @param _tokenAmount Amount of token to be minted when representation is a token
   * @param _tokenIpfsData IPFS hash for token metadata when representation is a token
   * @param _ipfsData Hash of additional data stored on IPFS
   *
   * It creates a new registry entry in following steps:
   * 1. Creates new {OwnerProxy} forwarding to `_regEntryTarget`
   * 2. Transfers `_trasferredValue` from this contract into newly created contract
   * 3. Calls `initialize` on the newly created contract
   *
   * Owner of the proxy is this contract. Created proxy is not meant to be updated.
   * If TCR type is CHALLENGEABLE_ANYTIME and representation is a token, it also mints
   * the token.
   *
   * Requirements:
   *
   * - `_creator` cannot be zero address
   * - `_creator` must be within `TCR.PermissionUserRoles.createRegEntryUserRoles`, if it's not empty
   * - `_tokenIpfsData` must be valid IPFS hash only if representation is a token
   * - `_ipfsData` must be valid IPFS hash
   *
   * Emits an {RegistryEntryCreated} event
   *
   * See spec :tcr/registry-entry-created for format of _ipfsData file
   * TODO: Needs implementation
   */
  function _createRegistryEntry(
    address _creator,
    VotingTokenValue memory _trasferredValue,
    uint _tokenAmount,
    bytes memory _tokenIpfsData,
    bytes memory _ipfsData
  ) internal {
  }


  /**
   * @dev Creates a new {ParamChangeyEntry}
   *
   * @param _creator Address creating the registry entry
   * @param _trasferredValue Actually transferred value into this contract
   * @param _entriesGroup Entries where parameter change will be applied
   * @param _parameterKey Parameter key which will be changed
   * @param _parameterValue New parameter value
   *
   * It creates a new registry entry in following steps:
   * 1. Creates new {OwnerProxy} forwarding to `_paramChangeEntryTarget`
   * 2. Transfers `_trasferredValue` from this contract into newly created contract
   * 3. Calls `initialize` on the newly created contract
   *
   * Owner of the proxy is this contract. Created proxy is not meant to be updated.
   *
   * Requirements:
   *
   * - `_creator` cannot be zero address
   * - `_creator` must be within `TCR.PermissionUserRoles.createRegEntryUserRoles`, if it's not empty
   * - `_ipfsData` must be valid IPFS hash
   *
   * Emits an {ParamChangeEntryCreated} event
   *
   * See spec :tcr/param-change-entry-created for format of _ipfsData file
   * TODO: Needs implementation
   */
  function _createParamChangeEntry(
    address _creator,
    VotingTokenValue memory _trasferredValue,
    EntriesGroup _entriesGroup,
    string memory _parameterKey,
    uint _parameterValue,
    bytes memory _ipfsData
  ) internal {
  }


  /**
   * @dev Mints new token based on its registry entry
   * It mints only if registry entry representation is a token.
   * It checks if registry entry is in registry.
   * It mints tokens for creator of the registry entry.
   * It mints the amount specified when creating the registry entry.
   * Can be called by anyone.
   * If tokens are already minted for given registry entry, it reverts.
   * It calls {RegistryEntry.markAsMinted}
   *
   * Emits an {RegistryEntryTokenMinted} event
   *
   * TODO: Needs implementation
   */
  function mintRegistryEntryToken(
    address _regEntry
  ) public {
  }


  /**
   * @dev Applies a parameter change to TCR parameters
   * Only param changes that successfully got into registry can be applied.
   * It checks if param change entry's original value is still the current value
   * in TCR parameters.
   * Same param change entry cannot be applied twice.
   * Can be called by anyone.
   * It calls {ParamChangeEntry.markAsApplied}
   *
   * Emits an {ParamChangeEntryApplied} event
   *
   * TODO: Needs implementation
   */
  function applyParamChangeEntry(
    address _paramChangeEntry
  ) external {
  }


  /**
   * @dev Updates proxy targets that newly created proxies will forward to
   * It's meant to be called only by {targetUpdated}
   *
   * Contracts with zero addresses will not be updated.
   *
   * Emits an {TCRProxyTargetsUpdated} event
   * TODO: Needs implementation
   */
  function _updateProxyTargets(
    ProxyFactory.ProxyTarget memory _regEntryTarget,
    ProxyFactory.ProxyTarget memory _paramChangeEntryTarget
  ) internal {
  }


  /**
   * @dev This function is called automatically when proxy forwarding to this contract
   * update its target.
   * It should decode `_data` into arguments of {_updateProxyTargets} and call it
   * TODO: Needs implementation
   */
  function targetUpdated(
    ProxyFactory.ProxyTarget memory _newTcrFactoryTarget,
    bytes memory _data
  ) external override onlySelf {
  }


  /**
   * @dev This function is called automatically when this contract receives approval for ERC20 token
   * It transfers approved tokens into this contract.
   * It decodes `_data` and calls either {_createRegistryEntry} or {_createParamChangeEntry}
   * It constructs `_trasferredValue` from the transferred token.
   *
   * NOTE: Person implementing this will need to figure out how based on `_data` we
   * determine if we should call {_createRegistryEntry} or {_createParamChangeEntry}
   *
   * TODO: Needs implementation
   */
  function receiveApproval(
    address from,
    uint256 _amount,
    address _token,
    bytes memory _data
  ) external override {
  }


  /**
   * @dev This function is called automatically when this contract receives ERC1155 token
   * It decodes `_data` and calls either {_createRegistryEntry} or {_createParamChangeEntry}
   * It constructs `_trasferredValue` from the transferred token.
   * TODO: Needs implementation
   */
  function onERC1155Received(
    address operator,
    address from,
    uint256 id,
    uint256 value,
    bytes calldata data
  ) external override returns (bytes4) {
    return bytes4(keccak256("onERC1155Received(address,address,uint256,uint256,bytes)"));
  }


  /**
   * @dev This function is called automatically when this contract receives multiple ERC1155 tokens
   * It always reverts, because voting cannot happen with multiple tokens.
   */
  function onERC1155BatchReceived(
    address operator,
    address from,
    uint256[] calldata ids,
    uint256[] calldata values,
    bytes calldata data
  ) external override returns (bytes4) {
    revert();
  }

}

