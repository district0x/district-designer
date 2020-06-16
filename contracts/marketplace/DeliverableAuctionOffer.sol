// SPDX-License-Identifier: MIT
pragma solidity >=0.4.22 <0.7.0;
pragma experimental ABIEncoderV2;

import "./BaseOffer.sol";
import "./MrktTypes.sol";
import "./extensions/Sponsorable.sol";
import "../tokens/ApproveAndCallFallback.sol";
import "../tokens/openzeppelin/ERC1155/ERC1155Receiver.sol"; // Replace with npm dependency once published

/**
 * @dev DeliverableAuctionOffer is the only offer type where requested value can be deliverable.
 * Offerer requests deliverable and respondents bid price for their provided deliverable.
 * Offerer picks the winner by his preference.
 * This offer is not limited by duration.
 */

contract DeliverableAuctionOffer is BaseOffer, Sponsorable, ApproveAndCallFallBack, ERC1155Receiver {

  uint public constant version = 1;
  MrktTypes.OfferType public constant offerType = MrktTypes.OfferType.DELIVERABLE_AUCTION;


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
    address[] memory _allowedRespondents,
    MrktTypes.TradeValue memory _offeredValue,
    bytes memory _ipfsData
  ) external {
    super._initialize(_offerer, _allowedRespondents, _offeredValue, _ipfsData);
  }


  /**
   * @dev Creates an offer response
   *
   * Respondent can specify different price than offered value.
   *
   * `_responseValue` must be of the same token as the offered value, but amounts
   * can be either lower or higher.
   * If `_responseValue` is ERC721/ERC1155 token, it needs to have same token id(s) as the offered value.
   *
   * Emits {OfferResponseCreated} event
   *
   * Requirements:
   *
   * - `_ipfsData` must be valid IPFS hash
   *
   * See spec :marketplace/offer-response-created for format of _ipfsData file
   * TODO: Needs implementation
   */
  function createOfferResponse(
    address _respondent,
    MrktTypes.TradeValue memory _responseValue,
    bytes memory _ipfsData
  ) external {
    super._createOfferResponse(_respondent);
  }


  /**
   * @dev Offerer accepts an offer response.
   * It checks if this contract has enough funds to accept the response and reserves them
   * for later transfer when the respondent calls {markDeliverableReceived}. Reserving means
   * preventing the funds withdrawal by the offerer or sponsors.
   *
   * Amount(s) required to accept the response was specified by respondent when calling {createOfferResponse}.
   * Offerer can accept as many responses as he prefers.
   * Accepting offer response doesn't transfer anything to the respondent, it'll do so only after
   * the offerer calls {markDeliverableReceived}.
   * If the offered value is deliverable, it doesn't transfer anything, just marks response as accepted.
   *
   * `_ipfsData` can be empty
   *
   * Emits {OfferResponseAccepted} event
   * TODO: Needs implementation
   */
  function acceptOfferResponse(
    uint _offerResponseIndex,
    bytes memory _ipfsData
  ) public {
  }


  /**
   * @dev Marks deliverable as received
   *
   * If the offered value is not deliverable, this function is called only by offerer.
   * Offerer must've called {acceptOfferResponse} before he can call {markDeliverableReceived}
   * for given `_offerResponseIndex`.
   * It'll transfer accepted value from the contract to the respondent and the trade is complete.
   *
   * If the offered value is deliverable, both the offerer and the respondent need to call this function
   * in order to make the trade completed. In such case, no token transfer is made, because it's
   * deliverable-for-deliverable trade.
   *
   * Emits {OfferDeliverableReceived} event with `offerResponseIndex` of the only offer response created
   *
   * Requirements:
   *
   * - `_offerResponseIndex` must refer to priorly accepted offer response
   * TODO: Needs implementation
   */
  function markDeliverableReceived(
    uint _offerResponseIndex
  ) external {
  }


  /**
   * Dipsute can be raised either by offerer or respondent, in case when
   * a response was accepted but {markDeliverableReceived} has not been called yet.
   *
   * If offered value is deliverable, dispute can never be raised, because it's
   * deliverable-for-deliverable trade with no value stored in the contract.
   *
   * It calls {BaseOffer._raiseDispute} with `_disputedValue` being `_responseValue` specified
   * by the respondent when creating the response.
   *
   * Requirements:
   *
   * - `_offerResponseIndex` must refer to priorly accepted offer response
   * TODO: Needs implementation
   */
  function raiseDispute(
    uint _offerResponseIndex,
    bytes memory _ipfsData
  ) external {
  }


  /**
   * @dev Amount of supply that can be widthdrawn is
   * the supply in the contract minus value reserved for accepted responses,
   * that haven't yet been {markDeliverableReceived}
   *
   * If offered value is deliverable, it always reverts.
   *
   * It calls {BaseOffer._withdrawSupply}
   * TODO: Needs implementation
   */
  function withdrawSupply(
  ) external onlyOfferer {
  }


  /**
   * @dev Adds a new sponsorship
   * It checks if `_transferredValue` is the same token and token id(s) as the offered value.
   * If offered value is deliverable, it reverts.
   * It calls {Sponsorable._addSponsorship}
   *
   * Emits {OfferAvailableSupplyUpdated} event
   * TODO: Needs implementation
   */
  function _addSponsorship(
    address _sponsor,
    MrktTypes.TradeValue memory _transferredValue
  ) internal {
  }


  /**
   * @dev Withdraws previously added sponsorship for `msg.sender` from the contract
   * It calls {Sponsorable._withdrawSponsorship} with `_availableSupply` argument being value stored
   * in the contract minus value reserved for accepted responses,
   * that haven't yet been {markDeliverableReceived}
   *
   * Emits {OfferAvailableSupplyUpdated} event
   * TODO: Needs implementation
   */
  function withdrawSponsorship(
  ) external {
  }


  /**
   * @dev This function is called automatically when this contract receives approval for ERC20 token.
   * It transfers approved tokens into this contract.
   * If `_from` is the offerer, it decodes `_data` and calls {acceptOfferResponse}
   * If `_from` is not the offerer, it calls {_addSponsorship}
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
   * If `_from` is the offerer, it decodes `_data` and calls {acceptOfferResponse}
   * If `_from` is not the offerer, it calls {_addSponsorship}
   * TODO: Needs implementation
   */
  function onERC1155Received(
    address operator,
    address from,
    uint256 id,
    uint256 value,
    bytes memory data
  ) external override returns (bytes4) {
    return bytes4(keccak256("onERC1155Received(address,address,uint256,uint256,bytes)"));
  }


  /**
   * @dev This function is called automatically when this contract receives multiple ERC1155 tokens
   * If `_from` is the offerer, it decodes `_data` and calls {acceptOfferResponse}
   * If `_from` is not the offerer, it calls {_addSponsorship}
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
   * If `msg.sender` is not the offerer, it calls {_addSponsorship}
   * TODO: Needs implementation
   */
  receive(
  ) external payable {
  }


}
