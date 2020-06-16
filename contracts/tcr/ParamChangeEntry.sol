// SPDX-License-Identifier: MIT
pragma solidity >=0.4.22 <0.7.0;
pragma experimental ABIEncoderV2;

import "./Challengable.sol";
import "./TCR.sol";

contract ParamChangeEntry is Challengable {

  function initialize(
    address _creator,
    TCR.EntriesGroup _entriesGroup,
    string memory _key,
    uint _value,
    bytes memory _ipfsData
  ) external {
  }

  function markAsApplied(
  ) external {
  }

}
