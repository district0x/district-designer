// SPDX-License-Identifier: MIT
pragma solidity >=0.4.22 <0.7.0;
pragma experimental ABIEncoderV2;

import "./Challengable.sol";
import "./TCR.sol";

/**
 * @dev Parameter Change Entry contract for TCR
 * This contract is created when someone proposes a change to TCR parameters.
 * It goes through initial challenge period and after that it can be applied.
 */

contract ParamChangeEntry is Challengable {

  /**
   * @dev Contract initialization
   * It is manually called instead of native contructor,
   * because this contract is used through a proxy.
   * This function cannot be called twice.
   *
   * It considers msg.sender to be {TCR}
   * It calls {Challengable._initialize}
   * It stores current TCR value for given key as an original value.
   * Param change entry can be applied only if the original value is still current value
   * in TCR parameters.
   *
   * Requirements:
   *
   * - `_creator` cannot be zero address
   * TODO: Needs implementation
   */
  function initialize(
    address _creator,
    TCR.EntriesGroup _entriesGroup,
    string memory _parameterKey,
    uint _parameterValue
  ) external {
  }

  /**
   * @dev Called by TCR when the change was applied to TCR paramters.
   * TODO: Needs implementation
   */
  function markAsApplied(
  ) external {
  }

}
