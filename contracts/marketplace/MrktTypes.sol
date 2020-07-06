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

  enum TokenType {
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

  struct TokenContract {
    TokenType tokenType;
    address tokenAddress;
  }

  struct Token {
    TokenContract tokenContract;
    uint tokenId;
  }

  struct TokenValue {
    Token token;
    uint value;
  }

  struct PermissionUserRoles {
    bytes16[] createOfferUserRoles; // Only user roles that can create an offer
    bytes16[] offerResponseUserRoles; // Only user roles that can respond to an offer
    bytes16[] resolveDisputeUserRoles; // Only user roles that can resolve disputes
  }

  struct DynamicPriceOfferRequest {
    Token token;
    uint startPrice;
    uint endPrice;
    uint duration;
  }

  struct FixedPricesOfferRequest {
    TokenValue[][] prices; // Definitions of several fixed prices
  }

  struct HighestBidAuctionOfferRequest {
    Token token;
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
    TokenContract[] acceptedTokenContracts;
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
