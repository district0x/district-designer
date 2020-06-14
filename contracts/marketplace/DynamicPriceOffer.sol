// SPDX-License-Identifier: MIT
pragma solidity >=0.4.22 <0.7.0;
pragma experimental ABIEncoderV2;

import "./BaseOffer.sol";
import "./MrktTypes.sol";
import "../tokens/ApproveAndCallFallback.sol";
import "../tokens/openzeppelin/ERC1155/ERC1155Receiver.sol"; // Replace with npm dependency once published

/**
 * @dev Offer contract where offerer defines dynamic price of requested asset.
 * Dynamic price is the price that linearly goes from `startPrice` to the `endPrice` over `duration` seconds.
 */

contract DynamicPriceOffer is BaseOffer, ApproveAndCallFallBack, ERC1155Receiver {

  uint public constant version = 1;
  MrktTypes.OfferType public constant offerType = MrktTypes.OfferType.DYNAMIC_PRICE;

  /**
   * @dev Contract initialization
   * Dynamic price starts at the moment of initialization.
   *
   * Emits {OfferCreated} event
   *
   * Requirements:
   *
   * - `Request.tokenId` must be defined if `tokenType` is ERC1155
   *
   * See spec :marketplace/offer-created for format of _ipfsData file
   * TODO: Needs implementation
   */
  function initialize(
    address _offerer,
    address[] calldata _allowedRespondents,
    MrktTypes.TradeValue calldata _offeredValue,
    MrktTypes.DynamicPriceOfferRequest calldata _request,
    bytes calldata _ipfsData
  ) external {
    super._initialize(_offerer, _allowedRespondents, _offeredValue, _ipfsData);
  }


  /**
   * @dev Updates offerer's request
   * It cannot be called after an offer response has been created.
   * It will restart dynamic price duration.
   *
   * Emits {OfferRequestUpdated} event
   *
   * See spec :marketplace/offer-request-updated for format of _ipfsData file
   * TODO: Needs implementation
   */
  function updateRequest(
    MrktTypes.DynamicPriceOfferRequest calldata _request,
    bytes calldata _ipfsData
  ) external {
  }


  /**
   * @dev If offered value is non-deliverable, it'll transfer respective values to both
   * offerer and respondent and the trade is completed.
   * If offered value is deliverable, the contract will transfer value from respondent
   * into this contract and hold it until respondent calls {markDeliverableReceived}
   * It calculates the price at `now` point of time.
   * This function can't be called more than once successfully.
   *
   * Emits {OfferResponseCreated} event
   *
   * See spec :marketplace/offer-response-created for format of _ipfsData file
   * TODO: Needs implementation
   */
  function createOfferResponse(
    address _respondent,
    bytes calldata _ipfsData
  ) external {
    super._createOfferResponse(_respondent);
  }


  /**
   * @dev Marks deliverable as received
   * This function is called by respondent after he receives deliverable from the offerer
   * Prior to this, respondent already transferred price into this contract when
   * creating the response.
   * It'll transfer value to the offerer and complete the trade.
   * Can be called only when offered value is a deliverable
   * It calls BaseOffer._markDeliverableReceived with `offerResponseIndex` of the only offer response created
   *
   * Emits {OfferDeliverableReceived} event with `offerResponseIndex` of the only offer response created
   *
   * Requirements:
   *
   * - `msg.sender` must be creator of the only offer response
   * TODO: Needs implementation
   */
  function markDeliverableReceived(
  ) external {
  }


  /**
   * @dev Dipsute can be raised either by offerer or respondent, in case when
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
  }


  /**
   * @dev This function is called automatically when this contract receives ERC20 token
   * If `_from` is the offerer, it reverts (DynamicPriceOffer cannot be resupplied)
   * If `_from` is not the offerer, it decodes `_data` and calls {createOfferResponse}
   * TODO: Needs implementation
   */
  function receiveApproval(
    address from,
    uint256 _amount,
    address _token,
    bytes memory _data
  ) external override {
  }


  /**
   * @dev This function is called automatically when this contract receives ERC1155 token
   * If `_from` is the offerer, it reverts (DynamicPriceOffer cannot be resupplied)
   * If `_from` is not the offerer, it decodes `_data` and calls {createOfferResponse}
   * TODO: Needs implementation
   */
  function onERC1155Received(
    address operator,
    address from,
    uint256 id,
    uint256 value,
    bytes calldata data
  ) external override returns (bytes4) {
    return bytes4(keccak256("onERC1155Received(address,address,uint256,uint256,bytes)"));
  }


  /**
   * @dev This function is called automatically when this contract receives multiple ERC1155 tokens
   * It always reverts because DynamicPriceOffer doesn't support multiple tokens nor
   * it can't be resupplied
   */
  function onERC1155BatchReceived(
    address operator,
    address from,
    uint256[] calldata ids,
    uint256[] calldata values,
    bytes calldata data
  ) external override returns (bytes4) {
    revert("DynamicPriceOffer doesn't support trading multiple tokens");
  }

}
