// SPDX-License-Identifier: MIT
pragma solidity >=0.4.22 <0.7.0;
pragma experimental ABIEncoderV2;

import "../district_designer/proxy/ProxyFactory.sol";

contract TCR is UpdateTargetAndCallFallBack {

  uint public constant version = 1;

  enum TCRType {
    INITIAL_CHALLENGE_PERIOD,
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

  struct RegistryEntryRepresentation {
    RegistryEntryRepresentationCategory category;
    string tokenName;
    string tokenSymbol;
    string baseMetadataUri;
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
    uint challengeDepositDispensation;
    uint voteQuorum;
  }


  function initialize(
    ProxyFactory.ProxyTarget memory _regEntryTarget,
    ProxyFactory.ProxyTarget memory _paramChangeEntryTarget,
    address _district,
    address _votingToken,
    TCR.TCRType _tcrType,
    TCR.RegistryEntryRepresentation memory _regEntryRepr,
    TCR.PermissionUserRoles memory _permissionUserRoles,
    TCR.Parameters memory _regParameters,
    TCR.Parameters memory _paramChangeParameters,
    bytes memory _ipfsData
  ) external {
  }


  function createRegistryEntry(
    address _creator,
    uint _tokenAmount,
    bytes memory _tokenIpfsData,
    bytes memory _ipfsData
  ) external {
  }


  function createParamChangeEntry(
    address _creator,
    EntriesGroup _entriesGroup,
    string memory _key,
    uint _value,
    bytes memory _ipfsData
  ) external {
  }


  function mintRegistryEntryToken(
    address _regEntry
  ) external {
  }


  function applyParamChangeEntry(
    address _paramChangeEntry
  ) external {
  }


  function _updateProxyTargets(
    ProxyFactory.ProxyTarget memory _regEntryTarget,
    ProxyFactory.ProxyTarget memory _paramChangeEntryTarget
  ) internal {
  }


  function targetUpdated(
    ProxyFactory.ProxyTarget memory _newTcrFactoryTarget,
    bytes memory _data
  ) external override onlySelf {
  }

}
