pragma solidity >=0.4.22 <0.7.0;

import "../district_designer/DistrictDesigner.sol";
import "@openzeppelin/contracts/access/Ownable.sol";

contract TokenFactoryEvents is Ownable {

  uint public constant version = 1;

  enum TokenType {
    ERC20,
    ERC721,
    ERC1155
  }

  event TokenCreatedEvent(
    bytes16 district,
    bytes16 token,
    address tokenAddress,
    TokenType tokenType,
    string tokenName,
    string tokenSymbol,
    uint8 decimalUnits,
    address owner,
    bytes ipfsData
  );


  function setFactories(
    address[] memory factories,
    bool isActive
  ) public onlyOwner {
  }


  function fireTokenCreatedEvent(
    bytes16 _district,
    bytes16 _token,
    address _tokenAddress,
    TokenType _tokenType,
    string memory _tokenName,
    string memory _tokenSymbol,
    uint8 _decimalUnits,
    address _owner,
    bytes memory _ipfsData
  ) public {
  }


}
