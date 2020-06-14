// SPDX-License-Identifier: MIT
pragma solidity >=0.4.22 <0.7.0;
pragma experimental ABIEncoderV2;

import "./BaseOffer.sol";
import "./MrktTypes.sol";
import "../tokens/ApproveAndCallFallback.sol";
import "@openzeppelin/contracts/token/ERC721/IERC721Receiver.sol";
import "../tokens/openzeppelin/ERC1155/ERC1155Receiver.sol"; // Replace with npm dependency once published

/**
 * @dev Offer contract where offerer defines list of tokens he's willing to accept for his offered value.
 * Respondents make arbitrary bids within allowed list of tokens.
 * Offerer picks the bid he prefers and completes the trade.
 */

contract MultiTokenAuctionOffer is BaseOffer, ApproveAndCallFallBack, IERC721Receiver, ERC1155Receiver {

  uint public constant version = 1;
  MrktTypes.OfferType public constant offerType = MrktTypes.OfferType.MULTI_TOKEN_AUCTION;

  /**
   * @dev Contract initialization
   * Auction countdown starts at the moment of initialization
   *
   * Emits {OfferCreated} event
   *
   * Requirements:
   *
   * - `Request.acceptedTokens` cannot be empty
   *
   * See spec :marketplace/offer-created for format of _ipfsData file
   * TODO: Needs implementation
   */
  function initialize(
    address _offerer,
    address[] calldata _allowedRespondents,
    MrktTypes.TradeValue calldata _offeredValue,
    MrktTypes.MultiTokenAuctionOfferRequest calldata _request,
    bytes calldata _ipfsData
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
    MrktTypes.MultiTokenAuctionOfferRequest calldata _request,
    bytes calldata _ipfsData
  ) external {
  }


  /**
   * @dev It makes a bid to the auction.
   * Bid can be made only before auction countdown is finished, otherwise revert.
   * If bid arrives `extensionTriggerDuration` seconds before the end of auction,
   * the auction will be extended by `extensionDuration` seconds.
   *
   * Emits {OfferResponseCreated} event
   *
   * Requirements:
   *
   * - `_response` must be within accepted tokens
   *
   * See spec :marketplace/offer-response-created for format of _ipfsData file
   * TODO: Needs implementation
   */
  function createOfferResponse(
    address _respondent,
    MrktTypes.TradeValue calldata _response,
    bytes calldata _ipfsData
  ) external {
    super._createOfferResponse(_respondent);
  }


  /**
   * @dev Offerer calls this function to pick the winning bid.
   * If called before the end of the auction, it reverts.
   * If offered value is not deliverable, it'll transfer respective values to both
   * offerer and respondent and the trade is completed.
   * If offered value is deliverable, this function assigns `_offerResponseIndex` as accepted,
   * but it doesn't make any transfer yet. Respondent will have to call {markDeliverableReceived}.
   *
   * Offerer can accept multiple responses, that means for the first picked winner it is transferring
   * offered value that's already in the contract. For all subsequent acceptances, it transfers the
   * offered value from offerer's address.
   *
   * Emits {OfferResponseAccepted} event
   * TODO: Needs implementation
   */
  function acceptOfferResponse(
    uint _offerResponseIndex
  ) public {
  }


  /**
   * @dev Transfers bid back to the bidder.
   * Can be called before or after the auction ends.
   * Cannot be called when offered value is deliverable and this bid was already assigned as
   * accepted, just waiting until respondent calls {markDeliverableReceived}
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
   * Offerer must've accepted this response first.
   * It'll transfer value to the offerer and the trade is completed.
   * Can be called only when offered value is a deliverable.
   * Can be called only after auction finished.
   * It calls BaseOffer._markDeliverableReceived
   *
   * Emits {OfferDeliverableReceived} event
   *
   * Requirements:
   *
   * - `msg.sender` must be creator of the offer response
   * TODO: Needs implementation
   */
  function markDeliverableReceived(
    uint _offerResponseIndex
  ) external {
    super._markDeliverableReceived(_offerResponseIndex);
  }


  /**
   * @dev Dipsute can be raised either by offerer or respondent, in case when
   * offered value is deliverable, offerer already accepted this response and respondent
   * hasn't yet called {markDeliverableReceived}
   *
   * Requirements:
   *
   * - `_offerResponseIndex` must refer to an accepted offer response
   * TODO: Needs implementation
   */
  function _canRaiseDispute(
    uint _offerResponseIndex
  ) internal override returns(bool) {
    return true;
  }


  /**
   * @dev Offerer can withdraw supply only if no bids have been made yet.
   * If he withdraws before the auction is finished, the auction is immidiately finished.
   * TODO: Needs implementation
   */
  function withdrawSupply(
  ) external onlyOfferer {
    super._withdrawSupply();
  }


  /**
   * @dev This function is called automatically when this contract receives ERC20 token
   * If `_from` is the offerer, it decodes `_data` and calls {acceptOfferResponse}
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
   * @dev This function is called automatically when this contract receives ERC721 token
   * If `_from` is the offerer, it decodes `_data` and calls {acceptOfferResponse}
   * If `_from` is not the offerer, it decodes `_data` and calls {createOfferResponse}
   * TODO: Needs implementation
   */
  function onERC721Received(
    address operator,
    address from,
    uint256 tokenId,
    bytes memory data
  ) public override returns (bytes4) {
    return bytes4(keccak256("onERC721Received(address,address,uint256,bytes)"));
  }


  /**
   * @dev This function is called automatically when this contract receives ERC1155 token
   * If `_from` is the offerer, it decodes `_data` and calls {acceptOfferResponse}
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
   * If `_from` is the offerer, it decodes `_data` and calls {acceptOfferResponse}
   * If `_from` is not the offerer, it decodes `_data` and calls {createOfferResponse}
   * TODO: Needs implementation
   */
  function onERC1155BatchReceived(
    address operator,
    address from,
    uint256[] calldata ids,
    uint256[] calldata values,
    bytes calldata data
  ) external override returns(bytes4) {
    return bytes4(keccak256("onERC1155BatchReceived(address,address,uint256[],uint256[],bytes)"));
  }
}
