// SPDX-License-Identifier: MIT
pragma solidity >=0.4.22 <0.7.0;
pragma experimental ABIEncoderV2;

import "./BaseOffer.sol";
import "./MrktTypes.sol";
import "../tokens/ApproveAndCallFallback.sol";
import "../tokens/openzeppelin/ERC1155/ERC1155Receiver.sol"; // Replace with npm dependency once published

/**
 * @dev Offer contract where offerer defines dynamic price for requested asset.
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
   * - `_request.tokenId` must be defined if `tokenType` is ERC1155
   *
   * See spec :marketplace/offer-created for format of _ipfsData file
   * TODO: Needs implementation
   */
  function initialize(
    address _offerer,
    address[] memory _allowedRespondents,
    MrktTypes.TradeValue memory _offeredValue,
    MrktTypes.DynamicPriceOfferRequest memory _request,
    bytes memory _ipfsData
  ) external {
    super._initialize(_offerer, _allowedRespondents, _offeredValue, _ipfsData);
  }


  /**
   * @dev Updates offerer's request
   * It cannot be called after any offer response has been created.
   * It will restart dynamic price duration.
   *
   * Emits {OfferRequestUpdated} event
   *
   * See spec :marketplace/offer-request-updated for format of _ipfsData file
   * TODO: Needs implementation
   */
  function updateRequest(
    MrktTypes.DynamicPriceOfferRequest memory _request,
    bytes memory _ipfsData
  ) external {
  }


  /**
   * @dev Buys the offered value at current price
   * It calculates the price at `now` point of time.
   * It checks if `_transferredValue` is enough to buy. Any surplus is transferred back to the respondent.
   * If offered value is non-deliverable, it'll transfer respective values to both
   * offerer and respondent and the trade is completed.
   * If offered value is deliverable, the contract will hold value until respondent calls {markDeliverableReceived}.
   * This function can't be called more than once successfully.
   * `_ipfsData` can be empty
   *
   * Emits {OfferResponseCreated} event
   *
   * See spec :marketplace/offer-response-created for format of _ipfsData file
   * TODO: Needs implementation
   */
  function _createOfferResponse(
    address _respondent,
    MrktTypes.TradeValue memory _transferredValue,
    bytes memory _ipfsData
  ) internal {
    super._createOfferResponse(_respondent);
  }


  /**
   * @dev Marks deliverable as received
   * This function is called by respondent after he receives deliverable from the offerer
   * Prior to this, respondent already transferred price into this contract when
   * creating the response.
   * It'll transfer value to the offerer and the trade is completed.
   * Can be called only when the offered value is a deliverable
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
   * @dev Dispute can be raised either by offerer or respondent, in case when
   * respondent hasn't yet called {markDeliverableReceived}
   *
   * It calls {BaseOffer._raiseDispute} with `_disputedValue` being price at which the respondent
   * paid for the offered value.
   *
   * TODO: Needs implementation
   */
  function raiseDispute(
    uint _offerResponseIndex,
    bytes memory _ipfsData
  ) external {
  }


  /**
   * @dev Supply can be withdrawn at any point
   *
   * If the offered value is deliverable, it always reverts.
   *
   * It calls {BaseOffer._withdrawSupply} with `_withdrawableValue` being the offered value
   * TODO: Needs implementation
   */
  function withdrawSupply(
  ) external onlyOfferer {
  }


  /**
   * @dev This function is called automatically when this contract receives approval for ERC20 token
   * It transfers approved tokens into this contract.
   * If `_from` is the offerer, it reverts (DynamicPriceOffer cannot be resupplied)
   * If `_from` is not the offerer, it decodes `_data` and calls {_createOfferResponse}
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
   * If `_from` is not the offerer, it decodes `_data` and calls {_createOfferResponse}
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


  /**
   * @dev This function is called automatically when this contract receives ETH
   * If `msg.sender` is the offerer, it reverts (DynamicPriceOffer cannot be resupplied)
   * If `msg.sender` is not the offerer, it decodes `msg.data` and calls {_createOfferResponse}
   * TODO: Needs implementation
   */
  receive(
  ) external payable {
  }

}
