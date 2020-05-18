pragma solidity >=0.4.22 <0.7.0;
pragma experimental ABIEncoderV2;

import "./Challengable.sol";
import "./TCR.sol";

contract RegistryEntry is Challengable {

  constructor(
    address _creator,
    bytes16 _regEntry,
    uint _tokenAmount,
    bytes memory _tokenIpfsData,
    bytes memory _ipfsData
  ) public {
  }

  function markAsMinted(
  ) public {
  }
}
