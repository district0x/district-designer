pragma solidity >=0.4.22 <0.7.0;

import "../district_designer/DistrictDesigner.sol";

contract Tokens {

  constructor(DistrictDesigner _districtDesigner) public {}

  event TokenCreatedEvent(
    bytes16 district,
    address tokenAddress,
    string implementation,
    string tokenName,
    string tokenSymbol,
    uint8 decimalUnits,
    address controller
  );

  function fireTokenCreatedEvent(
    bytes16 _district,
    address _tokenAddress,
    string _implementation,
    string _tokenName,
    string _tokenSymbol,
    uint8 _decimalUnits,
    address _controller
  ) public
  {}


}
