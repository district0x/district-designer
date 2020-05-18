pragma solidity >=0.4.22 <0.7.0;

import "./DistrictDesigner.sol";
import "./proxy/DistrictAdminProxy.sol";
import "./proxy/OwnerProxy.sol";

contract DDProxyFactory {

  DistrictDesigner public districtDesigner;
  mapping(address => bool) public isProxy;

  event DistrictAdminProxyCreatedEvent(
    bytes32 indexed contractName,
    address proxy,
    address target,
    bytes16 district,
    bytes ipfsData
  );


  event OwnerProxyCreatedEvent(
    bytes32 indexed contractName,
    address proxy,
    address target,
    address owner,
    bytes ipfsData
  );


  event ProxyTargetChangedEvent(
    bytes32 indexed contractName,
    address proxy,
    address oldTarget,
    address newTarget,
    bytes ipfsData
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
    bytes16 _district,
    bytes memory _ipfsData
  ) public returns (address _proxy) {
    DistrictAdminProxy proxy = new DistrictAdminProxy(_contractName, _target, districtDesigner, _district);
    _proxy = address(proxy);
    isProxy[_proxy] = true;
    DistrictAdminProxyCreatedEvent(_contractName, _proxy, _target, _district, _ipfsData);
    return _proxy;
  }


  function createDistrictAdminProxy(
    bytes32 _contractName,
    address _target,
    bytes16 _district
  ) public returns (address) {
    bytes memory _ipfsData = new bytes(0);
    return createDistrictAdminProxy(_contractName, _target, _district, _ipfsData);
  }


  function createOwnerProxy(
    bytes32 _contractName,
    address _target,
    address _owner,
    bytes memory _ipfsData
  ) public returns (address _proxy) {
    OwnerProxy proxy = new OwnerProxy(_contractName, _target, _owner);
    _proxy = address(proxy);
    isProxy[_proxy] = true;
    OwnerProxyCreatedEvent(_contractName, _proxy, _target, _owner, _ipfsData);
    return _proxy;
  }


  function createOwnerProxy(
    bytes32 _contractName,
    address _target,
    address _owner
  ) public returns (address) {
    bytes memory _ipfsData = new bytes(0);
    return createOwnerProxy(_contractName, _target, _owner);
  }


  function fireProxyTargetChangedEvent(
    bytes32 _contractName,
    address _oldTarget,
    address _newTarget,
    bytes memory _ipfsData
  ) public onlyProxy {
    ProxyTargetChangedEvent(_contractName, msg.sender, _oldTarget, _newTarget, _ipfsData);
  }
}
