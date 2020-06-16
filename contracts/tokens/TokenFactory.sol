// SPDX-License-Identifier: MIT
pragma solidity >=0.4.22 <0.7.0;
pragma experimental ABIEncoderV2;

import "@openzeppelin/contracts/access/Ownable.sol";
import "../district_designer/DistrictFactory.sol";
import "./ERC20Factory.sol";
import "./ERC721Factory.sol";
import "./ERC1155Factory.sol";


contract TokenFactory is Ownable {

  uint public constant version = 1;

  enum TokenType {
    ERC20,
    ERC721,
    ERC1155
  }

  event TokenCreated(
    address district,
    address token,
    TokenType tokenType,
    bytes tokenIpfsAbi,
    uint tokenVersion,
    string tokenName,
    string tokenSymbol,
    string baseURI,
    uint8 decimalUnits,
    address owner,
    bytes ipfsData,
    uint timestamp
  );


  event FactoryUpdated(
    address newFactory,
    TokenType tokenType,
    uint timestamp
  );


  function initialize(
    DistrictFactory _districtFactory,
    ERC20Factory _erc20Factory,
    ERC721Factory _erc721Factory,
    ERC1155Factory _erc1155Factory
  ) public {
  }


  function createERC20Token(
    address _district,
    string calldata _tokenName,
    string calldata _tokenSymbol,
    address[] calldata _inintialDistributionAddresses,
    uint[] calldata _inintialDistributionAmounts,
    bytes calldata _ipfsData
  ) public {
  }


  function createERC721Token(
    address _district,
    string calldata _tokenName,
    string calldata _tokenSymbol,
    string calldata _baseURI,
    bytes calldata _ipfsData
  ) public {
  }


  function createERC1155Token(
    address _district,
    string calldata _baseURI,
    bytes calldata _ipfsData
  ) public {
  }


  function updateFactory(
    address _newFactory,
    TokenType _tokenType
  ) public onlyOwner {
  }


  function fireTokenCreatedEvent(
    address _district,
    address _token,
    TokenType _tokenType,
    bytes memory _tokenIpfsAbi,
    uint _tokenVersion,
    string memory _tokenName,
    string memory _tokenSymbol,
    string memory _baseURI,
    uint8 _decimalUnits,
    address _owner,
    bytes memory _ipfsData
  ) public {
  }


}
