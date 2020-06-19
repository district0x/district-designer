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
 * No breaking changes will be introduced for events, so they all stay accessible from a single contract.
 */

contract OfferGroupFactory is UpdateTargetAndCallFallBack {

  uint public constant version = 1;

  event OfferGroupCreated(
    address district,
    address offerGroup,
    ProxyFactory.ProxyTarget offerGroupTarget,
    uint offerGroupVersion,
    MrktTypes.TradeAsset[] offerableAssets,
    MrktTypes.TradeAsset[] requestableAssets,
    MrktTypes.OfferType[] allowedOfferTypes,
    MrktTypes.PermissionUserRoles permissionUserRoles,
    bytes ipfsData,
    uint timestamp
  );

  event OfferGroupUpdated(
    address offerGroup,
    MrktTypes.TradeAsset[] offerableAssets,
    MrktTypes.TradeAsset[] requestableAssets,
    MrktTypes.OfferType[] allowedOfferTypes,
    MrktTypes.PermissionUserRoles permissionUserRoles,
    bytes ipfsData,
    uint timestamp
  );

  event OfferCreated(
    address offer,
    ProxyFactory.ProxyTarget offerTarget,
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
    MrktTypes.TradeValue responseValue,
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

  event AvailableSupplyUpdated(
    address offer,
    MrktTypes.TradeValue availableSupply,
    uint timestamp
  );

  event DeliverableReceived(
    address offer,
    uint offerResponseIndex,
    address receiver,
    MrktTypes.TradeValue offererReceivedValue,
    MrktTypes.TradeValue respondentReceivedValue,
    MrktTypes.TradeValue availableSupply,
    bytes ipfsData,
    uint timestamp
  );

  event OfferResponseCanceled(
    address offer,
    uint offerResponseIndex,
    uint timestamp
  );

  event DisputeRaised(
    address offer,
    uint offerResponseIndex,
    address raisedBy,
    bytes ipfsData,
    uint timestamp
  );

  event DisputeResolved(
    address offer,
    uint offerResponseIndex,
    MrktTypes.TradeValue offererReceivedValue,
    MrktTypes.TradeValue respondentReceivedValue,
    MrktTypes.TradeValue availableSupply,
    address resolvedBy,
    bytes ipfsData,
    uint timestamp
  );

  event SponsorshipAdded(
    address offer,
    address sponsor,
    MrktTypes.TradeValue sponsorship,
    uint timestamp
  );

  event SponsorshipWithdrawn(
    address offer,
    address sponsor,
    MrktTypes.TradeValue withdrawal,
    uint timestamp
  );

  event ProxyTargetsUpdated(
    ProxyFactory.ProxyTarget offerGroupTarget,
    ProxyFactory.ProxyTarget fixedPricesOfferTarget,
    ProxyFactory.ProxyTarget dynamicPriceOfferTarget,
    ProxyFactory.ProxyTarget highestBidAuctionOfferTarget,
    ProxyFactory.ProxyTarget multiTokenAuctionOfferTarget,
    ProxyFactory.ProxyTarget deliverableAuctionOfferTarget,
    uint timestamp
  );

  event OfferGroupProxyTargetsUpdated(
    address offerGroup,
    ProxyFactory.ProxyTarget fixedPricesOfferTarget,
    ProxyFactory.ProxyTarget dynamicPriceOfferTarget,
    ProxyFactory.ProxyTarget highestBidAuctionOfferTarget,
    ProxyFactory.ProxyTarget multiTokenAuctionOfferTarget,
    ProxyFactory.ProxyTarget deliverableAuctionOfferTarget,
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
    ProxyFactory.ProxyTarget memory _offerGroupTarget,
    ProxyFactory.ProxyTarget memory _fixedPricesOfferTarget,
    ProxyFactory.ProxyTarget memory _dynamicPriceOfferTarget,
    ProxyFactory.ProxyTarget memory _highestBidAuctionOfferTarget,
    ProxyFactory.ProxyTarget memory _multiTokenAuctionOfferTarget,
    ProxyFactory.ProxyTarget memory _deliverableAuctionOfferTarget
  ) external {
  }


  /**
   * @dev Creates a new {OfferGroup}
   * It creates {DistrictAdminProxy} forwarding to `offerGroupTarget` contract
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
    MrktTypes.TradeAsset[] memory _offerableAssets,
    MrktTypes.TradeAsset[] memory _requestableAssets,
    MrktTypes.OfferType[] memory _allowedOfferTypes,
    MrktTypes.PermissionUserRoles memory _permissionUserRoles,
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
    ProxyFactory.ProxyTarget memory _offerGroupTarget,
    ProxyFactory.ProxyTarget memory _fixedPricesOfferTarget,
    ProxyFactory.ProxyTarget memory _dynamicPriceOfferTarget,
    ProxyFactory.ProxyTarget memory _highestBidAuctionOfferTarget,
    ProxyFactory.ProxyTarget memory _multiTokenAuctionOfferTarget,
    ProxyFactory.ProxyTarget memory _deliverableAuctionOfferTarget
  ) internal {
  }


  /**
   * @dev This function is called automatically when proxy forwarding to this contract
   * update its target.
   * It should decode `_data` into arguments of {_updateProxyTargets} and call it
   * TODO: Needs implementation
   */
  function targetUpdated(
    ProxyFactory.ProxyTarget memory _newOfferGroupFactoryTarget,
    bytes memory _data
  ) external override onlySelf {
  }


  /**
   * TODO: Needs implementation
   */
  function fireOfferGroupUpdated(
    MrktTypes.TradeAsset[] memory _offerableAssets,
    MrktTypes.TradeAsset[] memory _requestableAssets,
    MrktTypes.OfferType[] memory _allowedOfferTypes,
    MrktTypes.PermissionUserRoles memory _permissionUserRoles,
    bytes memory _ipfsData
  ) external {
  }


  /**
   * TODO: Needs implementation
   */
  function fireOfferCreated(
    address _offer,
    ProxyFactory.ProxyTarget memory _offerTarget,
    uint _offerVersion,
    address _offerer,
    MrktTypes.TradeValue memory _offeredValue,
    MrktTypes.OfferRequest memory request,
    MrktTypes.TradeValue memory _availableSupply,
    bytes memory _ipfsData
  ) external {
  }


  /**
   * TODO: Needs implementation
   */
  function fireOfferResponseCreated(
    address _offer,
    address _respondent,
    uint _offerResponseIndex,
    MrktTypes.TradeValue memory _responseValue,
    MrktTypes.TradeValue memory _offererReceivedValue,
    MrktTypes.TradeValue memory _respondentReceivedValue,
    MrktTypes.TradeValue memory _availableSupply,
    bytes memory _ipfsData
  ) external {
  }


  /**
   * TODO: Needs implementation
   */
  function fireOfferRequestUpdated(
    address _offer,
    MrktTypes.OfferRequest memory request,
    bytes memory _ipfsData
  ) external {
  }


  /**
   * TODO: Needs implementation
   */
  function fireOfferResponseAccepted(
    address _offer,
    uint _offerResponseIndex,
    MrktTypes.TradeValue memory _offererReceivedValue,
    MrktTypes.TradeValue memory _respondentReceivedValue,
    MrktTypes.TradeValue memory _availableSupply,
    bytes memory _ipfsData
  ) external {
  }


  /**
   * TODO: Needs implementation
   */
  function fireAvailableSupplyUpdated(
    address _offer,
    MrktTypes.TradeValue memory _availableSupply
  ) external {
  }


  /**
   * TODO: Needs implementation
   */
  function fireDeliverableReceived(
    address _offer,
    uint _offerResponseIndex,
    MrktTypes.TradeValue memory _offererReceivedValue,
    MrktTypes.TradeValue memory _respondentReceivedValue,
    MrktTypes.TradeValue memory _availableSupply,
    bytes memory _ipfsData
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
    MrktTypes.TradeValue memory _offererReceivedValue,
    MrktTypes.TradeValue memory _respondentReceivedValue,
    MrktTypes.TradeValue memory _availableSupply,
    address _resolvedBy
  ) external {
  }


  /**
   * TODO: Needs implementation
   */
  function fireSponsorshipAdded(
    address _sponsor,
    MrktTypes.TradeValue memory _sponsorship
  ) external {
  }


  /**
   * TODO: Needs implementation
   */
  function fireSponsorshipWithdrawn(
    address _sponsor,
    MrktTypes.TradeValue memory _withdrawal,
    MrktTypes.TradeValue memory _availableSupply
  ) external {
  }


  /**
   * TODO: Needs implementation
   */
  function fireOfferGroupProxyTargetsUpdated(
    ProxyFactory.ProxyTarget memory _fixedPricesOfferTarget,
    ProxyFactory.ProxyTarget memory _dynamicPriceOfferTarget,
    ProxyFactory.ProxyTarget memory _highestBidAuctionOfferTarget,
    ProxyFactory.ProxyTarget memory _multiTokenAuctionOfferTarget,
    ProxyFactory.ProxyTarget memory _deliverableAuctionOfferTarget
  ) external {
  }
}
