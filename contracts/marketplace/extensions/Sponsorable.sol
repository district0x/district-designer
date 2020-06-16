// SPDX-License-Identifier: MIT
pragma solidity >=0.4.22 <0.7.0;
pragma experimental ABIEncoderV2;

import "../MrktTypes.sol";
import "../OfferGroupFactory.sol";

/**
 * @dev Sponsorable offer extensions adds functionality for sponsoring an offer.
 * It keeps track of sponsor's addresses and sponsored amounts.
 * It allowes sponsors to withdraw their sponsorships back as long as available supply allows it
 */

abstract contract Sponsorable {

  /**
   * @dev Adds a new sponsorship
   * It associates `_sponsorship` with `_sponsor`.
   * If the sponsor added sponsorship before, it adds values up.
   * Only single token is allowed as sponsorship. If the sponsor tries to add different token
   * than he added before, it reverts.
   *
   * Emits {SponsorshipAdded} event
   * TODO: Needs implementation
   */
  function _addSponsorship(
    OfferGroupFactory _offerGroupFactory,
    address _sponsor,
    MrktTypes.TradeValue memory _sponsorship
  ) internal {
  }

  /**
   * @dev Transfers sponsorship back to the owner.
   * If `_availableSupply` is less than the sponsorship it transfers only `_availableSupply`.
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
    MrktTypes.TradeValue memory _availableSupply
  ) internal returns (MrktTypes.TradeValue memory _withdrawal){
    return _withdrawal;
  }


}
