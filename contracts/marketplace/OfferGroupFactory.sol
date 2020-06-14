// SPDX-License-Identifier: MIT
pragma solidity >=0.4.22 <0.7.0;
pragma experimental ABIEncoderV2;

import "../district_designer/DistrictFactory.sol";
import "../district_designer/proxy/ProxyFactory.sol";
import "./MrktTypes.sol";

/**
 * @dev Factory contract for creating {OfferGroup}
 * It also emits all events related to marketplace module
 * This contract is used through a proxy, therefore its address will never change.
 * No breaking changes will be introduced for events,
 * so they all stay accessible from a single contract
 */

contract OfferGroupFactory is UpdateTargetAndCallFallBack {

  uint public constant version = 1;

  event OfferGroupCreated(
    bytes16 district,
    address offerGroup,
    address offerGroupBaseContract,
    bytes offerGroupIpfsAbi,
    uint offerGroupVersion,
    MrktTypes.TradeAsset[] offerableAssets,
    MrktTypes.TradeAsset[] requestableAssets,
    MrktTypes.OfferType[] allowedOfferTypes,
    MrktTypes.Fees fees,
    MrktTypes.PermissionUserRoles permissionUserRoles,
    bytes ipfsData,
    uint timestamp
  );

  event OfferGroupUpdated(
    address offerGroup,
    MrktTypes.TradeAsset[] offerableAssets,
    MrktTypes.TradeAsset[] requestableAssets,
    MrktTypes.OfferType[] allowedOfferTypes,
    MrktTypes.Fees fees,
    MrktTypes.PermissionUserRoles permissionUserRoles,
    bytes ipfsData,
    uint timestamp
  );

  event OfferCreated(
    address offer,
    address offerBaseContract,
    bytes offerIpfsAbi,
    uint offerVersion,
    MrktTypes.OfferType offerType,
    address offerer,
    MrktTypes.TradeValue offeredValue,
    MrktTypes.OfferRequest request,
    MrktTypes.TradeValue availableSupply,
    bytes ipfsData,
    uint timestamp
  );

  event OfferResponseCreated(
    address offer,
    address respondent,
    uint offerResponseIndex,
    MrktTypes.OfferResponse response,
    MrktTypes.TradeValue offererReceivedValue,
    MrktTypes.TradeValue respondentReceivedValue,
    MrktTypes.TradeValue availableSupply,
    bytes ipfsData,
    uint timestamp
  );

  event OfferRequestUpdated(
    address offer,
    MrktTypes.OfferRequest request,
    bytes ipfsData,
    uint timestamp
  );

  event OfferResponseAccepted(
    address offer,
    uint offerResponseIndex,
    MrktTypes.TradeValue offererReceivedValue,
    MrktTypes.TradeValue respondentReceivedValue,
    MrktTypes.TradeValue availableSupply,
    bytes ipfsData,
    uint timestamp
  );

  event OfferAvailableSupplyUpdated(
    address offer,
    MrktTypes.TradeValue availableSupply,
    uint timestamp
  );

  event OfferDeliverableReceived(
    address offer,
    uint offerResponseIndex,
    address receiver,
    MrktTypes.TradeValue offererReceivedValue,
    MrktTypes.TradeValue respondentReceivedValue,
    MrktTypes.TradeValue availableSupply,
    bytes ipfsData,
    uint timestamp
  );

  event OfferCanceled(
    address offer,
    uint timestamp
  );

  event OfferResponseCanceled(
    uint offerResponseIndex,
    uint timestamp
  );

  event DisputeRaised(
    uint offerResponseIndex,
    address raisedBy,
    bytes ipfsData,
    uint timestamp
  );

  event DisputeResolved(
    uint offerResponseIndex,
    MrktTypes.TradeValue offererReceivedValue,
    MrktTypes.TradeValue respondentReceivedValue,
    MrktTypes.TradeValue availableSupply,
    address resolvedBy,
    bytes ipfsData,
    uint timestamp
  );

  event BaseContractsUpdated(
    address offerGroupBaseContract,
    bytes offerGroupIpfsAbi,
    address offerBaseContract,
    bytes offerIpfsAbi,
    uint timestamp
  );

  event OfferGroupBaseContractsUpdated(
    address offerGroup,
    address offerBaseContract,
    bytes offerIpfsAbi,
    uint timestamp
  );

  /**
   * @dev Contract initialization
   * It is manually called instead of native contructor,
   * because this contract is used through a proxy.
   * This function cannot be called twice.
   *
   * @param _districtFactory Address of {DistrictFactory}
   * @param _offerGroupBaseContract Address of base contract for {OfferGroup} proxies
   * @param _offerGroupIpfsAbi IPFS hash of ABI for {OfferGroup} contract
   * @param _offerBaseContract Address of base contract for {Offer} proxies
   * @param _offerIpfsAbi IPFS hash of ABI for {Offer} contract
   * TODO: Needs implementation
   */
  function initialize(
    DistrictFactory _districtFactory,
    address _offerGroupBaseContract,
    bytes calldata _offerGroupIpfsAbi,
    address _offerBaseContract,
    bytes calldata _offerIpfsAbi
  ) external {
  }


  /**
   * @dev Creates a new {OfferGroup}
   * It creates {DistrictAdminProxy} forwarding to {OfferGroup} contract
   *
   * Requirements:
   *
   * - `_district` must be existing district created by {DistrictFactory}
   * - `msg.sender` must have "MANAGE_OFFER_GROUPS" permission for given district
   *
   * Emits an {OfferGroupCreated} event
   *
   * See spec :marketplace/offer-group-created for format of _ipfsData file
   * TODO: Needs implementation
   */
  function createOfferGroup(
    address _district,
    MrktTypes.TradeAsset[] calldata _offerableAssets,
    MrktTypes.TradeAsset[] calldata _requestableAssets,
    MrktTypes.OfferType[] calldata _allowedOfferTypes,
    MrktTypes.Fees calldata _fees,
    MrktTypes.PermissionUserRoles calldata _permissionUserRoles,
    bytes calldata _ipfsData
  ) external {
  }

  /**
   * @dev Updates contract addresses and ABIs which serve as base contracts for created proxies
   * It's meant to be called only by {targetUpdated}
   *
   * Requirements:
   *
   * - `_offerGroupBaseContract` cannot be zero address
   * - `_offerGroupIpfsAbi` must be valid ipfs hash
   * - `_offerBaseContract` cannot be zero address
   * - `_offerIpfsAbi` must be valid ipfs hash
   *
   * Emits an {BaseContractsUpdated} event
   * TODO: Needs implementation
   */
  function _updateBaseContracts(
    address _offerGroupBaseContract,
    bytes calldata _offerGroupIpfsAbi,
    address _offerBaseContract,
    bytes calldata _offerIpfsAbi
  ) internal {
  }

  /**
   * @dev This function is called automatically when proxy updates its target
   * It should decode `_data` into arguments of {_updateBaseContracts} and call it
   * TODO: Needs implementation
   */
  function targetUpdated(
    address _newTarget,
    bytes calldata _ipfsAbi,
    bytes calldata _data
  ) external override onlySelf {
  }


  /**
   * TODO: Needs implementation
   */
  function fireOfferGroupUpdated(
    MrktTypes.TradeAsset[] calldata _offerableAssets,
    MrktTypes.TradeAsset[] calldata _requestableAssets,
    MrktTypes.OfferType[] calldata _allowedOfferTypes,
    MrktTypes.Fees calldata _fees,
    MrktTypes.PermissionUserRoles calldata _permissionUserRoles,
    bytes calldata _ipfsData
  ) external {
  }


  /**
   * TODO: Needs implementation
   */
  function fireOfferCreated(
    address _offer,
    address _offerBaseContract,
    bytes calldata _offerIpfsAbi,
    uint _offerVersion,
    address _offerer,
    MrktTypes.TradeValue calldata _offeredValue,
    MrktTypes.OfferRequest calldata request,
    MrktTypes.TradeValue calldata _availableSupply,
    bytes calldata _ipfsData
  ) external {
  }


  /**
   * TODO: Needs implementation
   */
  function fireOfferResponseCreated(
    address _offer,
    address _respondent,
    uint _offerResponseIndex,
    MrktTypes.OfferResponse calldata _response,
    MrktTypes.TradeValue calldata _offererReceivedValue,
    MrktTypes.TradeValue calldata _respondentReceivedValue,
    MrktTypes.TradeValue calldata _availableSupply,
    bytes calldata _ipfsData
  ) external {
  }


  /**
   * TODO: Needs implementation
   */
  function fireOfferRequestUpdated(
    address _offer,
    MrktTypes.OfferRequest calldata request,
    bytes calldata _ipfsData
  ) external {
  }


  /**
   * TODO: Needs implementation
   */
  function fireOfferResponseAccepted(
    address _offer,
    uint _offerResponseIndex,
    MrktTypes.TradeValue calldata _offererReceivedValue,
    MrktTypes.TradeValue calldata _respondentReceivedValue,
    MrktTypes.TradeValue calldata _availableSupply,
    bytes calldata _ipfsData
  ) external {
  }


  /**
   * TODO: Needs implementation
   */
  function fireOfferAvailableSupplyUpdated(
    address _offer,
    MrktTypes.TradeValue calldata _availableSupply
  ) external {
  }


  /**
   * TODO: Needs implementation
   */
  function fireOfferDeliverableReceived(
    address _offer,
    uint _offerResponseIndex,
    address _receiver,
    MrktTypes.TradeValue calldata _offererReceivedValue,
    MrktTypes.TradeValue calldata _respondentReceivedValue,
    MrktTypes.TradeValue calldata _availableSupply,
    bytes calldata _ipfsData
  ) external {
  }

  /**
   * TODO: Needs implementation
   */
  function fireOfferCanceled(
    address _offer
  ) external {
  }

  /**
   * TODO: Needs implementation
   */
  function fireOfferResponseCanceled(
    uint _offerResponseIndex
  ) external {
  }

  /**
   * TODO: Needs implementation
   */
  function fireDisputeRaised(
    uint _offerResponseIndex,
    address _raisedBy
  ) external {
  }

  /**
   * TODO: Needs implementation
   */
  function fireDisputeResolved(
    uint _offerResponseIndex,
    MrktTypes.TradeValue calldata _offererReceivedValue,
    MrktTypes.TradeValue calldata _respondentReceivedValue,
    MrktTypes.TradeValue calldata _availableSupply,
    address _resolvedBy
  ) external {
  }

  /**
   * TODO: Needs implementation
   */
  function fireOfferGroupBaseContractsUpdated(
    address offerBaseContract,
    bytes calldata offerIpfsAbi
  ) external {
  }
}
