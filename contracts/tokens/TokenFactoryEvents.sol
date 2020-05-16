pragma solidity >=0.4.22 <0.7.0;

import "../district_designer/DistrictDesigner.sol";

contract TokenFactoryEvents {

  uint public constant version = 1;

  event TokenCreatedEvent(
    bytes16 district,
    bytes16 token,
    address tokenAddress,
    string tokenName,
    string tokenSymbol,
    uint8 decimalUnits,
    address owner
  );


  function setFactories(
    address[] memory factories,
    bool isActive
  ) public {
  }


  function fireTokenCreatedEvent(
    bytes16 _district,
    bytes16 _token,
    address _tokenAddress,
    string memory _tokenName,
    string memory _tokenSymbol,
    uint8 _decimalUnits,
    address _owner
  ) public
  {}


}
