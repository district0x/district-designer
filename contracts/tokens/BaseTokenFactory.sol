// SPDX-License-Identifier: MIT
pragma solidity >=0.4.22 <0.7.0;
pragma experimental ABIEncoderV2;

import  "../_libs/validIPFSHash/validIPFSHash.sol";

/**
 * @dev Base contract for {ERC20TokenFactory} {ERC20TokenFactory} {ERC20TokenFactory}
 * It stores token's IPFS ABI
 */

contract BaseTokenFactory {
  using validIPFSHash for bytes;

  bytes public tokenIpfsAbi;

  constructor(
    bytes memory _tokenIpfsAbi
  ) public {
    require(tokenIpfsAbi.isValidIPFSHash());
    tokenIpfsAbi = _tokenIpfsAbi;
  }

}
