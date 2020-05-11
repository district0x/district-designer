pragma solidity >=0.4.22 <0.7.0;
pragma experimental ABIEncoderV2;

import  "../_libs/proxy/ProxyFactory.sol";
import "../district_designer/DistrictDesigner.sol";
import "./OfferGroupFactory.sol";
import "./Offer.sol";


contract OfferGroup {

  uint public constant version = 1;

  function initialize(
    bytes16 _district,
    bytes16 _offerGroup,
    Offer.TradeAsset[] memory _assetsToOffer,
    Offer.TradeAsset[] memory _assetsToRequest,
    Offer.OfferType _offerType,
    Offer.Fees memory _fees,
    Offer.PermissionIds memory _permissionIds,
    address[] memory _disputeResolvers,
    Offer.OfferSettings memory _offerSettings
  ) public {}


  function createOffer(
    address _offerer,
    bytes16 _offer,
    Offer.TradeValue memory _offeredValue,
    Offer.TradeValue[] memory _requestedValues,
    Offer.TradeAuction memory _requestedAuction
  ) public
  {}


  function updateOfferGroup(
    Offer.TradeAsset[] memory _addedAssetsToOffer,
    Offer.TradeAsset[] memory _addedAssetsToRequest,
    Offer.Fees memory _fees,
    address[] memory _disputeResolvers
  ) public
  {}


}
