pragma solidity >=0.4.22 <0.7.0;
pragma experimental ABIEncoderV2;

import "../district_designer/DistrictDesigner.sol";
import "./OfferGroupFactory.sol";
import "./OfferGroup.sol";

contract Offer {

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

  function initialize(
    address _offerer,
    bytes16 _offer,
    TradeValue memory _offeredValue,
    TradeValue[] memory _requestedValues,
    TradeAuction memory _requestedAuction,
    bytes memory _ipfsData
  ) public {}


  function createOfferResponse(
    address _respondent,
    bytes16 _offer,
    bytes16 _offerResponse,
    TradeAsset[] memory _chosenAsset,
    TradeValue memory _counterOfferedValue,
    bytes memory _ipfsData
  ) public {
  }


  function acceptOfferResponse(
    bytes16 _offerResponse
  ) public {
  }


  function closeOffer(
  ) public {
  }


  function cancelOfferResponse(
    bytes16 _offerResponse
  ) public {
  }


  function raiseDispute(
    bytes16 _offerResponse
  ) public {
  }


  function resolveDispute(
    bytes16 _offerResponse,
    TradeValue memory _valueForOfferer,
    TradeValue memory _valueForRespondent
  ) public {
  }

}
