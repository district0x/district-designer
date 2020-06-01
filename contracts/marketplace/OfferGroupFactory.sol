pragma solidity >=0.4.22 <0.7.0;
pragma experimental ABIEncoderV2;

import "../district_designer/DistrictDesigner.sol";
import "../district_designer/DDProxyFactory.sol";
import "./Offer.sol";

contract OfferGroupFactory is UpdateTargetAndCallFallBack {

  uint public constant version = 1;

  event OfferGroupCreated(
    bytes16 district,
    address offerGroup,
    address offerGroupBaseContract,
    bytes offerGroupIpfsAbi,
    uint offerGroupversion,
    Offer.TradeAsset[] assetsToOffer,
    Offer.TradeAsset[] assetsToRequest,
    Offer.OfferType offerType,
    Offer.Fees fees,
    Offer.PermissionUserRoles permissionUserRoles,
    bool allowMultipleTrades,
    bytes ipfsData,
    uint timestamp
  );

  event OfferGroupUpdated(
    address offerGroup,
    Offer.TradeAsset[] addedAssetsToOffer,
    Offer.TradeAsset[] addedAssetsToRequest,
    Offer.Fees fees,
    Offer.PermissionUserRoles permissionUserRoles,
    bytes ipfsData,
    uint timestamp
  );

  event OfferCreated(
    address offer,
    address offerBaseContract,
    bytes offerIpfsAbi,
    uint offerVersion,
    address offerer,
    Offer.TradeValue offeredValue,
    Offer.TradeValue[] requestedValues,
    Offer.TradeAuction requestedAuction,
    bytes ipfsData,
    uint timestamp
  );

  event OfferResponseCreated(
    address offer,
    address respondent,
    bytes16 offerResponse,
    Offer.TradeValue offererTradedValue,
    Offer.TradeValue respondentTradedValue,
    bytes ipfsData,
    uint timestamp
  );

  event OfferResponseAccepted(
    bytes16 offerResponse,
    Offer.TradeValue offererTradedValue,
    Offer.TradeValue respondentTradedValue,
    uint timestamp
  );

  event OfferClosed(
    address offer,
    uint timestamp
  );

  event OfferResponseCanceled(
    bytes16 offerResponse,
    uint timestamp
  );

  event DisputeRaised(
    bytes16 offerResponse,
    address raisedBy,
    uint timestamp
  );

  event DisputeResolved(
    bytes16 offerResponse,
    Offer.TradeValue valueForOfferer,
    Offer.TradeValue valueForRespondent,
    address resolvedBy,
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

  function initialize(
    DistrictDesigner _districtDesigner,
    address _offerGroupBaseContract,
    bytes memory _offerGroupIpfsAbi,
    address _offerBaseContract,
    bytes memory _offerIpfsAbi
  ) public {}


  function createOfferGroup(
    bytes16 _district,
    Offer.TradeAsset[] memory _assetsToOffer,
    Offer.TradeAsset[] memory _assetsToRequest,
    Offer.OfferType _offerType,
    Offer.Fees memory _fees,
    Offer.PermissionUserRoles memory _permissionUserRoles,
    bool _allowMultipleTrades,
    bytes memory _ipfsData
  ) public {
  }


  function updateBaseContracts(
    address _offerGroupBaseContract,
    bytes memory _offerGroupIpfsAbi,
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


  function fireOfferGroupUpdatedEvent(
    Offer.TradeAsset[] memory _addedAssetsToOffer,
    Offer.TradeAsset[] memory _addedAssetsToRequest,
    Offer.Fees memory _fees,
    Offer.PermissionUserRoles memory _permissionUserRoles,
    bytes memory _ipfsData
  ) public {
  }


  function fireOfferCreatedEvent(
    address _offer,
    address _offerBaseContract,
    bytes memory _offerIpfsAbi,
    uint _offerVersion,
    address _offerer,
    Offer.TradeValue memory _offeredValue,
    Offer.TradeValue[] memory _requestedValues,
    Offer.TradeAuction memory _requestedAuction,
    bytes memory _ipfsData
  ) public {
  }


  function fireOfferResponseCreatedEvent(
    address _offer,
    address _respondent,
    bytes16 _offerResponse,
    Offer.TradeValue memory _offererTradedValue,
    Offer.TradeValue memory _respondentTradedValue,
    bytes memory _ipfsData
  ) public {
  }


  function fireOfferResponseAcceptedEvent(
    bytes16 _offerResponse,
    Offer.TradeValue memory _offererTradedValue,
    Offer.TradeValue memory _respondentTradedValue
  ) public {
  }


  function fireOfferClosedEvent(
    address _offer
  ) public {
  }


  function fireOfferResponseCanceledEvent(
    bytes16 _offerResponse
  ) public {
  }


  function fireDisputeRaisedEvent(
    bytes16 _offerResponse,
    address _raisedBy
  ) public {
  }


  function fireDisputeResolvedEvent(
    bytes16 _offerResponse,
    Offer.TradeValue memory _valueForOfferer,
    Offer.TradeValue memory _valueForRespondent,
    address _resolvedBy
  ) public {
  }


  function fireOfferGroupBaseContractsUpdatedEvent(
    address offerBaseContract,
    bytes memory offerIpfsAbi
  ) public {
  }
}
