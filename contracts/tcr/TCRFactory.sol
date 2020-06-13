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
    address tcrBaseContract,
    bytes tcrIpfsAbi,
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
    address regEntryBaseContract,
    bytes regEntryIpfsAbi,
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
    address paramChangeEntryAddress,
    address paramChangeEntryBaseContract,
    bytes paramChangeEntryIpfsAbi,
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

  event BaseContractsUpdated(
    address tcrBaseContract,
    bytes tcrIpfsAbi,
    address regEntryBaseContract,
    bytes regEntryIpfsAbi,
    address paramChangeEntryBaseContract,
    bytes paramChangeEntryIpfsAbi,
    uint timestamp
  );


  event TCRBaseContractsUpdated(
    address tcr,
    address regEntryBaseContract,
    bytes regEntryIpfsAbi,
    address paramChangeEntryBaseContract,
    bytes paramChangeEntryIpfsAbi,
    uint timestamp
  );


  function initialize(
    DistrictFactory _districtFactory,
    address _tcrBaseContract,
    bytes calldata _tcrIpfsAbi,
    address _regEntryBaseContract,
    bytes calldata _regEntryIpfsAbi,
    address _paramChangeEntryBaseContract,
    bytes calldata _paramChangeEntryIpfsAbi,
    TokenFactory _tokenFactory
  ) public {
  }


  function createTCR(
    address _district,
    address _tcr,
    address _votingToken,
    TCR.TCRType _tcrType,
    TCR.RegistryEntryRepresentation calldata _regEntryRepr,
    TCR.PermissionUserRoles calldata _permissionUserRoles,
    TCR.Parameters calldata _regParameters,
    TCR.Parameters calldata _paramChangeParameters,
    bytes calldata _ipfsData
  ) external {
  }


  function _updateBaseContracts(
    address _tcrBaseContract,
    bytes calldata _tcrIpfsAbi,
    address _regEntryBaseContract,
    bytes calldata _regEntryIpfsAbi,
    address _paramChangeEntryBaseContract,
    bytes calldata _paramChangeEntryIpfsAbi
  ) internal {
  }


  function targetUpdated(
    address _newTarget,
    bytes calldata _ipfsAbi,
    bytes calldata _data
  ) external override onlySelf {
  }


  function fireRegistryEntryCreated(
    address _regEntry,
    address _regEntryBaseContract,
    bytes calldata _regEntryIpfsAbi,
    uint _regEntryVersion,
    address _creator,
    uint _tokenAmount,
    bytes calldata _tokenIpfsData,
    bytes calldata _ipfsData
  ) external {
  }


  function fireParamChangeEntryCreated(
    address _paramChangeEntry,
    address _paramChangeEntryBaseContract,
    bytes memory _paramChangeEntryIpfsAbi,
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
    bytes calldata _ipfsData
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


  function fireTcrBaseContractsUpdated(
    address _tcr,
    address _regEntryBaseContract,
    bytes calldata _regEntryIpfsAbi,
    address paramChangeEntryBaseContract,
    bytes calldata _paramChangeEntryIpfsAbi
  ) external
  {}
}
