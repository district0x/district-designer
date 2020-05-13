pragma solidity >=0.4.22 <0.7.0;
pragma experimental ABIEncoderV2;

import "../district_designer/DistrictDesigner.sol";
import "../district_designer/DDProxyFactory.sol";
import "./TCR.sol";
import  "../tokens/ERC721Factory.sol";
import  "../tokens/ERC1155Factory.sol";


contract TCRFactory {

  uint public constant version = 1;

  event TCRCreatedEvent(
    bytes16 district,
    bytes16 tcr,
    uint tcrVersion,
    address tcrAddress,
    address votingToken,
    TCR.TCRType tcrType,
    TCR.RegistryEntryRepresentation regEntryRepr,
    TCR.PermissionIds permissionIds,
    TCR.Parameters regParameters,
    TCR.Parameters paramChangeParameters,
    uint timestamp
  );

  event RegistryEntryCreatedEvent(
    bytes16 regEntry,
    address regEntryAddress,
    address creator,
    uint tokenAmount,
    bytes tokenMetaHash,
    uint timestamp
  );

  event ParamChangeEntryCreatedEvent(
    bytes16 paramChange,
    address paramChangeAddress,
    address creator,
    TCR.EntriesGroup paramGroup,
    string key,
    uint value,
    uint originalValue
  );

  event ChallengeCreatedEvent(
    bytes16 entry,
    TCR.EntriesGroup entriesGroup,
    bytes16 challenge,
    address challenger,
    uint commitPeriodEnd,
    uint revealPeriodEnd,
    uint rewardPool,
    uint timestamp
  );

  event ChallengerRewardClaimedEvent(
    bytes16 entry,
    TCR.EntriesGroup entriesGroup,
    address challenger,
    uint amount,
    uint timestamp
  );

  event CreatorRewardClaimedEvent(
    bytes16 entry,
    TCR.EntriesGroup entriesGroup,
    address creator,
    uint amount,
    uint timestamp
  );

  event VotesReclaimedEvent(
    bytes16 entry,
    TCR.EntriesGroup entriesGroup,
    address voter,
    uint amount,
    uint timestamp
  );

  event VoteCommittedEvent(
    bytes16 entry,
    TCR.EntriesGroup entriesGroup,
    address voter,
    uint amount,
    uint timestamp
  );

  event VoteRevealedEvent(
    bytes16 entry,
    TCR.EntriesGroup entriesGroup,
    address voter,
    TCR.VoteOption voteOption,
    uint amount,
    uint timestamp
  );

  event VoteRewardClaimedEvent(
    bytes16 entry,
    TCR.EntriesGroup entriesGroup,
    address voter,
    uint amount,
    uint timestamp
  );


  function initialize(
    DistrictDesigner _districtDesigner,
    address _TCRContractBase,
    address _registryEntryContractBase,
    address _paramChangeEntryContractBase,
    ERC721Factory _erc721Factory,
    ERC1155Factory _erc1155Factory
  ) public {
  }


  function createTCR(
    bytes16 _district,
    bytes16 _tcr,
    address _votingToken,
    TCR.TCRType _tcrType,
    TCR.RegistryEntryRepresentation memory _regEntryRepr,
    TCR.PermissionIds memory _permissionIds,
    TCR.Parameters memory _regParameters,
    TCR.Parameters memory _paramChangeParameters
  ) public
  {}


  function setBaseContracts(
    address _TCRContractBase,
    address _registryEntryContractBase,
    address _paramChangeEntryContractBase
  ) public
  {}


  function fireRegistryEntryCreatedEvent(
    bytes16 _regEntry,
    address _creator,
    uint _tokenAmount,
    bytes memory _tokenMetaHash
  ) public
  {}


  function fireParamChangeEntryCreatedEvent(
    bytes16 _paramChange,
    address _creator,
    TCR.EntriesGroup _paramGroup,
    string memory _key,
    uint _value,
    uint _originalValue
  ) public
  {}


  function fireChallengeCreatedEvent(
    bytes16 _entry,
    TCR.EntriesGroup _entriesGroup,
    bytes16 _challenge,
    address _challenger,
    uint _commitPeriodEnd,
    uint _revealPeriodEnd,
    uint _rewardPool
  ) public
  {}


  function fireChallengerRewardClaimedEvent(
    bytes16 _entry,
    TCR.EntriesGroup _entriesGroup,
    address _challenger,
    uint _amount
  ) public
  {}


  function fireCreatorRewardClaimedEvent(
    bytes16 _entry,
    TCR.EntriesGroup _entriesGroup,
    address _creator,
    uint _amount
  ) public
  {}


  function fireVotesReclaimedEvent(
    bytes16 _entry,
    TCR.EntriesGroup _entriesGroup,
    address _voter,
    uint _amount
  ) public
  {}


  function fireVoteCommittedEvent(
    bytes16 _entry,
    TCR.EntriesGroup _entriesGroup,
    address _voter,
    uint _amount
  ) public
  {}


  function fireVoteRevealedEvent(
    bytes16 _entry,
    TCR.EntriesGroup _entriesGroup,
    address _voter,
    TCR.VoteOption _voteOption,
    uint _amount
  ) public
  {}


  function fireVoteRewardClaimedEvent(
    bytes16 _entry,
    TCR.EntriesGroup _entriesGroup,
    address _voter,
    uint _amount
  ) public
  {}
}
