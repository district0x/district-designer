pragma solidity >=0.4.22 <0.7.0;

import "./../DistrictDesigner.sol";
import "./../DDProxyFactory.sol";
import "./BaseProxy.sol";
import "@openzeppelin/contracts/access/Ownable.sol";

contract OwnerProxy is BaseProxy, Ownable {

  constructor(
    address _target,
    address _owner
  ) public BaseProxy(_target) {
    require(_owner != address(0));
    transferOwnership(_owner);
  }


  function _canUpdateTarget(
    address _sender
  ) public view override returns(bool) {
    return owner() == _sender;
  }


}
