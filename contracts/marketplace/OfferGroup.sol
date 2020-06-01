pragma solidity >=0.4.22 <0.7.0;
pragma experimental ABIEncoderV2;

import "../district_designer/DDProxyFactory.sol";
import "../district_designer/DistrictDesigner.sol";
import "./OfferGroupFactory.sol";
import "./Offer.sol";


contract OfferGroup is UpdateTargetAndCallFallBack {

  uint public constant version = 1;

  function initialize(
    address _offerBaseContract,
    bytes memory _offerIpfsAbi,
    bytes16 _district,
    bytes16 _offerGroup,
    Offer.TradeAsset[] memory _assetsToOffer,
    Offer.TradeAsset[] memory _assetsToRequest,
    Offer.OfferType _offerType,
    Offer.Fees memory _fees,
    Offer.PermissionUserRoles memory _permissionUserRoles,
    bool _allowMultipleTrades,
    bytes memory _ipfsData
  ) public {
  }


  function createOffer(
    address _offerer,
    bytes16 _offer,
    Offer.TradeValue memory _offeredValue,
    Offer.TradeValue[] memory _requestedValues,
    Offer.TradeAuction memory _requestedAuction,
    bytes memory _ipfsData
  ) public {
  }


  function updateOfferGroup(
    Offer.TradeAsset[] memory _addedAssetsToOffer,
    Offer.TradeAsset[] memory _addedAssetsToRequest,
    Offer.Fees memory _fees,
    Offer.PermissionUserRoles memory _permissionUserRoles,
    bytes memory _ipfsData
  ) public {
  }


  function updateBaseContracts(
    address _offerBaseContract,
    bytes memory _offerIpfsAbi
  ) internal {
  }


  function targetUpdated(
    address _newTarget,
    bytes memory _ipfsData,
    bytes memory _data
  ) public override {
  }


}
