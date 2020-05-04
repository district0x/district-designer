pragma solidity >=0.4.22 <0.7.0;

import "./Tokens.sol";
import "../district_designer/DistrictDesigner.sol";
import "@openzeppelin/contracts/token/ERC20/ERC20Snapshot.sol";


contract ERC20OpenZeppelinFactory {

  constructor(
    DistrictDesigner _districtDesigner,
    Tokens _tokensContractAddress
  ) public
  {}

  function createToken(
    bytes16 _district,
    string _tokenName,
    string _tokenSymbol,
    address[] _inintialDistributionAddresses,
    uint[] _inintialDistributionAmounts
  ) public
  {
    ERC20 _;
  }
}