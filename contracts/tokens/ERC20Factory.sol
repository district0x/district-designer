pragma solidity >=0.4.22 <0.7.0;

import "./TokenFactoryEvents.sol";
import "../district_designer/DistrictDesigner.sol";
import "./DDERC20.sol";


contract ERC20Factory {

  uint public constant version = 1;

  constructor(
    DistrictDesigner _districtDesigner,
    TokenFactoryEvents _tokenFactoryEvents,
    bytes memory _tokenIpfsAbi
  ) public {
  }

  function createToken(
    bytes16 _district,
    bytes16 _token,
    string memory _tokenName,
    string memory _tokenSymbol,
    address[] memory _inintialDistributionAddresses,
    uint[] memory _inintialDistributionAmounts,
    bytes memory _ipfsData
  ) public {
    DDERC20 _;
  }
}
