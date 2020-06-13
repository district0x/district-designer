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

  struct BaseContract {
    address baseContractAddress;
    bytes ipfsAbi;
  }

  function initialize(
    BaseContract calldata regEntryBaseContract,
    BaseContract calldata paramChangeBaseContract,
    address _district,
    address _votingToken,
    TCR.TCRType _tcrType,
    TCR.RegistryEntryRepresentation calldata _regEntryRepr,
    TCR.PermissionUserRoles calldata _permissionUserRoles,
    TCR.Parameters calldata _regParameters,
    TCR.Parameters calldata _paramChangeParameters,
    bytes calldata _ipfsData
  ) external {
  }


  function createRegistryEntry(
    address _creator,
    uint _tokenAmount,
    bytes calldata _tokenIpfsData,
    bytes calldata _ipfsData
  ) external {
  }


  function createParamChangeEntry(
    address _creator,
    EntriesGroup _entriesGroup,
    string calldata _key,
    uint _value,
    bytes calldata _ipfsData
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


  function _updateBaseContracts(
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

}
