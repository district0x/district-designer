pragma solidity >=0.4.22 <0.7.0;

import "../district_designer/DistrictDesigner.sol";

contract TokenFactoryEvents {

  uint public constant version = 1;

  event TokenCreatedEvent(
    bytes16 district,
    address tokenAddress,
    string implementation,
    string tokenName,
    string tokenSymbol,
    uint8 decimalUnits,
    address controller
  );


  function setFactories(
    address[] memory factories,
    bool isActive
  ) public {
  }


  function fireTokenCreatedEvent(
    bytes16 _district,
    address _tokenAddress,
    string memory _implementation,
    string memory _tokenName,
    string memory _tokenSymbol,
    uint8 _decimalUnits,
    address _controller
  ) public
  {}


}
