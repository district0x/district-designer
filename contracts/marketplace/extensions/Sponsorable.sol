// SPDX-License-Identifier: MIT
pragma solidity >=0.4.22 <0.7.0;
pragma experimental ABIEncoderV2;

import "../MrktTypes.sol";
import "../OfferGroupFactory.sol";

/**
 * @dev Sponsorable offer extensions adds functionality for sponsoring an offer.
 * It keeps track of sponsor's addresses and sponsored amounts.
 * It allows sponsors to withdraw their sponsorships back as long as available supply allows it
 */

abstract contract Sponsorable {


  /**
   * @dev Adds a new sponsorship
   * It associates `_sponsoredValues` with `_sponsor`.
   * If the sponsor added sponsorship before, it adds values up.
   * If previously added, `_sponsoredValues` must be of the same length, token address and token ids as
   * initially added sponsorship.
   *
   * Emits {SponsorshipAdded} event
   * TODO: Needs implementation
   */
  function _addSponsorship(
    OfferGroupFactory _offerGroupFactory,
    address _sponsor,
    MrktTypes.TokenValue[] memory _sponsoredValues
  ) internal {
  }


  /**
   * @dev Transfers sponsorship back to the sponsor.
   * If `_availableValues` is less than the sponsorship it transfers only `_availableValues`.
   * It should never transfer to the sponsor more than he added.
   *
   * Emits {SponsorshipWithdrawn} event
   *
   * Requirements:
   * TODO: Needs implementation
   */
  function _withdrawSponsorship(
    OfferGroupFactory _offerGroupFactory,
    address _sponsor,
    MrktTypes.TokenValue[] memory _availableValues
  ) internal returns (MrktTypes.TokenValue[] memory _withdrawnValues){
    return _withdrawnValues;
  }
}
