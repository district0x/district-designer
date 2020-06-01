pragma solidity >=0.4.22 <0.7.0;

import "./DistrictDesigner.sol";
import "./proxy/DistrictAdminProxy.sol";
import "./proxy/OwnerProxy.sol";

contract DDProxyFactory {

  DistrictDesigner public districtDesigner;
  mapping(address => bool) public isProxy;

  event ProxyTargetUpdated(
    address proxy,
    address oldTarget,
    address newTarget,
    bytes ipfsAbi,
    uint timestamp
  );

  modifier onlyProxy() {
    require(isProxy[msg.sender]);
    _;
  }

  constructor(
    DistrictDesigner _districtDesigner
  ) public {
    require(address(_districtDesigner) != address(0));
    districtDesigner = _districtDesigner;
  }


  function createDistrictAdminProxy(
    address _target,
    bytes16 _district
  ) public returns (address _proxy) {
    DistrictAdminProxy proxy = new DistrictAdminProxy(_target, districtDesigner, _district);
    _proxy = address(proxy);
    isProxy[_proxy] = true;
    return _proxy;
  }


  function createOwnerProxy(
    address _target,
    address _owner
  ) public returns (address _proxy) {
    OwnerProxy proxy = new OwnerProxy(_target, _owner);
    _proxy = address(proxy);
    isProxy[_proxy] = true;
    return _proxy;
  }


  function fireProxyTargetUpdatedEvent(
    address _oldTarget,
    address _newTarget,
    bytes memory _ipfsAbi
  ) public onlyProxy {
    ProxyTargetUpdated(msg.sender, _oldTarget, _newTarget, _ipfsAbi, now);
  }
}
