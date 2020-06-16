// SPDX-License-Identifier: MIT
pragma solidity >=0.4.22 <0.7.0;
pragma experimental ABIEncoderV2;

import "./DistrictAdminProxy.sol";
import "./OwnerProxy.sol";

/**
 * @dev District Designer Proxy Factory contract
 *
 * Proxy contract pattern is widely used throughout the whole District Designer system
 * This factory allows convenient creation of proxies and tracking of their updates
 *
 * Creates 2 different types of proxy contracts
 * Emits event every time a target for a proxy was updated
 *
 */

contract ProxyFactory {

  mapping(address => bool) public isProxy;

  struct ProxyTarget {
    address contractAddress;
    bytes ipfsAbi;
  }

  event ProxyTargetUpdated(
    address proxy,
    ProxyTarget oldTarget,
    ProxyTarget newTarget,
    uint timestamp
  );

  modifier onlyProxy() {
    require(isProxy[msg.sender]);
    _;
  }


  /**
   * @dev Creates a new district admin proxy contract
   *
   * DistrictAdminProxy is a proxy contract, whose target can be updated
   * only by admin user role of a district
   *
   * Requirements:
   *
   * - `_target.contractAddress` cannot be zero address
   * - `_target.ipfsAbi` must be valid IPFS hash
   * - `_district` must be already initialized district
   *
   */
  function createDistrictAdminProxy(
    ProxyTarget memory _target,
    address _district
  ) external returns (address _proxy) {
    DistrictAdminProxy proxy = new DistrictAdminProxy(_target, _district);
    _proxy = address(proxy);
    isProxy[_proxy] = true;
    return _proxy;
  }

  /**
   * @dev Creates a new owner proxy
   *
   * OwnerProxy is a proxy contract, whose target can be changed only by owner of the contract
   *
   * Requirements:
   *
   * - `_target` cannot be zero address
   * - `_owner` cannot be zero address
   *
   */
  function createOwnerProxy(
    ProxyTarget memory _target,
    address _owner
  ) external returns (address _proxy) {
    OwnerProxy proxy = new OwnerProxy(_target, _owner);
    _proxy = address(proxy);
    isProxy[_proxy] = true;
    return _proxy;
  }

  /**
   * @dev Emits {ProxyTargetUpdated} event
   *
   * Can only be called by a proxy contract createdy by this proxy factory
   *
   */
  function fireProxyTargetUpdated(
    ProxyTarget memory _oldTarget,
    ProxyTarget memory _newTarget
  ) external onlyProxy {
    emit ProxyTargetUpdated(msg.sender, _oldTarget, _newTarget, now);
  }
}
