// SPDX-License-Identifier: MIT
pragma solidity >=0.4.22 <0.7.0;
pragma experimental ABIEncoderV2;

import "./ERC721Token.sol";
import "./BaseTokenFactory.sol";

contract ERC721Factory is BaseTokenFactory {

  uint public constant version = 1;

  constructor(
    bytes memory _tokenIpfsAbi
  ) public BaseTokenFactory(_tokenIpfsAbi) {
  }

  function createToken(
    address _district,
    string calldata _tokenName,
    string calldata _tokenSymbol,
    string calldata _baseURI,
    bytes calldata _ipfsData
  ) public {
  }
}
