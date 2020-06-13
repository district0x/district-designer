// SPDX-License-Identifier: MIT
pragma solidity >=0.4.22 <0.7.0;
pragma experimental ABIEncoderV2;

import "../district_designer/proxy/ProxyFactory.sol";
import "./OfferGroupFactory.sol";
import "./MultiTokenAuctionOffer.sol";
import "./HighestBidAuctionOffer.sol";
import "./FixedPricesOffer.sol";
import "./DynamicPriceOffer.sol";
import "./DeliverableAuctionOffer.sol";
import "../district_designer/District.sol";
import "../tokens/ApproveAndCallFallback.sol";
import "@openzeppelin/contracts/token/ERC721/IERC721Receiver.sol";
import "../tokens/openzeppelin/ERC1155/IERC1155Receiver.sol"; // Replace with npm dependency once published
import "@openzeppelin/contracts/introspection/ERC165.sol";


/**
 * @dev Contract for creating new {Offer} contracts
 * This contract is used through a proxy, therefore its address will never change.
 * Only admin user role of a district can update its OfferGroups, by updating target of a proxy
 */

contract OfferGroup is UpdateTargetAndCallFallBack, ApproveAndCallFallBack, IERC721Receiver, IERC1155Receiver, ERC165 {

  uint public constant version = 1;

  struct PermissionUserRoles {
    bytes16[] createOfferUserRoles; // Only user roles that can create an offer
    bytes16[] offerResponseUserRoles; // Only user roles that can respond to an offer
    bytes16[] resolveDisputeUserRoles; // Only user roles that can resolve disputes
  }

  struct Fees {
    uint createOfferFee; // Fee transferred to district treasury when offer is created
    uint offerResponseFee; // Fee transferred to district treasury when offer response is created
  }

  /**
   * @dev Contract initialization
   * It is manually called instead of native contructor,
   * because this contract is used through a proxy.
   * This function cannot be called twice.
   *
   * @param _offerBaseContract Address of base contract for {Offer} proxies
   * @param _offerIpfsAbi IPFS hash of ABI for {Offer} contract
   * @param _district {District} address
   * @param _offerableAssets Assets offerer can choose to offer
   * @param _requestableAssets Assets offerer can request for his offer
   * @param _allowedOfferTypes Offer types allowed to be created
   * @param _fees Operational fees associated with the offer
   * @param _permissionUserRoles Access restrictions for certain operations
   * @param _ipfsData Hash of additional data stored on IPFS
   *
   * `msg.sender` is considered to be {OfferGroupFactory}
   *
   * Requirements:
   *
   * - `_offerBaseContract` cannot be zero address
   * - `_offerIpfsAbi` must be valid ipfs hash
   * - `_offerableAssets` must contain at least 1 item
   * - `_requestableAssets` must contain at least 1 item
   *
   * See spec :marketplace/offer-group-created for format of _ipfsData file
   */
  function initialize(
    address _offerBaseContract,
    bytes memory _offerIpfsAbi,
    District _district,
    BaseOffer.TradeAsset[] memory _offerableAssets,
    BaseOffer.TradeAsset[] memory _requestableAssets,
    BaseOffer.OfferType[] memory _allowedOfferTypes,
    Fees memory _fees,
    PermissionUserRoles memory _permissionUserRoles,
    bytes memory _ipfsData
  ) external {
  }


  /**
   * @dev Creates a new {Offer}
   * It creates {OwnerProxy} forwarding to {Offer} contract
   * Owner of the proxy is this contract. Created proxy is not meant to be updatable
   *
   * Requirements:
   *
   * - `_offerer` cannot be zero address
   * - `_offerer` must be within `Offer.PermissionUserRoles.createOfferUserRoles`, if it's not empty
   * - `_offeredValue` must be one of `offerableAssets`
   * - `_requestedValues` must be all within `requestableAssets`
   *
   * Emits an {OfferCreated} event
   *
   * See spec :marketplace/offer-created for format of _ipfsData file
   * TODO: Needs implementation
   */
  function createOffer(
    address _offerer,
    BaseOffer.TradeValue calldata _offeredValue,
    address[] calldata _allowedRespondents,
    bytes calldata _ipfsData
  ) external {
  }

  /**
   * @dev Updates {OfferGroup}
   *
   * Requirements:
   *
   * - `msg.sender` must have "MANAGE_OFFER_GROUPS" permission for given district
   *
   * Emits an {OfferGroupUpdated} event
   *
   * See spec :marketplace/offer-group-updated for format of _ipfsData file
   * TODO: Needs implementation
   */
  function updateOfferGroup(
    BaseOffer.TradeAsset[] calldata _offerableAssets,
    BaseOffer.TradeAsset[] calldata _requestableAssets,
    BaseOffer.OfferType[] calldata _allowedOfferTypes,
    Fees calldata _fees,
    PermissionUserRoles calldata _permissionUserRoles,
    bytes calldata _ipfsData
  ) external {
  }


  /**
   * @dev Updates contract address and ABI of {Offer} contract that new proxies will forward to
   * It's meant to be called only by {targetUpdated}
   *
   * Requirements:
   *
   * - `_offerBaseContract` cannot be zero address
   * - `_offerIpfsAbi` must be valid ipfs hash
   *
   * Emits an {OfferGroupBaseContractsUpdated} event
   * TODO: Needs implementation
   */
  function _updateBaseContracts(
    address _offerBaseContract,
    bytes calldata _offerIpfsAbi
  ) internal {
  }


  /**
   * @dev This function is called automatically when proxy updates its target
   * It should decode `_data` into arguments of {_updateBaseContracts} and call it
   * TODO: Needs implementation
   */
  function targetUpdated(
    address _newTarget,
    bytes calldata _ipfsAbi,
    bytes calldata _data
  ) external override onlySelf {
  }


  /**
   * @dev Called when this contract receives ERC20 token as offered value when creating an offer
   * It should call {createOffer}
   * TODO: Needs implementation
   */
  function receiveApproval(
    address from,
    uint256 _amount,
    address _token,
    bytes memory _data
  ) external override {
  }


  /**
   * @dev Called when this contract receives ERC721 token as offered value when creating an offer
   * It should call {createOffer}
   * TODO: Needs implementation
   */
  function onERC721Received(
    address operator,
    address from,
    uint256 tokenId,
    bytes memory data
  ) public override returns (bytes4) {
    return bytes4(keccak256("onERC721Received(address,address,uint256,bytes)"));
  }


  /**
   * @dev Called when this contract receives ERC1155 token as offered value when creating an offer
   * It should call {createOffer}
   * TODO: Needs implementation
   */
  function onERC1155Received(
    address operator,
    address from,
    uint256 id,
    uint256 value,
    bytes calldata data
  ) external override returns (bytes4) {
    return bytes4(keccak256("onERC1155Received(address,address,uint256,uint256,bytes)"));
  }


  function onERC1155BatchReceived(
    address operator,
    address from,
    uint256[] calldata ids,
    uint256[] calldata values,
    bytes calldata data
  ) external override returns(bytes4) {
    return bytes4(keccak256("onERC1155BatchReceived(address,address,uint256[],uint256[],bytes)"));
  }


}
