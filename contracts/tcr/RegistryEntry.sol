// SPDX-License-Identifier: MIT
pragma solidity >=0.4.22 <0.7.0;
pragma experimental ABIEncoderV2;

import "./Challengable.sol";
import "./TCR.sol";

/**
 * @dev Registry Entry contract for TCR
 */

contract RegistryEntry is Challengable {

  /**
   * @dev Contract initialization
   * It is manually called instead of native contructor,
   * because this contract is used through a proxy.
   * This function cannot be called twice.
   *
   * It considers msg.sender to be {TCR}
   * It calls {Challengable._initialize}
   *
   * Requirements:
   *
   * - `_creator` cannot be zero address
   * TODO: Needs implementation
   */
  function initialize(
    address _creator,
    uint _tokenAmount,
    bytes memory _tokenIpfsData
  ) external {
  }


  /**
   * @dev Called by TCR when token representation was minted.
   * TODO: Needs implementation
   */
  function markAsMinted(
  ) external {
  }
}
