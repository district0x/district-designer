pragma solidity >=0.4.22 <0.7.0;

import "./Tokens.sol";
import "@openzeppelin/contracts/token/ERC721/ERC721.sol";

contract ERC721OpenZeppelinFactory {

  constructor(Tokens _tokensContractAddress)
  public
  {}

  function createToken(
    string _tokenName,
    string _tokenSymbol
  ) public
  {
    ERC721 _;
  }
}
