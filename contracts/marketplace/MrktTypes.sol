// SPDX-License-Identifier: MIT
pragma solidity >=0.4.22 <0.7.0;
pragma experimental ABIEncoderV2;

/**
 * @dev All Marketplace types are stored here in one place.
 * It's because compiler had problems with tangled dependencies. (I couldn't make it work, open for suggestions)
 */

abstract contract MrktTypes {

  enum OfferType {
    FIXED_PRICES,
    DYNAMIC_PRICE,
    HIGEST_BID_AUCTION,
    MULTI_TOKEN_AUCTION,
    DELIVERABLE_AUCTION
  }

  enum AssetCategory {
    ETH,
    ERC20,
    ERC721,
    ERC1155,
    /**
     * Deliverable is anything that is not a standardized token on Ethereum blockchain.
     * Can be a service, physical product, file, token outside Ethereum, etc.
     * Deliverable on any side of a trade automatically implies possibility of disputes.
     */
    DELIVERABLE
  }

  enum TokenType {
    ETH,
    ERC20,
    ERC721,
    ERC1155
  }

  struct Token {
    TokenType tokenType;
    address tokenAddress;
  }

  struct TradeAsset {
    AssetCategory assetCategory;
    address tokenAddress;
  }

  struct ETHValue {
    uint value;
  }

  struct ERC20Value {
    uint value;
  }

  struct ERC721Value {
    uint tokenId;
  }

  struct ERC1155Value {
    uint[] tokenIds;
    uint[] values;
  }

  struct TradeValue {
    TradeAsset tradeAsset;
    ETHValue ethValue;
    ERC20Value erc20Value;
    ERC721Value erc721Value;
    ERC1155Value erc1155Value;
  }

  struct PermissionUserRoles {
    bytes16[] createOfferUserRoles; // Only user roles that can create an offer
    bytes16[] offerResponseUserRoles; // Only user roles that can respond to an offer
    bytes16[] resolveDisputeUserRoles; // Only user roles that can resolve disputes
  }

  struct DeliverableAuctionOfferResponse {
    uint[] values; // Array, because ERC1155 can have multiple ids
  }

  struct DynamicPriceOfferRequest {
    TokenType tokenType;
    address tokenAddress;
    uint tokenId; // `tokenId` is used only for ERC1155
    uint startPrice;
    uint endPrice;
    uint duration;
  }

  struct FixedPricesOfferRequest {
    TradeValue[] prices; // Definitions of several fixed prices
  }

  struct HighestBidAuctionOfferRequest {
    TokenType tokenType;
    address tokenAddress;
    uint tokenId; // `tokenId` is used only for ERC1155
    uint minPrice;
    uint minBidStep;
    uint duration;
    /**
     * `extensionTriggerDuration` is amount of seconds before the end of auction,
     *  which, when received bid, will extend the auction by `extensionDuration`
     */
    uint extensionTriggerDuration;
    uint extensionDuration;
  }

  struct MultiTokenAuctionOfferRequest {
    Token[] acceptedTokens;
    uint duration;
    /**
     * `extensionTriggerDuration` is amount of seconds before the end of auction,
     *  which, when received bid, will extend the auction by `extensionDuration`
     */
    uint extensionTriggerDuration;
    uint extensionDuration;
  }

  struct OfferRequest {
    DynamicPriceOfferRequest dynamicPriceOfferRequest;
    FixedPricesOfferRequest fixedPriceOfferRequest;
    HighestBidAuctionOfferRequest highestBidAuctionOfferRequest;
    MultiTokenAuctionOfferRequest multiTokenAuctionOfferRequest;
  }
}
