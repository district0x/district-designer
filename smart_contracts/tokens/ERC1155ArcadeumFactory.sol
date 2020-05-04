pragma solidity >=0.4.22 <0.7.0;

import "./Tokens.sol";
import "../district_designer/DistrictDesigner.sol";
import 'multi-token-standard/contracts/tokens/ERC1155PackedBalance/ERC1155MintBurnPackedBalance.sol';

contract ERC1155ArcadeumFactory {

  constructor(
    DistrictDesigner _districtDesigner,
    Tokens _tokensContractAddress
  ) public
  {}

  function createToken(
    bytes16 _district,
    string _tokenName,
    string _tokenSymbol
  ) public
  {
    ERC1155MintBurnPackedBalance _;
  }
}
