// SPDX-License-Identifier: MIT
pragma solidity >=0.4.22 <0.7.0;
pragma experimental ABIEncoderV2;

import "../district_designer/DistrictFactory.sol";
import "../district_designer/proxy/ProxyFactory.sol";
import "./TCR.sol";
import "../tokens/TokenFactory.sol";


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
    address regEntryBaseContract,
    bytes regEntryIpfsAbi,
    address paramChangeEntryBaseContract,
    bytes paramChangeEntryIpfsAbi,
    uint timestamp
  );


  function initialize(
    DistrictFactory _districtFactory,
    ProxyFactory.ProxyTarget memory _tcrTarget,
    ProxyFactory.ProxyTarget memory _regEntryTarget,
    ProxyFactory.ProxyTarget memory _paramChangeEntryTarget,
    TokenFactory _tokenFactory
  ) public {
  }


  function createTCR(
    address _district,
    address _tcr,
    address _votingToken,
    TCR.TCRType _tcrType,
    TCR.RegistryEntryRepresentation memory _regEntryRepr,
    TCR.PermissionUserRoles memory _permissionUserRoles,
    TCR.Parameters memory _regParameters,
    TCR.Parameters memory _paramChangeParameters,
    bytes memory _ipfsData
  ) external {
  }


  function _updateProxyTargets(
    ProxyFactory.ProxyTarget memory _tcrTarget,
    ProxyFactory.ProxyTarget memory _regEntryTarget,
    ProxyFactory.ProxyTarget memory _paramChangeEntryTarget
  ) internal {
  }


  function targetUpdated(
    ProxyFactory.ProxyTarget memory _newTcrFactory,
    bytes memory _data
  ) external override onlySelf {
  }


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


  function fireRegistryEntryTokenMinted(
    address _regEntry,
    uint _tokenId
  ) external
  {}


  function fireParamChangeEntryApplied(
    address _paramChange
  ) external
  {}


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


  function fireChallengerRewardClaimed(
    address _entry,
    TCR.EntriesGroup _entriesGroup,
    address _challenger,
    uint _amount
  ) external
  {}


  function fireCreatorRewardClaimed(
    address _entry,
    TCR.EntriesGroup _entriesGroup,
    address _creator,
    uint _amount
  ) external
  {}


  function fireVotesReclaimed(
    address _entry,
    TCR.EntriesGroup _entriesGroup,
    address _voter,
    uint _amount
  ) external
  {}


  function fireVoteCommitted(
    address _entry,
    TCR.EntriesGroup _entriesGroup,
    address _voter,
    uint _amount
  ) external
  {}


  function fireVoteRevealed(
    address _entry,
    TCR.EntriesGroup _entriesGroup,
    address _voter,
    TCR.VoteOption _voteOption,
    uint _amount
  ) external
  {}


  function fireVoteRewardClaimed(
    address _entry,
    TCR.EntriesGroup _entriesGroup,
    address _voter,
    uint _amount
  ) external
  {}


  function fireTcrProxyTargetsUpdated(
    address _tcr,
    ProxyFactory.ProxyTarget memory _regEntryTarget,
    ProxyFactory.ProxyTarget memory _paramChangeEntryTarget
  ) external
  {}
}
