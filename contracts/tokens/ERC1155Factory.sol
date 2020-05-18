pragma solidity >=0.4.22 <0.7.0;

import "./TokenFactoryEvents.sol";
import "../district_designer/DistrictDesigner.sol";
import "./DDERC1155.sol";

contract ERC1155Factory {

  uint public constant version = 1;

  constructor(
    DistrictDesigner _districtDesigner,
    TokenFactoryEvents _tokensContractAddress
  ) public {
  }

  function createToken(
    bytes16 _district,
    bytes16 _token,
    string memory _tokenName,
    string memory _tokenSymbol,
    bytes memory _ipfsData
  ) public {
    DDERC1155 _;
  }
}
