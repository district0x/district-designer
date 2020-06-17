// SPDX-License-Identifier: MIT
pragma solidity >=0.4.22 <0.7.0;
pragma experimental ABIEncoderV2;

import "./ProxyFactory.sol";
import "./UpdateTargetAndCallFallback.sol";
import  "../../_libs/validIPFSHash/validIPFSHash.sol";

/**
 * @dev Base contract for {DistrictAdminProxy} and {OwnerProxy}
 *
 * Implements full proxy functionality, extending contracts are only responsible for
 * defining permissions to update the `proxyTarget`
 *
 */

abstract contract BaseProxy {
  using validIPFSHash for bytes;

  ProxyFactory.ProxyTarget public proxyTarget;
  ProxyFactory public proxyFactory;

  modifier canUpdateTarget() {
    require(_canUpdateTarget(msg.sender));
    _;
  }


  /**
   * @dev Constructs a proxy
   *
   * Caller of this function is considered to be {ProxyFactory}
   *
   * @param _proxyTarget Proxy target the proxy will forward to
   *
   * Requirements:
   *
   * - `_proxyTarget.contractAddress` cannot be zero address
   * - `_proxyTarget.ipfsAbi` must be valid ipfs hash
   *
   */
  constructor(
    ProxyFactory.ProxyTarget memory _proxyTarget
  ) public {
    require(_proxyTarget.contractAddress != address(0));
    require(_proxyTarget.ipfsAbi.isValidIPFSHash());
    proxyFactory = ProxyFactory(msg.sender);
    proxyTarget = _proxyTarget;
  }


  /**
   * @dev Updates target the proxy forwards to
   *
   * Only authenticated address can update the target
   *
   * @param _newTarget New proxy target proxy will forward to
   *
   * Requirements:
   *
   * - `_newTarget.contractAddress` cannot be zero address
   * - `_newTarget.ipfsAbi` must be valid ipfs hash
   *
   */
  function updateTarget(
    ProxyFactory.ProxyTarget memory _newTarget
  ) public canUpdateTarget {
    require(_newTarget.contractAddress != address(0));
    require(_newTarget.ipfsAbi.isValidIPFSHash());
    proxyFactory.fireProxyTargetUpdated(proxyTarget, _newTarget);
    proxyTarget = _newTarget;
  }


  /**
   * @dev Same as {updateTarget} but additionally calls a contract function with passed `data`
   * A contract, which this proxy forwards to, must implement {targetUpdated} function
   *
   * @param _newTarget New target proxy will forward to
   *
   */
  function updateTargetAndCall(
    ProxyFactory.ProxyTarget memory _newTarget,
    bytes memory _data
  ) external canUpdateTarget {
    updateTarget(_newTarget);
    UpdateTargetAndCallFallBack _this = UpdateTargetAndCallFallBack(address(this));
    _this.targetUpdated(_newTarget, _data);
  }


  /**
   * @dev Checks whether sender can update proxy target
   * Implementation must be done by extending contracts
   */
  function _canUpdateTarget(
    address _sender
  ) internal view virtual returns(bool);


  /**
   * @dev Performs a delegatecall and returns whatever the delegatecall returned (entire context execution will return!)
   * @param _dst Destination address to perform the delegatecall
   * @param _calldata Calldata for the delegatecall
   */
  function _delegatedFwd(
    address _dst,
    bytes memory _calldata
  ) internal {
    require(isContract(_dst));
    assembly {
      let result := delegatecall(sub(gas(), 10000), _dst, add(_calldata, 0x20), mload(_calldata), 0, 0)
      let size := returndatasize()

      let ptr := mload(0x40)
      returndatacopy(ptr, 0, size)

    // revert instead of invalid() bc if the underlying call failed with invalid() it already wasted gas.
    // if the call returned error data, forward it
      switch result case 0 {revert(ptr, size)}
      default {return (ptr, size)}
    }
  }


  /**
   * @dev Returns true if given address is a smart-contract
   */
  function isContract(
    address _target
  ) internal view returns (bool) {
    uint256 size;
    assembly {size := extcodesize(_target)}
    return size > 0;
  }


  /**
   * @dev All calls landing here will be forwarded
   */
  receive(
  ) external payable {
    _delegatedFwd(proxyTarget.contractAddress, msg.data);
  }


  /**
   * @dev All calls landing here will be forwarded
   */
  fallback(
  ) external payable {
    _delegatedFwd(proxyTarget.contractAddress, msg.data);
  }
}
