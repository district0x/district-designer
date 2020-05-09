pragma solidity >=0.4.22 <0.7.0;
pragma experimental ABIEncoderV2;

contract TCR {

  enum TCRType {
    INITIAL_CHALLENGE_PERIOD,
    CHALLENGEABLE_ANYTIME
  }

  enum RegistryEntryRepresentationCategory {
    ERC721,
    ERC1155,
    NO_TOKEN
  }

  enum ParameterGroup {
    REGISTRY_ENTRIES,
    PARAM_CHANGE_ENTRIES
  }

  struct RegistryEntryRepresentation {
    RegistryEntryRepresentationCategory category;
    string tokenName;
    string tokenSymbol;
    string baseMetadataURI;
  }

  struct PermissionIds {
    bytes32 createRegEntry;
    bytes32 createParamChangeEntry;
  }

  struct Parameters {
    uint deposit;
    uint challengePeriodDuration;
    uint voteCommitPeriodDuration;
    uint voteRevealPeriodDuration;
    uint challengeDepositDispensation;
    uint voteQuorum;
  }


  constructor(
    bytes16 _district,
    bytes16 _tcr,
    address _votingToken,
    TCR.TCRType _tcrType,
    TCR.RegistryEntryRepresentation memory _regEntryRepr,
    TCR.PermissionIds memory _permissionIds,
    TCR.Parameters memory _regParameters,
    TCR.Parameters memory _paramChangeParameters
  ) public {
  }


  function createRegistryEntry(
    address _creator,
    bytes16 _regEntry,
    uint _tokenAmount,
    bytes memory _tokenMetaHash
  ) public
  {}


  function createParamChangeEntry(
    address _creator,
    bytes16 _paramChangeEntry,
    ParameterGroup _paramGroup,
    string memory _key,
    uint _value
  ) public
  {}


  function mintRegistryEntryToken(
    bytes16 _regEntry
  ) public
  {}


  function burnRegistryEntryToken(
    bytes16 _regEntry
  ) public
  {}


  function applyParamChangeEntry(
    bytes16 _paramChangeEntry
  ) public
  {}

}
