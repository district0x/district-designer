pragma solidity >=0.4.22 <0.7.0;
pragma experimental ABIEncoderV2;

import "../district_designer/DistrictDesigner.sol";
import "./OffersGroupFactory.sol";

contract OffersGroup {

  enum OfferType {
    FIXED_PRICE,
    DYNAMIC_PRICE,
    HIGEST_BID_WINS,
    OFFERER_PICKS_WINNER,
    MULTIPLE_FIXED_PRICES
  }

  enum TradeAssetCategory {
    ETH,
    ERC20,
    ERC721,
    ERC1155,
    DELIVERABLE
  }

  struct PermissionIds {
    bytes32 createOffer;
    bytes32 offerResponse;
  }

  struct Fees {
    uint createOffer;
    uint offerResponse;
  }

  struct OfferSettings {
    bool allowMultipleTrades;
  }

  struct TradeAsset {
    address tokenAddress;
    TradeAssetCategory category;
  }

  struct TradeValue {
    TradeAsset asset;
    uint tokenAmount;
    uint tokenId;
  }

  struct TradeAuction {
    TradeAsset asset;
    uint startAmount;
    uint endAmount;
    uint minAmount;
    uint minBidStep;
    uint duration;
  }

  constructor(
    bytes16 _district,
    bytes16 _offerConfig,
    TradeAsset[] memory _assetsToOffer,
    TradeAsset[] memory _assetsToRequest,
    OfferType _offerType,
    Fees memory _fees,
    PermissionIds memory _permissionIds,
    address[] memory _disputeResolvers,
    OfferSettings memory _offerSettings
  ) public {}


  function createOffer(
    address _offerer,
    bytes16 _offer,
    TradeValue memory _offeredValue,
    TradeValue[] memory _requestedValues,
    TradeAuction memory _requestedAuction
  ) public
  {}


  function createOfferResponse(
    address _respondent,
    bytes16 _offer,
    bytes16 _offerResponse,
    TradeAsset[] memory _chosenAsset,
    TradeValue memory _counterOfferedValue
  ) public
  {}


  function acceptOfferResponse(
    bytes16 _offerResponse
  ) public
  {}


  function closeOffer(
    bytes16 _offer
  ) public
  {}


  function cancelOfferResponse(
    bytes16 _offerResponse
  ) public
  {}


  function raiseDispute(
    bytes16 _offerResponse
  ) public
  {}


  function resolveDispute(
    bytes16 _offerResponse,
    TradeValue memory _valueForOfferer,
    TradeValue memory _valueForRespondent
  ) public
  {}


  function updateOfferConfig(
    TradeAsset[] memory _addedAssetsToOffer,
    TradeAsset[] memory _addedAssetsToRequest,
    Fees memory _fees,
    address[] memory _disputeResolvers
  ) public
  {}


}
