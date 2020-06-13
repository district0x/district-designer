// SPDX-License-Identifier: MIT
pragma solidity >=0.4.22 <0.7.0;
pragma experimental ABIEncoderV2;

import "./openzeppelin/ERC1155/ERC1155.sol"; // Replace with npm dependency once published
import "@openzeppelin/contracts/access/Ownable.sol";


contract ERC1155Token is ERC1155, Ownable {

  uint public constant version = 1;

  constructor(
    string memory _baseURI
  ) public ERC1155(_baseURI) {
  }


  function mint(
    address to,
    uint256 id,
    uint256 value,
    bytes calldata data
  ) public onlyOwner {
    _mint(to, id, value, data);
  }


  function mintBatch(
    address to,
    uint256[] calldata ids,
    uint256[] calldata values,
    bytes calldata data
  ) public onlyOwner {
    _mintBatch(to, ids, values, data);
  }

}
