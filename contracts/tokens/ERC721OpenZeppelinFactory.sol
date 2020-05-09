pragma solidity >=0.4.22 <0.7.0;

import "./Tokens.sol";
import "../district_designer/DistrictDesigner.sol";
import "@openzeppelin/contracts/token/ERC721/ERC721.sol";

contract ERC721OpenZeppelinFactory {

  constructor(
    DistrictDesigner _districtDesigner,
    Tokens _tokensContractAddress
  ) public
  {}

  function createToken(
    bytes16 _district,
    string memory _tokenName,
    string memory _tokenSymbol
  ) public
  {
    ERC721 _;
  }
}
