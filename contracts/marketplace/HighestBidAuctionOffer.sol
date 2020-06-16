// SPDX-License-Identifier: MIT
pragma solidity >=0.4.22 <0.7.0;
pragma experimental ABIEncoderV2;

import "./BaseOffer.sol";
import "./MrktTypes.sol";
import "../tokens/ApproveAndCallFallback.sol";
import "../tokens/openzeppelin/ERC1155/ERC1155Receiver.sol"; // Replace with npm dependency once published

/**
 * @dev Offer contract where offerer defines auction and respondents compete
 * in bidding the highest bid to obtain the offered value.
 * For token-for-token, trade is complete after auction finishes and somebody makes finalisation.
 * For deliverable-for-token, trade is complete when respondent marks deliverable as received.
 */

contract HighestBidAuctionOffer is BaseOffer, ApproveAndCallFallBack, ERC1155Receiver {

  uint public constant version = 1;
  MrktTypes.OfferType public constant offerType = MrktTypes.OfferType.HIGEST_BID_AUCTION;

  /**
   * @dev Contract initialization
   * Auction countdown starts at the moment of initialization
   *
   * Emits {OfferCreated} event
   *
   * Requirements:
   *
   * - `_request.tokenId` must be defined if `_request.tokenType` is ERC1155
   *
   * See spec :marketplace/offer-created for format of _ipfsData file
   * TODO: Needs implementation
   */
  function initialize(
    address _offerer,
    address[] memory _allowedRespondents,
    MrktTypes.TradeValue memory _offeredValue,
    MrktTypes.HighestBidAuctionOfferRequest memory _request,
    bytes memory _ipfsData
  ) external {
    super._initialize(_offerer, _allowedRespondents, _offeredValue, _ipfsData);
  }


  /**
   * @dev Updates offerer's request
   * Request can be updated only when no bids have been made yet.
   * It will restart the auction countdown.
   *
   * Emits {OfferRequestUpdated} event
   *
   * See spec :marketplace/offer-request-updated for format of _ipfsData file
   * TODO: Needs implementation
   */
  function updateRequest(
    MrktTypes.HighestBidAuctionOfferRequest memory _request,
    bytes memory _ipfsData
  ) external {
  }


  /**
   * @dev Makes a bid to the auction.
   * Bid can be made only before auction countdown is finished, otherwise it reverts.
   * Checks if `_transferredValue` is of requested token
   * Checks if `_transferredValue` is higher or equal to current price (or minPrice) + minBidStep
   *
   * If bid arrives `extensionTriggerDuration` seconds before the end of auction,
   * the auction will be extended by `extensionDuration` seconds.
   *
   * Everytime a new bid arrives, it tries to safely transfer previous bid back to the original bidder.
   * If transferring the bid fails, this function still continues and leaves the bid for manual reclaiming.
   *
   * `ipfsData` can be empty
   *
   * Emits {OfferResponseCreated} event
   * Emits {OfferResponseCanceled} for the previous bidder if the transfer was successfull
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
   * @dev After the auction finishes, this function can be called by anyone.
   * If called before the end of the auction, it reverts.
   *
   * If offered value is not deliverable, it'll transfer respective values to both
   * offerer and the winning respondent and the trade is completed.
   *
   * If offered value is deliverable, this function reverts, because in order to complete such trade
   * the respondent needs to call {markDeliverableReceived}
   *
   * Emits {OfferResponseAccepted} event with `offerResponseIndex` of the winning bid
   * TODO: Needs implementation
   */
  function acceptOfferResponse(
  ) external {
  }


  /**
   * @dev Transfers bid back to the bidder
   * Cannot be called for currently winning bid.
   *
   * If the bidder was overbid and automatic transfer didn't work, he calls this function in order to
   * reclaim his bid.
   *
   * Emits {OfferResponseCanceled} event
   * TODO: Needs implementation
   */
  function cancelOfferResponse(
    uint _offerResponseIndex
  ) external {
  }


  /**
   * @dev Marks deliverable as received
   * This function is called by respondent after he receives a deliverable from the offerer.
   * Respondent must be the winner of the auction.
   * It'll transfer value to the offerer and the trade is completed.
   * Can be called only when offered value is a deliverable.
   * Can be called only after auction finished.
   * It calls BaseOffer._markDeliverableReceived with `offerResponseIndex` of the winning bid
   *
   * Emits {OfferDeliverableReceived} event with `offerResponseIndex` of the winning bid
   *
   * Requirements:
   *
   * - `msg.sender` must be creator of the offer response that won the auction
   * TODO: Needs implementation
   */
  function markDeliverableReceived(
  ) external {
  }


  /**
   * @dev Dipsute can be raised either by offerer or respondent, in case when
   * the auction is finished and the winning respondent hasn't yet called {markDeliverableReceived}
   *
   * It calls {BaseOffer._raiseDispute} with `_disputedValue` being price at which the respondent
   * paid for the offered value.
   * TODO: Needs implementation
   */
  function raiseDispute(
    uint _offerResponseIndex,
    bytes calldata _ipfsData
  ) external {
  }


  /**
   * @dev Offerer can withdraw supply only if no bids have been made yet.
   *
   * If he withdraws before the auction is finished, the auction is immidiately finished.
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
   * If `_from` is the offerer, it reverts (HighestBidAuctionOffer cannot be resupplied)
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
   * If `_from` is the offerer, it reverts (HighestBidAuctionOffer cannot be resupplied)
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
   * It always reverts because HighestBidAuctionOffer doesn't support multiple tokens nor
   * it can't be resupplied
   */
  function onERC1155BatchReceived(
    address operator,
    address from,
    uint256[] calldata ids,
    uint256[] calldata values,
    bytes calldata data
  ) external override returns (bytes4) {
    revert();
  }


  /**
  * @dev This function is called automatically when this contract receives ETH
  * If `msg.sender` is the offerer, it reverts (HighestBidAuctionOffer cannot be resupplied)
  * If `msg.sender` is not the offerer, it decodes `msg.data` and calls {_createOfferResponse}
  * TODO: Needs implementation
  */
  receive(
  ) external payable {
  }

}
