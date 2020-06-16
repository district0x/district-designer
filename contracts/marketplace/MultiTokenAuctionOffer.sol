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
 * Offerer picks the bid he prefers and the trade is completed.
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
   * - `_request.acceptedTokens` cannot be empty
   *
   * See spec :marketplace/offer-created for format of _ipfsData file
   * TODO: Needs implementation
   */
  function initialize(
    address _offerer,
    address[] memory _allowedRespondents,
    MrktTypes.TradeValue memory _offeredValue,
    MrktTypes.MultiTokenAuctionOfferRequest memory _request,
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
    MrktTypes.MultiTokenAuctionOfferRequest memory _request,
    bytes memory _ipfsData
  ) external {
  }


  /**
   * @dev It makes a bid to the auction.
   * It checks if `_transferredValue` is within MultiTokenAuctionOfferRequest.acceptedTokens
   * Bid can be made only before auction countdown is finished, otherwise revert.
   *
   * If bid arrives `extensionTriggerDuration` seconds before the end of auction,
   * the auction will be extended by `extensionDuration` seconds.
   *
   * `ipfsData` can be empty
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
   * @dev Offerer calls this function to accept a response.
   * It checks if this contract still has enough funds to accept the response.
   * Value to accept any reponse is value of the offered value.
   *
   * If called before the end of the auction, it reverts.
   *
   * If offered value is not deliverable, it'll transfer respective values to both
   * offerer and respondent and the trade is completed.
   *
   * If offered value is deliverable, this function sets `_offerResponseIndex` as accepted,
   * but it doesn't make any transfer yet. Respondent will have to call {markDeliverableReceived}.
   *
   * Any number of responses can be accepted.
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
   *
   * Cannot be called when offered value is deliverable and this response was already accepted and
   * it's just waiting until respondent calls {markDeliverableReceived}
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
   *
   * It'll transfer value to the offerer and the trade is completed.
   *
   * Can be called only when offered value is a deliverable.
   * Can be called only after auction finished.
   *
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
   * offered value is deliverable, offerer already accepted the response, but the respondent
   * hasn't yet called {markDeliverableReceived}
   *
   * It calls {BaseOffer._raiseDispute} with `_disputedValue` being the value, that respondent
   * transferred into the contract, when creating the response.
   *
   * TODO: Needs implementation
   */
  function raiseDispute(
    uint _offerResponseIndex,
    bytes memory _ipfsData
  ) external {
  }


  /**
   * @dev Offerer can withdraw only if no offer responses haven't been accepted yet.
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
   * If `_from` is the offerer, it decodes `_data` and calls {acceptOfferResponse}
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
   * @dev This function is called automatically when this contract receives ERC721 token
   * If `_from` is the offerer, it decodes `_data` and calls {acceptOfferResponse}
   * If `_from` is not the offerer, it decodes `_data` and calls {_createOfferResponse}
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
   * If `_from` is the offerer, it decodes `_data` and calls {acceptOfferResponse}
   * If `_from` is not the offerer, it decodes `_data` and calls {_createOfferResponse}
   * TODO: Needs implementation
   */
  function onERC1155BatchReceived(
    address operator,
    address from,
    uint256[] calldata ids,
    uint256[] calldata values,
    bytes calldata data
  ) external override returns (bytes4) {
    return bytes4(keccak256("onERC1155BatchReceived(address,address,uint256[],uint256[],bytes)"));
  }


  /**
  * @dev This function is called automatically when this contract receives ETH
  * If `msg.sender` is the offerer, it decodes `msg.data` and calls {acceptOfferResponse}
  * If `msg.sender` is not the offerer, it decodes `msg.data` and calls {_createOfferResponse}
  * TODO: Needs implementation
  */
  receive(
  ) external payable {
  }
}
