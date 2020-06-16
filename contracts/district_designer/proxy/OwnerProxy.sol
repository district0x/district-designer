// SPDX-License-Identifier: MIT
pragma solidity >=0.4.22 <0.7.0;
pragma experimental ABIEncoderV2;

import "./BaseProxy.sol";
import "@openzeppelin/contracts/access/Ownable.sol";

/**
 * @dev Extending {BaseProxy} with {Ownable} permissions
 */

contract OwnerProxy is BaseProxy, Ownable {

  constructor(
    ProxyFactory.ProxyTarget memory _target,
    address _owner
  ) public BaseProxy(_target) {
    require(_owner != address(0));
    transferOwnership(_owner);
  }

  /**
   * @dev Restricts target updating only to contract owner
   */
  function _canUpdateTarget(
    address _sender
  ) internal view override returns(bool) {
    return owner() == _sender;
  }


}
