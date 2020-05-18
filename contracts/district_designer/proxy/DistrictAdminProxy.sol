pragma solidity >=0.4.22 <0.7.0;

import "./../DistrictDesigner.sol";
import "./../DDProxyFactory.sol";
import "./BaseProxy.sol";

contract DistrictAdminProxy is BaseProxy {

  DistrictDesigner public districtDesigner;
  bytes16 public district;

  constructor(
    bytes32 _contractName,
    address _target,
    DistrictDesigner _districtDesigner,
    bytes16 _district
  ) public BaseProxy(_contractName, _target) {
    require(address(_districtDesigner) != address(0));
    require(_district != bytes16(0));
    district = _district;
    target = _target;
  }


  function _canChangeTarget(
    address _sender
  ) public view override returns(bool) {
    return districtDesigner.adminUserRoleHasAddress(district, _sender);
  }

}
