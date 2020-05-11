pragma solidity >=0.4.22 <0.7.0;

import "./DDProxy.sol";

contract DDProxyFactory {

  DistrictDesigner public districtDesigner;

  event ProxyCreatedEvent(
    address proxy,
    bytes16 district,
    bytes32 updatePermissionId,
    address target
  );


  constructor(
    DistrictDesigner _districtDesigner
  ) public {
    require(address(_districtDesigner) != address(0));
    districtDesigner = _districtDesigner;
  }


  function createProxy(
    bytes16 _district,
    bytes32 _updatePermissionId,
    address _target
  ) public returns (address _proxy) {
    DDProxy proxy = new DDProxy(districtDesigner, _district, _updatePermissionId, _target);
    _proxy = address(proxy);
    ProxyCreatedEvent(_proxy, _district, _updatePermissionId, _target);
    return _proxy;
  }
}
