// SPDX-License-Identifier: MIT
pragma solidity >=0.4.22 <0.7.0;
pragma experimental ABIEncoderV2;

library validIPFSHash {
  function isValidIPFSHash(
    bytes memory _ipfsHash
  ) public returns (bool) {
    return _ipfsHash.length == 46;
  }
}