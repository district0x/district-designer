pragma solidity >=0.4.22 <0.7.0;

import "./Tokens.sol";
import "@openzeppelin/contracts/token/ERC20/ERC20Snapshot.sol";


contract ERC20OpenZeppelinFactory {

  constructor(Tokens _tokensContractAddress)
  public
  {}

  function createToken(
    string _tokenName,
    string _tokenSymbol,
    address[] _inintialDistributionAddresses,
    uint[] _inintialDistributionAmounts
  ) public
  {
    ERC20 _;
  }
}
