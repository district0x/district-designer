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

  event TokenCreated(
    bytes16 district,
    bytes16 token,
    address tokenAddress,
    TokenType tokenType,
    bytes tokenIpfsAbi,
    uint tokenVersion,
    string tokenName,
    string tokenSymbol,
    string baseMetadataUri,
    uint8 decimalUnits,
    address owner,
    bytes ipfsData,
    uint timestamp
  );


  function setFactories(
    address[] memory factories,
    bool[] memory isActive
  ) public onlyOwner {
  }


  function fireTokenCreatedEvent(
    bytes16 _district,
    bytes16 _token,
    address _tokenAddress,
    TokenType _tokenType,
    bytes memory _tokenIpfsAbi,
    uint _tokenVersion,
    string memory _tokenName,
    string memory _tokenSymbol,
    string memory baseMetadataUri,
    uint8 _decimalUnits,
    address _owner,
    bytes memory _ipfsData
  ) public {
  }


}
