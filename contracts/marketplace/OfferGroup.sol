// SPDX-License-Identifier: MIT
pragma solidity >=0.4.22 <0.7.0;
pragma experimental ABIEncoderV2;

import "../district_designer/proxy/ProxyFactory.sol";
import "./MrktTypes.sol";
import "./MultiTokenAuctionOffer.sol";
import "./HighestBidAuctionOffer.sol";
import "./FixedPricesOffer.sol";
import "./DynamicPriceOffer.sol";
import "./DeliverableAuctionOffer.sol";
import "./OfferGroupFactory.sol";
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

  /**
   * @dev Contract initialization
   * It is manually called instead of native contructor,
   * because this contract is used through a proxy.
   * This function cannot be called twice.
   *
   * @param _fixedPricesOfferTarget Contract for {FixedPricesOffer} proxies
   * @param _dynamicPriceOfferTarget Contract for {DynamicPriceOffer} proxies
   * @param _highestBidAuctionOfferTarget Contract for {HighestBidAuctionOffer} proxies
   * @param _multiTokenAuctionOfferTarget Contract for {MultiTokenAuctionOffer} proxies
   * @param _deliverableAuctionOfferTarget Contract for {DeliverableAuctionOffer} proxies
   * @param _district {District} address
   * @param _offerableAssets Assets offerer can choose to offer
   * @param _requestableAssets Assets offerer can request for in his offers
   * @param _allowedOfferTypes Offer types allowed to be created
   * @param _permissionUserRoles Access restrictions for certain operations
   * @param _ipfsData Hash of additional data stored on IPFS
   *
   * `msg.sender` is considered to be {OfferGroupFactory}
   *
   * Requirements:
   *
   * - `_offerTarget` cannot be empty
   * - `_offerableAssets` cannot be empty
   * - `_requestableAssets` cannot be empty
   * - `_allowedOfferTypes` cannot be empty
   * - `_ipfsData` must be valid IPFS hash
   *
   * See spec :marketplace/offer-group-created for format of _ipfsData file
   */
  function initialize(
    ProxyFactory.ProxyTarget memory _fixedPricesOfferTarget,
    ProxyFactory.ProxyTarget memory _dynamicPriceOfferTarget,
    ProxyFactory.ProxyTarget memory _highestBidAuctionOfferTarget,
    ProxyFactory.ProxyTarget memory _multiTokenAuctionOfferTarget,
    ProxyFactory.ProxyTarget memory _deliverableAuctionOfferTarget,
    District _district,
    MrktTypes.TradeAsset[] memory _offerableAssets,
    MrktTypes.TradeAsset[] memory _requestableAssets,
    MrktTypes.OfferType[] memory _allowedOfferTypes,
    MrktTypes.PermissionUserRoles memory _permissionUserRoles,
    bytes memory _ipfsData
  ) external {
  }


  /**
   * @dev Creates a new {Offer}
   *
   * It creates a new offer in following steps:
   * 1. Creates new {OwnerProxy} forwarding to an offer contract based on `_offerType`.
   * 2. Transfers `_offeredValue` from this contract into newly created contract
   * 3. Calls `initialize` on the newly created contract
   *
   * Owner of the proxy is this contract. Created proxy is not meant to be updated.
   *
   * Requirements:
   *
   * - `_offerer` cannot be zero address
   * - `_offerer` must be within `Offer.PermissionUserRoles.createOfferUserRoles`, if it's not empty
   * - `_offeredValue` must be one of `offerableAssets`
   * - `_offerRequest` token addresses of any sort must be within `_requestableAssets`
   * - `_ipfsData` can be empty or valid IPFS hash
   *
   * Emits an {OfferCreated} event
   *
   * See spec :marketplace/offer-created for format of _ipfsData file
   * TODO: Needs implementation
   */
  function _createOffer(
    address _offerer,
    MrktTypes.TradeValue memory _offeredValue,
    MrktTypes.OfferType _offerType,
    MrktTypes.OfferRequest memory _offerRequest,
    address[] memory _allowedRespondents,
    bytes memory _ipfsData
  ) internal {
  }


  /**
   * @dev This function is for the sole purpose of creating an offer with
   * deliverable as the offered value. Since there's no token callback for that.
   * It calls {_createOffer}
   * TODO: Needs implementation
   */
  function createDeliverableOffer(
    MrktTypes.OfferType _offerType,
    MrktTypes.OfferRequest memory _offerRequest,
    address[] memory _allowedRespondents,
    bytes memory _ipfsData
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
    MrktTypes.TradeAsset[] memory _offerableAssets,
    MrktTypes.TradeAsset[] memory _requestableAssets,
    MrktTypes.OfferType[] memory _allowedOfferTypes,
    MrktTypes.PermissionUserRoles memory _permissionUserRoles,
    bytes memory _ipfsData
  ) external {
  }


  /**
   * @dev Updates proxy targets that newly created proxies will forward to
   * It's meant to be called only by {targetUpdated}
   *
   * Contracts with zero addresses will not be updated.
   *
   * Emits an {OfferGroupProxyTargetsUpdated} event
   * TODO: Needs implementation
   */
  function _updateProxyTargets(
    ProxyFactory.ProxyTarget memory _fixedPricesOfferTarget,
    ProxyFactory.ProxyTarget memory _dynamicPriceOfferTarget,
    ProxyFactory.ProxyTarget memory _highestBidAuctionOfferTarget,
    ProxyFactory.ProxyTarget memory _multiTokenAuctionOfferTarget,
    ProxyFactory.ProxyTarget memory _deliverableAuctionOfferTarget
  ) internal {
  }


  /**
   * @dev This function is called automatically when proxy forwarding to this contract
   * update its target.
   * It should decode `_data` into arguments of {_updateProxyTargets} and call it
   * TODO: Needs implementation
   */
  function targetUpdated(
    ProxyFactory.ProxyTarget memory _newOfferGroupTarget,
    bytes memory _data
  ) external override onlySelf {
  }


  /**
   * @dev This function is called automatically when this contract receives approval for ERC20 token
   * It transfers approved tokens into this contract.
   * It calls {_createOffer} where:
   * - passed `_offerer` is `_from`
   * - passed `_offeredValue` is constructed from the transferred token
   * - rest of arguments is obtained by decoding `_data`
   * TODO: Needs implementation
   */
  function receiveApproval(
    address _from,
    uint256 _amount,
    address _token,
    bytes memory _data
  ) external override {
  }


  /**
   * @dev This function is called automatically when this contract receives ERC721 token
   * It calls {_createOffer} where:
   * - passed `_offerer` is `_from`
   * - passed `_offeredValue` is constructed from the transferred token
   * - rest of arguments is obtained by decoding `_data`
   * TODO: Needs implementation
   */
  function onERC721Received(
    address _operator,
    address _from,
    uint256 _tokenId,
    bytes memory _data
  ) public override returns (bytes4) {
    return bytes4(keccak256("onERC721Received(address,address,uint256,bytes)"));
  }


  /**
   * @dev This function is called automatically when this contract receives ERC1155 token
   * It calls {_createOffer} where:
   * - passed `_offerer` is `_from`
   * - passed `_offeredValue` is constructed from the transferred token
   * - rest of arguments is obtained by decoding `_data`
   * TODO: Needs implementation
   */
  function onERC1155Received(
    address _operator,
    address _from,
    uint256 _id,
    uint256 _value,
    bytes calldata _data
  ) external override returns (bytes4) {
    return bytes4(keccak256("onERC1155Received(address,address,uint256,uint256,bytes)"));
  }


  /**
   * @dev This function is called automatically when this contract receives multiple ERC1155 tokens
   * If `_from` is the offerer, it decodes `_data` and calls {acceptOfferResponse}
   * If `_from` is not the offerer, it decodes `_data` and calls {_createOfferResponse}
   * TODO: Needs implementation
   */
  function onERC1155BatchReceived(
    address _operator,
    address _from,
    uint256[] calldata _ids,
    uint256[] calldata _values,
    bytes calldata _data
  ) external override returns (bytes4) {
    return bytes4(keccak256("onERC1155BatchReceived(address,address,uint256[],uint256[],bytes)"));
  }


  /**
  * @dev This function is called automatically when this contract receives ETH
   * It calls {_createOffer} where:
   * - passed `_offerer` is `msg.sender`
   * - passed `_offeredValue` is constructed from `msg.value`
   * - rest of arguments is obtained by decoding `msg.data`
  * TODO: Needs implementation
  */
  receive(
  ) external payable {
  }
}
