// SPDX-License-Identifier: MIT
pragma solidity >=0.4.22 <0.7.0;
pragma experimental ABIEncoderV2;

import "./Challengable.sol";
import "./TCR.sol";

contract RegistryEntry is Challengable {

  function initialize(
    address _creator,
    uint _tokenAmount,
    bytes memory _tokenIpfsData,
    bytes memory _ipfsData
  ) external {
  }

  function markAsMinted(
  ) external {
  }
}
