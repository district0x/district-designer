// SPDX-License-Identifier: MIT
pragma solidity >=0.4.22 <0.7.0;
pragma experimental ABIEncoderV2;

import "./BaseOffer.sol";
import "./MrktTypes.sol";
import "../tokens/ApproveAndCallFallback.sol";
import "@openzeppelin/contracts/token/ERC721/IERC721Receiver.sol";
import "../tokens/openzeppelin/ERC1155/ERC1155Receiver.sol"; // Replace with npm dependency once published

/**
 * @dev Offer contract where offerer defines one or more fixed prices for his offered value.
 * For token-for-token, trade is complete when response is created.
 * For deliverable-for-token, trade is complete when respondent marks deliverable received.
 */

contract FixedPricesOffer is BaseOffer, ApproveAndCallFallBack, IERC721Receiver, ERC1155Receiver {

  uint public constant version = 1;
  MrktTypes.OfferType public constant offerType = MrktTypes.OfferType.FIXED_PRICES;


  /**
   * @dev Contract initialization
   *
   * Emits {OfferCreated} event
   *
   * Requirements:
   *
   * - `Request.prices` cannot be empty
   * - `Request.prices` cannot contain deliverable
   *
   * See spec :marketplace/offer-created for format of _ipfsData file
   * TODO: Needs implementation
   */
  function initialize(
    address _offerer,
    address[] calldata _allowedRespondents,
    MrktTypes.TradeValue calldata _offeredValue,
    MrktTypes.FixedPricesOfferRequest calldata _request,
    bytes calldata _ipfsData
  ) external {
    super._initialize(_offerer, _allowedRespondents, _offeredValue, _ipfsData);
  }


  /**
   * @dev Updates offerer's request
   * This shouldn't influence values that are held by contract until a respondent calls
   * {markDeliverableReceived}
   *
   * Emits {OfferRequestUpdated} event
   *
   * Requirements:
   *
   * - `Request.prices` cannot be empty
   * - `Request.prices` cannot contain deliverable
   *
   * See spec :marketplace/offer-request-updated for format of _ipfsData file
   * TODO: Needs implementation
   */
  function updateRequest(
    MrktTypes.FixedPricesOfferRequest calldata _request,
    bytes calldata _ipfsData
  ) external {
  }


  /**
   * @dev If offered value is non-deliverable, it'll transfer respective values to both
   * offerer and respondent and the trade is completed.
   * If offered value is deliverable, the contract will transfer value from respondent
   * into this contract and hold it until respondent calls {markDeliverableReceived}
   *
   * Emits {OfferResponseCreated} event
   *
   * Requirements:
   *
   * - `priceIndex` must be valid indes inside prices
   *
   * See spec :marketplace/offer-response-created for format of _ipfsData file
   * TODO: Needs implementation
   */
  function createOfferResponse(
    address _respondent,
    MrktTypes.FixedPricesOfferResponse calldata _response,
    bytes calldata _ipfsData
  ) public {
    super._createOfferResponse(_respondent);
  }


  /**
   * @dev Marks deliverable as received
   * This function is called by respondent after he receives deliverable from the offerer
   * Prior to this, respondent already transferred price into this contract when
   * creating the response.
   * It'll transfer value to the offerer and complete the trade.
   * Can be called only when offered value is a deliverable
   * It calls BaseOffer._markDeliverableReceived
   *
   * Emits {OfferDeliverableReceived} event
   *
   * Requirements:
   *
   * - `msg.sender` must be creator of the offer response with `_offerResponseIndex`
   * - `_offerResponseIndex` must refer to valid offer response
   * TODO: Needs implementation
   */
  function markDeliverableReceived(
    uint _offerResponseIndex
  ) external {
    super._markDeliverableReceived(_offerResponseIndex);
  }


  /**
   * @dev Returns true if a dispute can be raised
   * Dipsute can be raised either by offerer or respondent, in case when
   * respondent hasn't yet called {markDeliverableReceived}
   *
   * Requirements:
   *
   * - `_offerResponseIndex` must refer to valid offer response
   * TODO: Needs implementation
   */
  function _canRaiseDispute(
    uint _offerResponseIndex
  ) internal override returns(bool) {
    return true;
  }


  /**
   * @dev Supply can be withdrawn at any point
   */
  function withdrawSupply(
  ) external onlyOfferer {
    super._withdrawSupply();
  }


  /**
   * @dev This function is called automatically when this contract receives ERC20 token
   * If `_from` is the offerer, it calls {_depositSupply}
   * If `_from` is not the offerer, it decodes `_data` and calls {createOfferResponse}
   * TODO: Needs implementation
   */
  function receiveApproval(
    address _from,
    uint256 _amount,
    address _token,
    bytes memory _data
  ) external override {
  }


  /**
   * @dev This function is called automatically when this contract receives ERC721 token
   * If `_from` is the offerer, it reverts (ERC721 cannot be resupplied)
   * If `_from` is not the offerer, it decodes `_data` and calls {createOfferResponse}
   * TODO: Needs implementation
   */
  function onERC721Received(
    address _operator,
    address _from,
    uint256 _tokenId,
    bytes memory _data
  ) public override returns (bytes4) {
    return bytes4(keccak256("onERC721Received(address,address,uint256,bytes)"));
  }


  /**
   * @dev This function is called automatically when this contract receives ERC1155 token
   * If `_from` is the offerer, it calls {_depositSupply}
   * If `_from` is not the offerer, it decodes `_data` and calls {createOfferResponse}
   * TODO: Needs implementation
   */
  function onERC1155Received(
    address _operator,
    address _from,
    uint256 _id,
    uint256 _value,
    bytes calldata _data
  ) external override returns (bytes4) {
    return bytes4(keccak256("onERC1155Received(address,address,uint256,uint256,bytes)"));
  }


  /**
   * @dev This function is called automatically when this contract receives multiple ERC1155 tokens
   * If `_from` is the offerer, it calls {_depositSupply}
   * If `_from` is not the offerer, it decodes `_data` and calls {createOfferResponse}
   * TODO: Needs implementation
   */
  function onERC1155BatchReceived(
    address _operator,
    address _from,
    uint256[] calldata _ids,
    uint256[] calldata _values,
    bytes calldata _data
  ) external override returns (bytes4) {
    return bytes4(keccak256("onERC1155BatchReceived(address,address,uint256[],uint256[],bytes)"));
  }
}
