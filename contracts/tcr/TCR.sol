pragma solidity >=0.4.22 <0.7.0;
pragma experimental ABIEncoderV2;

import "../district_designer/DDProxyFactory.sol";

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
    BaseContract memory regEntryBaseContract,
    BaseContract memory paramChangeBaseContract,
    bytes16 _district,
    address _votingToken,
    TCR.TCRType _tcrType,
    TCR.RegistryEntryRepresentation memory _regEntryRepr,
    TCR.PermissionUserRoles memory _permissionUserRoles,
    TCR.Parameters memory _regParameters,
    TCR.Parameters memory _paramChangeParameters,
    bytes memory _ipfsData
  ) public {
  }


  function createRegistryEntry(
    address _creator,
    uint _tokenAmount,
    bytes memory _tokenIpfsData,
    bytes memory _ipfsData
  ) public {
  }


  function createParamChangeEntry(
    address _creator,
    EntriesGroup _entriesGroup,
    string memory _key,
    uint _value,
    bytes memory _ipfsData
  ) public {
  }


  function mintRegistryEntryToken(
    address _regEntry
  ) public {
  }


  function applyParamChangeEntry(
    address _paramChangeEntry
  ) public {
  }


  function updateBaseContracts(
    address _regEntryBaseContract,
    bytes memory _regEntryIpfsAbi,
    address _paramChangeEntryBaseContract,
    bytes memory _paramChangeEntryIpfsAbi
  ) internal {
  }


  function targetUpdated(
    address _newTarget,
    bytes memory _ipfsData,
    bytes memory _data
  ) public override {
  }

}
