pragma solidity >=0.4.22 <0.7.0;
pragma experimental ABIEncoderV2;

import "../district_designer/DistrictDesigner.sol";
import "./OfferGroupFactory.sol";
import "./OfferGroup.sol";

contract Offer {

  uint public constant version = 1;

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

  struct PermissionUserRoles {
    bytes16[] createOfferUserRoles;
    bytes16[] offerResponseUserRoles;
    bytes16[] resolveDisputeUserRoles;
  }

  struct Fees {
    uint createOfferFee;
    uint offerResponseFee;
  }


  struct TradeAsset {
    address tokenAddress;
    TradeAssetCategory tradeAssetCategory;
  }

  struct TradeValue {
    TradeAsset tradeAsset;
    uint tokenAmount;
    uint tokenId;
  }

  struct TradeAuction {
    TradeAsset tradeAsset;
    uint startAmount;
    uint endAmount;
    uint minAmount;
    uint minBidStep;
    uint duration;
  }

  function initialize(
    address _offerer,
    TradeValue memory _offeredValue,
    TradeValue[] memory _requestedValues,
    TradeAuction memory _requestedAuction,
    bytes memory _ipfsData
  ) public {}


  function createOfferResponse(
    address _respondent,
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
