pragma solidity >=0.4.22 <0.7.0;

import "./Tokens.sol";
import "@aragon/apps-shared-minime/contracts/MiniMeToken.sol";


contract ERC20MiniMeFactory {

  constructor(Tokens _tokensContractAddress)
  public
  {}

  function createToken(
    string _tokenName,
    uint8 _decimalUnits,
    string _tokenSymbol,
    address[] _inintialDistributionAddresses,
    uint[] _inintialDistributionAmounts
  ) public
  {
    MiniMeToken _;
  }
}
