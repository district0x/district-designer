pragma solidity >=0.4.22 <0.7.0;

import  "./Proxy.sol";

contract ProxyFactory {

  event ProxyCreatedEvent(
    address proxy,
    address target,
    address owner
  );


  function createProxy(
    address _target,
    address _owner
  ) public returns (address _proxy) {
    Proxy proxy = new Proxy();
    proxy.setTarget(_target);
    proxy.transferOwnership(_owner);
    _proxy = address(proxy);
    ProxyCreatedEvent(_proxy, _target, _owner);
    return _proxy;
  }
}
