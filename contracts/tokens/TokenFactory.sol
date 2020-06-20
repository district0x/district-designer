// SPDX-License-Identifier: MIT
pragma solidity >=0.4.22 <0.7.0;
pragma experimental ABIEncoderV2;

import "@openzeppelin/contracts/access/Ownable.sol";
import "../district_designer/DistrictFactory.sol";
import "./ERC20TokenFactory.sol";
import "./ERC721TokenFactory.sol";
import "./ERC1155TokenFactory.sol";

/**
 * @dev Factory contract for creating {ERC20Token} {ERC721Token} {ERC1155Token}
 * It also emits all events related to tokens module
 * This contract is used through a proxy, therefore its address will never change.
 * No breaking changes will be introduced for events, so they all stay accessible from a single contract.
 */

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


  /**
   * @dev Contract initialization
   * It is manually called instead of native contructor,
   * because this contract is used through a proxy.
   * This function cannot be called twice.
   *
   * @param _districtFactory Address of {DistrictFactory}
   * @param _erc20TokenFactory Address of {ERC20TokenFactory}
   * @param _erc721TokenFactory Address of {ERC721TokenFactory}
   * @param _erc1155TokenFactory Address of {ERC1155TokenFactory}
   *
   * Requirements:
   *
   * - `_districtFactory` cannot be zero address
   * - `_erc20TokenFactory` cannot be zero address
   * - `_erc721TokenFactory` cannot be zero address
   * - `_erc1155TokenFactory` cannot be zero address
   * TODO: Needs implementation
   */
  function initialize(
    DistrictFactory _districtFactory,
    ERC20TokenFactory _erc20TokenFactory,
    ERC721TokenFactory _erc721TokenFactory,
    ERC1155TokenFactory _erc1155TokenFactory
  ) public {
  }


  /**
   * @dev Creates a new {ERC20Token} using {ERC20TokenFactory}
   *
   * It makes `msg.sender` the owner of a token contract.
   *
   * Requirements:
   *
   * - `_district` must be existing district created by {DistrictFactory}
   * - `msg.sender` must have "MANAGE_TOKENS" permission for given district
   *
   * Emits an {TokenCreated} event
   * TODO: Needs implementation
   */
  function createERC20Token(
    address _district,
    string calldata _tokenName,
    string calldata _tokenSymbol,
    address[] calldata _inintialDistributionAddresses,
    uint[] calldata _inintialDistributionAmounts,
    bytes calldata _ipfsData
  ) public {
  }


  /**
   * @dev Creates a new {ERC721Token} using {ERC721TokenFactory}
   *
   * It makes `msg.sender` the owner of a token contract.
   *
   * Requirements:
   *
   * - `_district` must be existing district created by {DistrictFactory}
   * - `msg.sender` must have "MANAGE_TOKENS" permission for given district
   *
   * Emits an {TokenCreated} event
   * TODO: Needs implementation
   */
  function createERC721Token(
    address _district,
    string calldata _tokenName,
    string calldata _tokenSymbol,
    string calldata _baseURI,
    bytes calldata _ipfsData
  ) public {
  }


  /**
   * @dev Creates a new {ERC1155Token} using {ERC1155TokenFactory}
   *
   * It makes `msg.sender` the owner of a token contract.
   *
   * Requirements:
   *
   * - `_district` must be existing district created by {DistrictFactory}
   * - `msg.sender` must have "MANAGE_TOKENS" permission for given district
   *
   * Emits an {TokenCreated} event
   * TODO: Needs implementation
   */
  function createERC1155Token(
    address _district,
    string calldata _baseURI,
    bytes calldata _ipfsData
  ) public {
  }


  /**
   * @dev Updates factory contract address
   *
   * Requirements:
   *
   * - `msg.sender` must be the owner of {TokenFactory}
   * - `_newFactory` cannot be zero address
   *
   * Emits an {FactoryUpdated} event
   * TODO: Needs implementation
   */
  function updateFactory(
    address _newFactory,
    TokenType _tokenType
  ) public onlyOwner {
  }
}
