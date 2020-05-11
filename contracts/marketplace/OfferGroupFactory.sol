pragma solidity >=0.4.22 <0.7.0;
pragma experimental ABIEncoderV2;

import "../district_designer/DistrictDesigner.sol";
import "../district_designer/DDProxyFactory.sol";
import "./Offer.sol";

contract OfferGroupFactory {

  uint public constant version = 1;

  event OfferGroupCreatedEvent(
    bytes16 district,
    bytes16 offerGroup,
    address offerGroupAddress,
    uint offerGroupVersion,
    Offer.TradeAsset[] assetsToOffer,
    Offer.TradeAsset[] assetsToRequest,
    Offer.OfferType offerType,
    Offer.Fees fees,
    Offer.PermissionIds permissionIds,
    address[] disputeResolvers,
    Offer.OfferSettings offerSettings,
    uint timestamp
  );

  event OfferGroupUpdatedEvent(
    bytes16 offerGroup,
    Offer.TradeAsset[] addedAssetsToOffer,
    Offer.TradeAsset[] addedAssetsToRequest,
    Offer.Fees fees,
    address[] disputeResolvers,
    uint timestamp
  );

  event OfferCreatedEvent(
    bytes16 offer,
    address offerer,
    Offer.TradeValue offeredValue,
    Offer.TradeValue[] requestedValues,
    Offer.TradeAuction requestedAuction,
    uint timestamp
  );

  event OfferResponseCreatedEvent(
    bytes16 offer,
    address respondent,
    bytes16 offerResponse,
    Offer.TradeValue offererTradedValue,
    Offer.TradeValue respondentTradedValue,
    uint timestamp
  );

  event OfferResponseAcceptedEvent(
    bytes16 offerResponse,
    Offer.TradeValue offererTradedValue,
    Offer.TradeValue respondentTradedValue,
    uint timestamp
  );

  event OfferClosedEvent(
    bytes16 offer,
    uint timestamp
  );

  event OfferResponseCanceledEvent(
    bytes16 offerResponse,
    uint timestamp
  );

  event DisputeRaisedEvent(
    bytes16 offerResponse,
    address raisedBy,
    uint timestamp
  );

  event DisputeResolvedEvent(
    bytes16 offerResponse,
    Offer.TradeValue valueForOfferer,
    Offer.TradeValue valueForRespondent,
    address resolvedBy,
    uint timestamp
  );

  function initialize(
    DistrictDesigner _districtDesigner,
    address _offerGroupBaseContract,
    address _offerBaseContract
  ) public {}


  function createOfferGroup(
    bytes16 _district,
    bytes16 _offerGroup,
    Offer.TradeAsset[] memory _assetsToOffer,
    Offer.TradeAsset[] memory _assetsToRequest,
    Offer.OfferType _offerType,
    Offer.Fees memory _fees,
    Offer.PermissionIds memory _permissionIds,
    address[] memory _disputeResolvers,
    Offer.OfferSettings memory _offerSettings
  ) public
  {}


  function setBaseContracts(
    address _offerGroupBaseContract,
    address _offerBaseContract
  ) public
  {}


  function fireOfferGroupUpdatedEvent(
    Offer.TradeAsset[] memory _addedAssetsToOffer,
    Offer.TradeAsset[] memory _addedAssetsToRequest,
    Offer.Fees memory _fees,
    address[] memory disputeResolvers
  ) public
  {}


  function fireOfferCreatedEvent(
    bytes16 _offer,
    address _offerer,
    Offer.TradeValue memory _offeredValue,
    Offer.TradeValue[] memory _requestedValues,
    Offer.TradeAuction memory _requestedAuction
  ) public
  {}


  function fireOfferResponseCreatedEvent(
    bytes16 _offer,
    address _respondent,
    bytes16 _offerResponse,
    Offer.TradeValue memory _offererTradedValue,
    Offer.TradeValue memory _respondentTradedValue
  ) public
  {}


  function fireOfferResponseAcceptedEvent(
    bytes16 _offerResponse,
    Offer.TradeValue memory _offererTradedValue,
    Offer.TradeValue memory _respondentTradedValue
  ) public
  {}


  function fireOfferClosedEvent(
    bytes16 _offer
  ) public
  {}


  function fireOfferResponseCanceledEvent(
    bytes16 _offerResponse
  ) public
  {}


  function fireDisputeRaisedEvent(
    bytes16 _offerResponse,
    address _raisedBy
  ) public
  {}


  function fireDisputeResolvedEvent(
    bytes16 _offerResponse,
    Offer.TradeValue memory _valueForOfferer,
    Offer.TradeValue memory _valueForRespondent,
    address _resolvedBy
  ) public
  {}
}
