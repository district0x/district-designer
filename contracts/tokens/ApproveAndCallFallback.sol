pragma solidity >=0.4.22 <0.7.0;

abstract contract ApproveAndCallFallBack {
  function receiveApproval(
    address from,
    uint256 _amount,
    address _token,
    bytes memory _data
  ) public virtual;
}