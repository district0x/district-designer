pragma solidity >=0.4.22 <0.7.0;

import "@openzeppelin/contracts/token/ERC721/ERC721.sol";
import "@openzeppelin/contracts/token/ERC721/ERC721Burnable.sol";
import "@openzeppelin/contracts/access/Ownable.sol";

contract DDERC721 is ERC721, ERC721Burnable, Ownable {

  uint public constant version = 1;

  constructor(
    string memory _tokenName,
    string memory _tokenSymbol
  )
  ERC721(_tokenName, _tokenSymbol)
  public {
  }


  function safeMint(
    address _to,
    uint256 _tokenId,
    string memory _tokenURI
  ) onlyOwner public virtual {
    _safeMint(_to, _tokenId);
    _setTokenURI(_tokenId, _tokenURI);
  }


  function safeMint(
    address _to,
    uint256 _tokenId,
    string memory _tokenURI,
    bytes memory _data
  ) onlyOwner public virtual {
    _safeMint(_to, _tokenId, _data);
    _setTokenURI(_tokenId, _tokenURI);
  }



}
