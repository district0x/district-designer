// SPDX-License-Identifier: MIT
pragma solidity >=0.4.22 <0.7.0;
pragma experimental ABIEncoderV2;

import "./ERC20Token.sol";
import "./BaseTokenFactory.sol";


contract ERC20TokenFactory is BaseTokenFactory {

  uint public constant version = 1;

  constructor(
    bytes memory _tokenIpfsAbi
  ) public BaseTokenFactory(_tokenIpfsAbi) {
  }


  /**
   * @dev Deploys a new {ERC20Token}
   * Sets `_owner` as the owner of the token contract
   * Returns the new address and tokenIpfsAbi
   * TODO: Needs implementation
   */
  function createToken(
    address _owner,
    string memory _tokenName,
    string memory _tokenSymbol,
    address[] memory _inintialDistributionAddresses,
    uint[] memory _inintialDistributionAmounts,
    bytes memory _ipfsData
  ) public returns (address _tokenAddress, bytes memory _tokenIpfsAbi){
    return (_tokenAddress, _tokenIpfsAbi);
  }
}
