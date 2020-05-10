pragma solidity >=0.4.22 <0.7.0;
pragma experimental ABIEncoderV2;

import "./Challengable.sol";
import "./TCR.sol";

contract ParamChangeEntry is Challengable {

  constructor(
    address _creator,
    bytes16 _paramChangeEntry,
    TCR.EntriesGroup _entriesGroup,
    string memory _key,
    uint _value
  )
  public
  {}

  function markAsApplied(
  ) public
  {}

}
