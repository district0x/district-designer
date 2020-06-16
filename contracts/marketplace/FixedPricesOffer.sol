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
   * - `_request.prices` cannot be empty
   * - `_request.prices` cannot contain deliverable
   *
   * See spec :marketplace/offer-created for format of _ipfsData file
   * TODO: Needs implementation
   */
  function initialize(
    address _offerer,
    address[] memory _allowedRespondents,
    MrktTypes.TradeValue memory _offeredValue,
    MrktTypes.FixedPricesOfferRequest memory _request,
    bytes memory _ipfsData
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
   * - `_request.prices` cannot be empty
   * - `_request.prices` cannot contain deliverable
   *
   * See spec :marketplace/offer-request-updated for format of _ipfsData file
   * TODO: Needs implementation
   */
  function updateRequest(
    MrktTypes.FixedPricesOfferRequest memory _request,
    bytes memory _ipfsData
  ) external {
  }


  /**
   * @dev Buys the offered value at fixed price.
   * Checks if `_transferredValue` is one of fixed prices.
   *
   * If the offered value is non-deliverable, it'll transfer respective values to both
   * offerer and respondent and the trade is completed.
   *
   * If the offered value is a deliverable, the contract will hold the value until the offerer
   * calls {acceptOfferResponse} and subsequently the respondent calls {markDeliverableReceived}
   *
   * `ipfsData` can be empty if the offered value is not deliverable.
   *
   * Emits {OfferResponseCreated} event
   *
   * See spec :marketplace/offer-response-created for format of _ipfsData file
   * TODO: Needs implementation
   */
  function _createOfferResponse(
    address _respondent,
    MrktTypes.TradeValue memory _transferredValue,
    bytes calldata _ipfsData
  ) internal {
    super._createOfferResponse(_respondent);
  }


  /**
   * @dev Offerer calls this function to accept a response.
   * This function is supposed to be called only when the offered value is deliverable, otherwise it reverts.
   *
   * It sets given `_offerResponseIndex` as accepted,
   * but it doesn't make any transfer yet. Respondent will have to call {markDeliverableReceived}.
   *
   * Any number of responses can be accepted.
   *
   * Emits {OfferResponseAccepted} event
   * TODO: Needs implementation
   */
  function acceptOfferResponse(
    uint _offerResponseIndex
  ) external {
  }


  /**
   * @dev Marks deliverable as received
   * This function is called by respondent after he receives deliverable from the offerer.
   *
   * Prior to this, respondent already transferred price into this contract when
   * creating the response.
   *
   * It'll transfer value to the offerer and the trade is completed.
   * Can be called only when the response was accepted by the offerer.
   *
   * It calls BaseOffer._markDeliverableReceived
   *
   * Emits {OfferDeliverableReceived} event
   *
   * Requirements:
   *
   * - `msg.sender` must be creator of the offer response with `_offerResponseIndex`
   * - `_offerResponseIndex` must refer to an accepted offer response
   * TODO: Needs implementation
   */
  function markDeliverableReceived(
    uint _offerResponseIndex
  ) external {
    super._markDeliverableReceived(_offerResponseIndex);
  }


  /**
   * @dev Dipsute can be raised either by offerer or respondent, in case when
   * the response was already accepted, but the respondent hasn't yet called {markDeliverableReceived}
   *
   * It calls {BaseOffer._raiseDispute} with `_disputedValue` being price at which the respondent
   * paid for the offered value.
   *
   * TODO: Needs implementation
   */
  function raiseDispute(
    uint _offerResponseIndex,
    bytes calldata _ipfsData
  ) external {
  }



  /**
   * @dev This function is called automatically when this contract receives approval for ERC20 token
   * It transfers approved tokens into this contract.
   * If `_from` is the offerer, it reverts. (FixedPricesOffer cannot be resupplied)
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
   * If `_from` is the offerer, it reverts. (FixedPricesOffer cannot be resupplied)
   * If `_from` is not the offerer, it decodes `_data` and calls {_createOfferResponse}
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
   * If `_from` is the offerer, it reverts. (FixedPricesOffer cannot be resupplied)
   * If `_from` is not the offerer, it decodes `_data` and calls {_createOfferResponse}
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
   * If `_from` is the offerer, it reverts. (FixedPricesOffer cannot be resupplied)
   * If `_from` is not the offerer, it decodes `_data` and calls {_createOfferResponse}
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


  /**
  * @dev This function is called automatically when this contract receives ETH
  * If `msg.sender` is the offerer, it reverts. (FixedPricesOffer cannot be resupplied)
  * If `msg.sender` is not the offerer, it decodes `msg.data` and calls {_createOfferResponse}
  * TODO: Needs implementation
  */
  receive(
  ) external payable {
  }
}
