// SPDX-License-Identifier: MIT
pragma solidity >=0.4.22 <0.7.0;
pragma experimental ABIEncoderV2;

import "../district_designer/DistrictFactory.sol";
import "../district_designer/proxy/ProxyFactory.sol";
import "./TCR.sol";
import "../tokens/TokenFactory.sol";

/**
 * @dev Factory contract for creating {TCR}
 * It also emits all events related to tcr module
 * This contract is used through a proxy, therefore its address will never change.
 * No breaking changes will be introduced for events, so they all stay accessible from a single contract.
 */

contract TCRFactory is UpdateTargetAndCallFallBack {

  uint public constant version = 1;

  event TCRCreated(
    address indexed district,
    address tcr,
    ProxyFactory.ProxyTarget tcrTarget,
    uint tcrVersion,
    address votingToken,
    TCR.TCRType tcrType,
    TCR.RegistryEntryRepresentation regEntryRepresentation,
    TCR.PermissionUserRoles permissionUserRoles,
    TCR.Parameters regEntryParameters,
    TCR.Parameters paramChangeEntryParameters,
    bytes ipfsData,
    uint timestamp
  );

  event RegistryEntryCreated(
    address tcr,
    address regEntry,
    ProxyFactory.ProxyTarget regEntryTarget,
    uint regEntryVersion,
    address creator,
    uint tokenAmount,
    bytes tokenMetaIpfsData,
    bytes ipfsData,
    uint timestamp
  );

  event ParamChangeEntryCreated(
    address tcr,
    address paramChangeEntry,
    ProxyFactory.ProxyTarget paramChangeEntryTarget,
    uint paramChangeEntryVersion,
    address creator,
    TCR.EntriesGroup entriesGroup,
    string key,
    uint value,
    uint originalValue,
    bytes ipfsData,
    uint timestamp
  );

  event ChallengeCreated(
    address entry,
    TCR.EntriesGroup entriesGroup,
    uint challengeIndex,
    address challenger,
    uint commitPeriodEnd,
    uint revealPeriodEnd,
    uint rewardPool,
    bytes ipfsData,
    uint timestamp
  );

  event RegistryEntryTokenMinted(
    address regEntry,
    uint tokenId,
    uint timestamp
  );

  event ParamChangeEntryApplied(
    address paramChangeEntry,
    uint timestamp
  );

  event ChallengerRewardClaimed(
    address entry,
    TCR.EntriesGroup entriesGroup,
    address challenger,
    uint amount,
    uint timestamp
  );

  event CreatorRewardClaimed(
    address entry,
    TCR.EntriesGroup entriesGroup,
    address creator,
    uint amount,
    uint timestamp
  );

  event VotesReclaimed(
    address entry,
    TCR.EntriesGroup entriesGroup,
    address voter,
    uint amount,
    uint timestamp
  );

  event VoteCommitted(
    address entry,
    TCR.EntriesGroup entriesGroup,
    address voter,
    uint amount,
    uint timestamp
  );

  event VoteRevealed(
    address entry,
    TCR.EntriesGroup entriesGroup,
    address voter,
    TCR.VoteOption voteOption,
    uint amount,
    uint timestamp
  );

  event VoteRewardClaimed(
    address entry,
    TCR.EntriesGroup entriesGroup,
    address voter,
    uint amount,
    uint timestamp
  );

  event ProxyTargetsUpdated(
    ProxyFactory.ProxyTarget tcrTarget,
    ProxyFactory.ProxyTarget regEntryTarget,
    ProxyFactory.ProxyTarget paramChangeEntryTarget,
    uint timestamp
  );


  event TCRProxyTargetsUpdated(
    address tcr,
    ProxyFactory.ProxyTarget regEntryTarget,
    ProxyFactory.ProxyTarget paramChangeEntryTarget,
    uint timestamp
  );


  /**
   * @dev Contract initialization
   * It is manually called instead of native contructor,
   * because this contract is used through a proxy.
   * This function cannot be called twice.
   *
   * It stores addresses of contracts where proxies will forward to.
   *
   * TODO: Needs implementation
   */
  function initialize(
    DistrictFactory _districtFactory,
    ProxyFactory.ProxyTarget memory _tcrTarget,
    ProxyFactory.ProxyTarget memory _regEntryTarget,
    ProxyFactory.ProxyTarget memory _paramChangeEntryTarget,
    TokenFactory _tokenFactory
  ) public {
  }


  /**
   * @dev Creates a new {OfferGroup}
   * It creates {DistrictAdminProxy} forwarding to tcrTarget` contract
   *
   * Requirements:
   *
   * - `_district` must be existing district created by {DistrictFactory}
   * - `msg.sender` must have "MANAGE_TCRS" permission for given district
   *
   * Emits an {TcrCreated} event
   *
   * See spec :tcr/tcr-created for format of _ipfsData file
   * TODO: Needs implementation
   */
  function createTCR(
    District _district,
    address _tcr,
    TCR.VotingToken memory _votingToken,
    TCR.TCRType _tcrType,
    TCR.RegistryEntryRepresentation memory _regEntryRepr,
    TCR.PermissionUserRoles memory _permissionUserRoles,
    TCR.Parameters memory _regParameters,
    TCR.Parameters memory _paramChangeParameters,
    bytes memory _ipfsData
  ) external {
  }

  /**
   * @dev Updates contracts that serve as targets for proxies.
   * It's meant to be called only by {targetUpdated}
   *
   * Contracts with zero addresses will not be updated.
   *
   * Emits an {ProxyTargetsUpdated} event
   * TODO: Needs implementation
   */
  function _updateProxyTargets(
    ProxyFactory.ProxyTarget memory _tcrTarget,
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
    ProxyFactory.ProxyTarget memory _newTcrFactory,
    bytes memory _data
  ) external override onlySelf {
  }


  /**
   * TODO: Needs implementation
   */
  function fireRegistryEntryCreated(
    address _regEntry,
    ProxyFactory.ProxyTarget memory _regEntryTarget,
    uint _regEntryVersion,
    address _creator,
    uint _tokenAmount,
    bytes memory _tokenIpfsData,
    bytes memory _ipfsData
  ) external {
  }


  /**
   * TODO: Needs implementation
   */
  function fireParamChangeEntryCreated(
    address _paramChangeEntry,
    ProxyFactory.ProxyTarget memory _paramChangeEntryTarget,
    uint _paramChangeEntryVersion,
    address _creator,
    TCR.EntriesGroup _paramGroup,
    string memory _key,
    uint _value,
    uint _originalValue,
    bytes memory _ipfsData
  ) external
  {}


  /**
   * TODO: Needs implementation
   */
  function fireRegistryEntryTokenMinted(
    address _regEntry,
    uint _tokenId
  ) external
  {}


  /**
   * TODO: Needs implementation
   */
  function fireParamChangeEntryApplied(
    address _paramChange
  ) external
  {}


  /**
   * TODO: Needs implementation
   */
  function fireChallengeCreated(
    address _entry,
    TCR.EntriesGroup _entriesGroup,
    uint _challengeIndex,
    address _challenger,
    uint _commitPeriodEnd,
    uint _revealPeriodEnd,
    uint _rewardPool,
    bytes memory _ipfsData
  ) external
  {}


  /**
   * TODO: Needs implementation
   */
  function fireChallengerRewardClaimed(
    address _entry,
    TCR.EntriesGroup _entriesGroup,
    address _challenger,
    uint _amount
  ) external
  {}


  /**
   * TODO: Needs implementation
   */
  function fireCreatorRewardClaimed(
    address _entry,
    TCR.EntriesGroup _entriesGroup,
    address _creator,
    uint _amount
  ) external
  {}


  /**
   * TODO: Needs implementation
   */
  function fireVotesReclaimed(
    address _entry,
    TCR.EntriesGroup _entriesGroup,
    address _voter,
    uint _amount
  ) external
  {}


  /**
   * TODO: Needs implementation
   */
  function fireVoteCommitted(
    address _entry,
    TCR.EntriesGroup _entriesGroup,
    address _voter,
    uint _amount
  ) external
  {}


  /**
   * TODO: Needs implementation
   */
  function fireVoteRevealed(
    address _entry,
    TCR.EntriesGroup _entriesGroup,
    address _voter,
    TCR.VoteOption _voteOption,
    uint _amount
  ) external
  {}


  /**
   * TODO: Needs implementation
   */
  function fireVoteRewardClaimed(
    address _entry,
    TCR.EntriesGroup _entriesGroup,
    address _voter,
    uint _amount
  ) external
  {}


  /**
   * TODO: Needs implementation
   */
  function fireTcrProxyTargetsUpdated(
    address _tcr,
    ProxyFactory.ProxyTarget memory _regEntryTarget,
    ProxyFactory.ProxyTarget memory _paramChangeEntryTarget
  ) external
  {}
}
