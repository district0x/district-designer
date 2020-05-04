pragma solidity >=0.4.22 <0.7.0;

import "./Tokens.sol";
import 'multi-token-standard/contracts/tokens/ERC1155PackedBalance/ERC1155MintBurnPackedBalance.sol';

contract ERC1155ArcadeumFactory {

  constructor(Tokens _tokensContractAddress)
  public
  {}

  function createToken(
    string _tokenName,
    string _tokenSymbol
  ) public
  {
    ERC1155MintBurnPackedBalance _;
  }
}
