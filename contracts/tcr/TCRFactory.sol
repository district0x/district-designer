pragma solidity >=0.4.22 <0.7.0;
pragma experimental ABIEncoderV2;

import "../district_designer/DistrictDesigner.sol";
import "../district_designer/DDProxyFactory.sol";
import "./TCR.sol";
import "../tokens/ERC721Factory.sol";
import "../tokens/ERC1155Factory.sol";


contract TCRFactory is UpdateTargetAndCallFallBack {

  uint public constant version = 1;

  event TCRCreated(
    bytes16 district,
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
    bytes16 challenge,
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
    DistrictDesigner _districtDesigner,
    address _tcrBaseContract,
    bytes memory _tcrIpfsAbi,
    address _regEntryBaseContract,
    bytes memory _regEntryIpfsAbi,
    address _paramChangeEntryBaseContract,
    bytes memory _paramChangeEntryIpfsAbi,
    ERC721Factory _erc721Factory,
    ERC1155Factory _erc1155Factory
  ) public {
  }


  function createTCR(
    bytes16 _district,
    address _tcr,
    address _votingToken,
    TCR.TCRType _tcrType,
    TCR.RegistryEntryRepresentation memory _regEntryRepr,
    TCR.PermissionUserRoles memory _permissionUserRoles,
    TCR.Parameters memory _regParameters,
    TCR.Parameters memory _paramChangeParameters,
    bytes memory _ipfsData
  ) public
  {}


  function updateBaseContracts(
    address _tcrBaseContract,
    bytes memory _tcrIpfsAbi,
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


  function fireRegistryEntryCreatedEvent(
    bytes16 _regEntry,
    address _regEntryBaseContract,
    bytes memory _regEntryIpfsAbi,
    uint _regEntryVersion,
    address _creator,
    uint _tokenAmount,
    bytes memory _tokenIpfsData,
    bytes memory _ipfsData
  ) public
  {}


  function fireParamChangeEntryCreatedEvent(
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
  ) public
  {}


  function fireRegistryEntryTokenMintedEvent(
    address _regEntry,
    uint _tokenId
  ) public
  {}


  function fireParamChangeEntryAppliedEvent(
    address _paramChange
  ) public
  {}


  function fireChallengeCreatedEvent(
    address _entry,
    TCR.EntriesGroup _entriesGroup,
    bytes16 _challenge,
    address _challenger,
    uint _commitPeriodEnd,
    uint _revealPeriodEnd,
    uint _rewardPool,
    bytes memory _ipfsData
  ) public
  {}


  function fireChallengerRewardClaimedEvent(
    address _entry,
    TCR.EntriesGroup _entriesGroup,
    address _challenger,
    uint _amount
  ) public
  {}


  function fireCreatorRewardClaimedEvent(
    address _entry,
    TCR.EntriesGroup _entriesGroup,
    address _creator,
    uint _amount
  ) public
  {}


  function fireVotesReclaimedEvent(
    address _entry,
    TCR.EntriesGroup _entriesGroup,
    address _voter,
    uint _amount
  ) public
  {}


  function fireVoteCommittedEvent(
    address _entry,
    TCR.EntriesGroup _entriesGroup,
    address _voter,
    uint _amount
  ) public
  {}


  function fireVoteRevealedEvent(
    address _entry,
    TCR.EntriesGroup _entriesGroup,
    address _voter,
    TCR.VoteOption _voteOption,
    uint _amount
  ) public
  {}


  function fireVoteRewardClaimedEvent(
    address _entry,
    TCR.EntriesGroup _entriesGroup,
    address _voter,
    uint _amount
  ) public
  {}


  function fireTcrBaseContractsUpdated(
    address _tcr,
    address _regEntryBaseContract,
    bytes memory _regEntryIpfsAbi,
    address paramChangeEntryBaseContract,
    bytes memory _paramChangeEntryIpfsAbi
  ) public
  {}
}
