// SPDX-License-Identifier: MIT
pragma solidity >=0.4.22 <0.7.0;
pragma experimental ABIEncoderV2;

import "./ProxyFactory.sol";

/**
 * @dev Abstract contract meant for every contract that's used through a proxy, which
 * needs to have some logic executed right after target of the proxy is updated
 */

abstract contract UpdateTargetAndCallFallBack {

  modifier onlySelf() {
    require(msg.sender == address(this));
    _;
  }

  /**
   * @dev This function is called when proxy contract had its target updated
   */
  function targetUpdated(
    ProxyFactory.ProxyTarget memory _newTarget,
    bytes memory _data
  ) external virtual;
}