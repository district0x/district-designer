pragma solidity >=0.4.22 <0.7.0;

import "./../DDProxyFactory.sol";

abstract contract BaseProxy {

  address public target;
  DDProxyFactory ddProxyFactory;

  modifier canChangeTarget() {
    require(_canChangeTarget(msg.sender));
    _;
  }

  constructor(
    address _target
  ) public {
    require(_target != address(0));
    ddProxyFactory = DDProxyFactory(msg.sender);
    target = _target;
  }

  /**
   * @dev Replaces targer forwarder contract is pointing to
   * Only authenticated user can replace target

   * @param _newTarget New target to proxy into
  */
  function setTarget(
    address _newTarget,
    bytes memory _ipfsData
  ) public canChangeTarget {
    require(_newTarget != address(0));
    ddProxyFactory.fireProxyTargetChangedEvent(target, _newTarget, _ipfsData);
    target = _newTarget;
  }


  function _canChangeTarget(
    address _sender
  ) public view virtual returns(bool);


  /**
  * @dev Performs a delegatecall and returns whatever the delegatecall returned (entire context execution will return!)
  * @param _dst Destination address to perform the delegatecall
  * @param _calldata Calldata for the delegatecall
  */
  function delegatedFwd(
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


  function isContract(
    address _target
  ) internal view returns (bool) {
    uint256 size;
    assembly {size := extcodesize(_target)}
    return size > 0;
  }


fallback(
) external payable {
delegatedFwd(target, msg.data);
}
}
