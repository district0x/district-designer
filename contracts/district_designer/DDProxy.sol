pragma solidity >=0.4.22 <0.7.0;

import "./DistrictDesigner.sol";

contract DDProxy {

  DistrictDesigner public DDProxyDistrictDesigner;
  bytes16 public DDProxyDistrict;
  bytes32 public DDProxyPermissionId;
  address public DDProxyTarget;

  modifier DDisAllowed() {
    require(DDProxyDistrictDesigner.isAllowed(DDProxyDistrict, DDProxyPermissionId, msg.sender));
    _;
  }


  constructor(
    DistrictDesigner _DDProxyDistrictDesigner,
    bytes16 _DDProxyDistrict,
    bytes32 _DDProxyPermissionId,
    address _DDProxyTarget
  ) public {
    require(address(DDProxyDistrictDesigner) == address(0));
    require(address(_DDProxyDistrictDesigner) != address(0));
    require(_DDProxyTarget != address(0));
    DDProxyDistrictDesigner = _DDProxyDistrictDesigner;
    DDProxyDistrict = _DDProxyDistrict;
    DDProxyPermissionId = _DDProxyPermissionId;
    DDProxyTarget = _DDProxyTarget;
  }

  /**
   * @dev Replaces targer forwarder contract is pointing to
   * Only authenticated user can replace target

   * @param _target New target to proxy into
  */
  function setTarget(
    address _target
  ) public DDisAllowed {
    DDProxyTarget = _target;
  }


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
delegatedFwd(DDProxyTarget, msg.data);
}
}
