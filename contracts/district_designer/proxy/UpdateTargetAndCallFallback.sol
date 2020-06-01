pragma solidity >=0.4.22 <0.7.0;

abstract contract UpdateTargetAndCallFallBack {
  function targetUpdated(
    address _newTarget,
    bytes memory _ipfsData,
    bytes memory _data
  ) public virtual;
}