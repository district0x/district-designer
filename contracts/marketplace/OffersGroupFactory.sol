pragma solidity >=0.4.22 <0.7.0;
pragma experimental ABIEncoderV2;

import "../district_designer/DistrictDesigner.sol";
import "./OffersGroup.sol";

contract OffersGroupFactory {

  event OffersGroupCreatedEvent(
    bytes16 district,
    bytes16 offersGroup,
    address offersGroupAddress,
    OffersGroup.TradeAsset[] assetsToOffer,
    OffersGroup.TradeAsset[] assetsToRequest,
    OffersGroup.OfferType offerType,
    OffersGroup.Fees fees,
    OffersGroup.PermissionIds permissionIds,
    address[] disputeResolvers,
    OffersGroup.OfferSettings offerSettings,
    uint timestamp
  );

  event OffersGroupUpdatedEvent(
    bytes16 offersGroup,
    OffersGroup.TradeAsset[] addedAssetsToOffer,
    OffersGroup.TradeAsset[] addedAssetsToRequest,
    OffersGroup.Fees fees,
    address[] disputeResolvers,
    uint timestamp
  );

  event OfferCreatedEvent(
    bytes16 offer,
    address offerer,
    OffersGroup.TradeValue offeredValue,
    OffersGroup.TradeValue[] requestedValues,
    OffersGroup.TradeAuction requestedAuction,
    uint timestamp
  );

  event OfferResponseCreatedEvent(
    bytes16 offer,
    address respondent,
    bytes16 offerResponse,
    OffersGroup.TradeValue offererTradedValue,
    OffersGroup.TradeValue respondentTradedValue,
    uint timestamp
  );

  event OfferResponseAcceptedEvent(
    bytes16 offerResponse,
    OffersGroup.TradeValue offererTradedValue,
    OffersGroup.TradeValue respondentTradedValue,
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
    OffersGroup.TradeValue valueForOfferer,
    OffersGroup.TradeValue valueForRespondent,
    address resolvedBy,
    uint timestamp
  );

  constructor(DistrictDesigner _districtDesigner) public {}


  function createOffersGroup(
    bytes16 _district,
    bytes16 _OffersGroup,
    OffersGroup.TradeAsset[] memory _assetsToOffer,
    OffersGroup.TradeAsset[] memory _assetsToRequest,
    OffersGroup.OfferType _offerType,
    OffersGroup.Fees memory _fees,
    OffersGroup.PermissionIds memory _permissionIds,
    address[] memory _disputeResolvers,
    OffersGroup.OfferSettings memory _offerSettings
  ) public
  {}


  function fireOffersGroupUpdatedEvent(
    OffersGroup.TradeAsset[] memory _addedAssetsToOffer,
    OffersGroup.TradeAsset[] memory _addedAssetsToRequest,
    OffersGroup.Fees memory _fees,
    address[] memory disputeResolvers
  ) public
  {}


  function fireOfferCreatedEvent(
    bytes16 _offer,
    address _offerer,
    OffersGroup.TradeValue memory _offeredValue,
    OffersGroup.TradeValue[] memory _requestedValues,
    OffersGroup.TradeAuction memory _requestedAuction
  ) public
  {}


  function fireOfferResponseCreatedEvent(
    bytes16 _offer,
    address _respondent,
    bytes16 _offerResponse,
    OffersGroup.TradeValue memory _offererTradedValue,
    OffersGroup.TradeValue memory _respondentTradedValue
  ) public
  {}


  function fireOfferResponseAcceptedEvent(
    bytes16 _offerResponse,
    OffersGroup.TradeValue memory _offererTradedValue,
    OffersGroup.TradeValue memory _respondentTradedValue
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
    OffersGroup.TradeValue memory _valueForOfferer,
    OffersGroup.TradeValue memory _valueForRespondent,
    address _resolvedBy
  ) public
  {}
}
