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
   * It checks if this contract is the owner of all `_offeredValues`. Offered values are transferred
   * into this contract before {initialize} is called.
   *
   * If there's a deliverable in `_offeredValues`, it cannot contain any other items
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
    address[] memory _allowedRespondents,
    MrktTypes.TokenValue[] memory _offeredValues,
    bytes memory _ipfsData
  ) internal {
  }


  /**
   * @dev Creates an offer response
   * Associates respondent with a new offerResponseIndex
   * Same address can create multiple responses.
   * Extending contracts should add individual logic
   *
   * Requirements:
   *
   * - `_respondent` cannot be zero address
   * - `_respondent` cannot be the offerer
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
   * Dispute can be raised only by the offerer or by the respondent
   * Dispute cannot be raised twice for the same offer response.
   *
   * Emits {DisputeRaised} event
   *
   * Requirements:
   *
   * - `_ipfsData` must be valid ipfs hash
   *
   * See spec :marketplace/dispute-raised for format of _ipfsData file
   * TODO: Needs implementation
   */
  function _raiseDispute(
    uint _offerResponseIndex,
    MrktTypes.TokenValue[] memory _disputedValues,
    bytes memory _ipfsData
  ) internal {
  }


  /**
   * @dev Resolves a dispute
   * Transfers `_valuesForOfferer` to the offerer.
   * Transfers `_valuesForRespondent` to the respondent.
   *
   * Adding up `_valuesForOfferer` and `_valuesForRespondent` must be exactly `_disputedValues`,
   * which was specified when the dispute was raised.
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
    MrktTypes.TokenValue[] memory _valuesForOfferer,
    MrktTypes.TokenValue[] memory _valuesForRespondent,
    bytes memory _ipfsData
  ) external {
  }


  /**
   * @dev Allows the offerer to withdraw his supply.
   * Can be called only by the offerer and makes transfer only to the offerer.
   *
   * Supply cannot be withdrawn if some dispute is not resolved yet.
   * It reverts if the offered value is a deliverable.
   *
   * It is meant to be called by extending contracts.
   *
   * Emits {AvailableValuesWithdrawn} event
   * TODO: Needs implementation
   */
  function _withdrawAvailableValues(
    MrktTypes.TokenValue[] memory _withdrawableValues
  ) internal onlyOfferer {
  }


}
