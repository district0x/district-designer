pragma solidity >=0.4.22 <0.7.0;

import "./DistrictDesigner.sol";
import "./proxy/DistrictAdminProxy.sol";
import "./proxy/OwnerProxy.sol";

contract DDProxyFactory {

  DistrictDesigner public districtDesigner;
  mapping(address => bool) public isProxy;

  event ProxyTargetChangedEvent(
    address proxy,
    address oldTarget,
    address newTarget,
    uint version,
    bytes ipfsAbi
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
    bytes32 _contractName,
    address _target,
    bytes16 _district
  ) public returns (address _proxy) {
    DistrictAdminProxy proxy = new DistrictAdminProxy(_contractName, _target, districtDesigner, _district);
    _proxy = address(proxy);
    isProxy[_proxy] = true;
    return _proxy;
  }


  function createOwnerProxy(
    bytes32 _contractName,
    address _target,
    address _owner
  ) public returns (address _proxy) {
    OwnerProxy proxy = new OwnerProxy(_contractName, _target, _owner);
    _proxy = address(proxy);
    isProxy[_proxy] = true;
    return _proxy;
  }


  function fireProxyTargetChangedEvent(
    address _oldTarget,
    address _newTarget,
    uint _version,
    bytes memory _ipfsAbi
  ) public onlyProxy {
    ProxyTargetChangedEvent(_contractName, msg.sender, _oldTarget, _newTarget, _version, _ipfsAbi);
  }
}
