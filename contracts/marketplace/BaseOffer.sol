// SPDX-License-Identifier: MIT
pragma solidity >=0.4.22 <0.7.0;
pragma experimental ABIEncoderV2;

import "./MrktTypes.sol";
import  "./OfferGroupFactory.sol";

/**
 * @dev Base abstraction for offer contracts
 * Offer is a contract between single offerer and multiple respondents.
 * It serves as a trustless escrow between seller and buyer, with available
 * dispute resolution done by 3rd party.
 * Each extension provides different pricing mechanism.
 */

abstract contract BaseOffer {

  /**
   * @dev Modifier that allows only when `msg.sender` is the offerer
   * TODO: Needs implementation
   */
  modifier onlyOfferer() {
    _;
  }

  /**
   * @dev Contract initialization
   * It is manually called instead of native contructor,
   * because this contract is used through a proxy.
   * This function cannot be called twice.
   *
   * It considers msg.sender to be {OfferGroup}
   * It transfers `_offeredValue` into this contract
   *
   * Requirements:
   *
   * - `_offerer` cannot be zero address
   * - `_offerer` must be within `PermissionUserRoles.createOfferUserRoles` if it's not empty
   * - `_ipfsData` must be valid ipfs hash
   *
   * See spec :marketplace/offer-created for format of _ipfsData file
   * TODO: Needs implementation
   */
  function _initialize(
    address _offerer,
    address[] calldata _allowedRespondents,
    MrktTypes.TradeValue calldata _offeredValue,
    bytes calldata _ipfsData
  ) internal {
  }


  /**
   * @dev Creates an offer response
   * Associates respondent with a new offerResponseIndex
   * Same address can create multiple responses
   * Extending contracts should add indivitual logic
   *
   * Requirements:
   *
   * - `_respondent` cannot be zero address
   * - `_respondent` cannot be offerer
   * - `_respondent` must be within `allowedRespondents` if it's not empty
   * - `_respondent` must be within `PermissionUserRoles.offerResponseUserRoles` if it's not empty
   *
   * TODO: Needs implementation
   */
  function _createOfferResponse(
    address _respondent
  ) internal {
  }


  /**
   * @dev Determines if offer response is in the state that
   * allows raising a dispute.
   * Extending contracts must implement this function individually.
   */
  function _canRaiseDispute(
    uint _offerResponseIndex
  ) internal virtual returns (bool);


  /**
   * @dev When offered value is a deliverable,
   * respondent calls this function once the deliverable is actually received.
   * It cannot be called when there's dispute raised for given offer response.
   * It is meant to be called by extending contracts.
   *
   * Requirements:
   *
   * - `msg.sender` must be creator of the offer response `_offerResponseIndex` refers to
   * TODO: Needs implementation
   */
  function _markDeliverableReceived(
    uint _offerResponseIndex
  ) internal {
  }


  /**
   * @dev Raises a dispute
   * It calls {_canRaiseDispute} to check if a dispute can be raised
   *
   * Emits {DisputeRaised} event
   *
   * Requirements:
   *
   * - `msg.sender` must be creator of the offer response `_offerResponseIndex` refers to
   * - `_ipfsData` must be valid ipfs hash
   *
   * See spec :marketplace/dispute-raised for format of _ipfsData file
   * TODO: Needs implementation
   */
  function raiseDispute(
    uint _offerResponseIndex,
    bytes calldata _ipfsData
  ) external {
    require(_canRaiseDispute(_offerResponseIndex));
  }


  /**
   * @dev Resolves a dispute
   * It transfers `_valueForOfferer` to the offerer and
   * and `_valueForRespondent` to the respondent
   *
   * Emits {DisputeResolved} event
   *
   * Requirements:
   *
   * - `msg.sender` must be within PermissionUserRoles.resolveDisputeUserRoles
   * - `_offerResponseIndex` must refer to a response with raised dispute
   *
   * See spec :marketplace/dispute-resolved for format of _ipfsData file
   * TODO: Needs implementation
   */
  function resolveDispute(
    uint _offerResponseIndex,
    MrktTypes.TradeValue calldata _valueForOfferer,
    MrktTypes.TradeValue calldata _valueForRespondent,
    bytes calldata _ipfsData
  ) external {
  }


  /**
   * @dev Deposits supply into offered value
   * It adds supply into offered value that is either ETH, ERC20 or ERC1155 with single id
   * Supply can be deposited only by offerer
   * Meant to be called by extending contracts
   *
   * Emits {OfferAvailableSupplyUpdated} event
   *
   * Requirements:
   *
   * - `_sender` must be the offerer
   * - `_value` must be larger than zero
   * TODO: Needs implementation
   */
  function _depositSupply(
    address _sender,
    uint _value
  ) internal {
  }

  /**
   * @dev Same as {depositSupply} above, but for the case when offered value
   * is ERC1155 with multiple ids
   *
   * Emits {OfferAvailableSupplyUpdated} event
   *
   * Requirements:
   *
   * - `_sender` must be the offerer
   * - `_values` must have the same length as offered value's ERC1155 ids
   * TODO: Needs implementation
   */
  function _depositSupply(
    address _sender,
    uint[] calldata _values
  ) internal {
  }


  /**
   * @dev Allows offerer to fully withdraw his supply.
   * Can be called only by the offerer and makes transfer only to the offerer.
   * Supply cannot be withdrawn if one or more disputes is raised, but not resolved.
   * It reverts if offered value is a deliverable.
   * It is meant to be called by extending contracts.
   *
   * Emits {OfferAvailableSupplyUpdated} event
   * TODO: Needs implementation
   */
  function _withdrawSupply(
  ) internal onlyOfferer {
  }


}
