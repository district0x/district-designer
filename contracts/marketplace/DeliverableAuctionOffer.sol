// SPDX-License-Identifier: MIT
pragma solidity >=0.4.22 <0.7.0;
pragma experimental ABIEncoderV2;

import "./BaseOffer.sol";
import "../tokens/ApproveAndCallFallback.sol";
import "@openzeppelin/contracts/token/ERC721/IERC721Receiver.sol";
import "../tokens/openzeppelin/ERC1155/ERC1155Receiver.sol"; // Replace with npm dependency once published

/**
 * @dev DeliverableAuctionOffer is the only offer type where requested value can be deliverable.
 * Offerer requests deliverable and respondents bid price for their provided deliverable.
 * Offerer picks the winner by his preference.
 * This offer is not limited by duration.
 */

contract DeliverableAuctionOffer is BaseOffer, ApproveAndCallFallBack, IERC721Receiver, ERC1155Receiver {

  uint public constant version = 1;
  BaseOffer.OfferType public constant offerType = BaseOffer.OfferType.DELIVERABLE_AUCTION;


  struct Response {
    uint value;
    uint[] values; // In case offered value is multiple ERC1155 ids
  }

  /**
   * @dev Contract initialization
   *
   * Emits {OfferCreated} event
   *
   * See spec :marketplace/offer-created for format of _ipfsData file
   * TODO: Needs implementation
   */
  function initialize(
    address _offerer,
    address[] calldata _allowedRespondents,
    BaseOffer.TradeValue calldata _offeredValue,
    bytes calldata _ipfsData
  ) external {
    super._initialize(_offerer, _allowedRespondents, _offeredValue, _ipfsData);
  }


  /**
   * @dev Creates an offer response
   * Respondent can optionally specify different value than offered value, he wants for his deliverable.
   * If `_response` is empty, then it consideres the respondent is interested in full offered value
   * `_response` value(s) can be lower as well as higher than offered value
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
    Response calldata _response,
    bytes calldata _ipfsData
  ) external {
    super._createOfferResponse(_respondent);
  }


  function acceptOfferResponse(
    uint _offerResponseIndex,
    bytes calldata _ipfsData
  ) external {
  }


  function markDeliverableReceived(
    uint _offerResponseIndex
  ) external override {
  }


  function _canRaiseDispute(
    uint _offerResponseIndex
  ) internal override returns(bool) {
    return true;
  }


  function withdrawSupply(
  ) external onlyOfferer {
    super._withdrawSupply();
  }


  function receiveApproval(
    address from,
    uint256 _amount,
    address _token,
    bytes memory _data
  ) external override {
  }


  function onERC721Received(
    address operator,
    address from,
    uint256 tokenId,
    bytes memory data
  ) public override returns (bytes4) {
    return bytes4(keccak256("onERC721Received(address,address,uint256,bytes)"));
  }


  function onERC1155Received(
    address operator,
    address from,
    uint256 id,
    uint256 value,
    bytes calldata data
  ) external override returns (bytes4) {
    return bytes4(keccak256("onERC1155Received(address,address,uint256,uint256,bytes)"));
  }


  function onERC1155BatchReceived(
    address operator,
    address from,
    uint256[] calldata ids,
    uint256[] calldata values,
    bytes calldata data
  ) external override returns (bytes4) {
    return bytes4(keccak256("onERC1155BatchReceived(address,address,uint256[],uint256[],bytes)"));
  }

}
