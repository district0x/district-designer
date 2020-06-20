// SPDX-License-Identifier: MIT
pragma solidity >=0.4.22 <0.7.0;
pragma experimental ABIEncoderV2;

import "./TCR.sol";
import "../tokens/ApproveAndCallFallback.sol";
import "../tokens/openzeppelin/ERC1155/ERC1155Receiver.sol"; // Replace with npm dependency once published

/**
 * @dev Abstract contract providing challenge/voting features for TCR entries
 */

abstract contract Challengable is ApproveAndCallFallBack, ERC1155Receiver {

  /**
   * @dev Contract initialization
   * It is manually called instead of native contructor,
   * because this contract is used through a proxy.
   * This function cannot be called twice.
   *
   * For CHALLENGABLE_ONCE this function starts initial challenge period.
   *
   * It considers msg.sender to be {TCR}
   *
   * Requirements:
   *
   * - `_creator` cannot be zero address
   * TODO: Needs implementation
   */
  function _initialize(
    address _creator,
    TCR.TCRType _tcrType
  ) internal {
  }


  /**
   * @dev Creates a new challenge
   * It checks if `transferredValue` matches required voting token and deposit amount.
   * Entry cannot be challenged if its previous challenge hasn't been resolved yet.
   * For CHALLENGABLE_ONCE entry must be in the initial challenge period.
   * For CHALLENGEABLE_ANYTIME entry cannot be currently challenged or blacklisted already.
   *
   * Requirements:
   *
   * - `_creator` cannot be zero address
   * - `_ipfsData` must be valid IPFS hash
   * TODO: Needs implementation
   */
  function _createChallenge(
    address _challenger,
    TCR.VotingTokenValue memory _transferredValue,
    bytes memory _ipfsData
  ) internal {
  }


  /**
   * @dev Commit a vote
   * It checks if `transferredValue` matches required voting token.
   * Voting is allowed only if the entry is in vote commit period.
   * If the same voter votes multiple times, it adds up amounts and uses the last secret hash.
   *
   * Requirements:
   *
   * - `_voter` cannot be zero address
   * - `_secretHash` cannot be empty
   * TODO: Needs implementation
   */
  function _commitVote(
    address _voter,
    TCR.VotingTokenValue memory _transferredValue,
    bytes32 _secretHash
  ) internal {
  }


  /**
   * @dev Reveal a vote
   * It checks if hashing `_voteOption` and `_salt` together equals previously commited `_secretHash`.
   * Revealing is allowed only if the entry is in vote reveal period.
   * Same vote cannot be revealed twice.
   * It adds up votes based on `_voteOption`.
   *
   * Requirements:
   *
   * - `_voter` cannot be zero address
   * - `_secretHash` cannot be empty
   * TODO: Needs implementation
   */
  function revealVote(
    address _voter,
    TCR.VoteOption _voteOption,
    string memory _salt
  ) external {
  }


  /**
   * @dev Convenience function that calls {reclaimVotes} with the last challenge index.
   * TODO: Needs implementation
   */
  function reclaimVotes(
    address _voter
  ) public {
  }


  /**
   * @dev Reclaims votes back to the voter.
   * This function is used when a voter misses revealing his vote.
   * It transfers stored votes back to the voter.
   *
   * Requirements:
   *
   * - `_voter` cannot be zero address
   * - `_secretHash` cannot be empty
   * TODO: Needs implementation
   */
  function reclaimVotes(
    address _voter,
    uint _challengeIndex
  ) public {
  }


  /**
   * @dev Convenience function that calls {claimVoteReward} with the last challenge index.
   * TODO: Needs implementation
   */
  function claimVoteReward(
    address _voter
  ) public {
  }


  /**
   * @dev Claims vote reward
   * Vote reward is calculated as follows:
   * - Value of 1 deposit is split according to the parameter challengeDispensation.
   * - If challengeDispensation is 50, it splits 50/50. If it's 40, it splits 40/60.
   * - First part of this split is reserved as creator's or challenger's reward, depends on who won.
   * - The second part of the split is rewarded to voters according to their vote amounts.
   *
   * Requirements:
   *
   * - `_voter` cannot be zero address
   * TODO: Needs implementation
   */
  function claimVoteReward(
    address _voter,
    uint _challengeIndex
  ) public {
  }


  /**
   * @dev Convenience function that calls {claimChallengerReward} with the last challenge index.
   * TODO: Needs implementation
   */
  function claimChallengerReward(
  ) public {
  }


  /**
   * @dev Claims challenger's reward
   * If VoteOption.Exclude won, challenger gets his reward according to challengeDispensation
   * as explained in {claimVoteReward}. Additionally, challenger gets his deposit back.
   *
   * Can be called by anyone, but transfers reward only to the challenger.
   *
   * Requirements:
   *
   * - `_voter` cannot be zero address
   * TODO: Needs implementation
   */
  function claimChallengerReward(
    uint _challengeIndex
  ) public {
  }


  /**
   * @dev Convenience function that calls {claimChallengerReward} with the last challenge index.
   * TODO: Needs implementation
   */
  function claimCreatorReward(
  ) public {
  }


  /**
   * @dev Claims creator's reward
   * If VoteOption.Include won, challenger gets his reward according to challengeDispensation
   * as explained in {claimVoteReward}. Creator doesn't get his deposit back.
   *
   * Can be called by anyone, but transfers reward only to the creator.
   *
   * Requirements:
   *
   * - `_voter` cannot be zero address
   * TODO: Needs implementation
   */
  function claimCreatorReward(
    uint _challengeIndex
  ) public {
  }


  /**
   * @dev Convenience function that calls {claimRewards} with the last challenge index.
   * TODO: Needs implementation
   */
  function claimRewards(
    address _voter
  ) public {
  }


  /**
   * @dev Unifying function for all:
   * - {claimCreatorReward}
   * - {claimChallengerReward}
   * - {claimVoteReward}
   * - {reclaimVotes}
   *
   * TODO: Needs implementation
   */
  function claimRewards(
    address _voter,
    uint _challengeIndex
  ) public {
  }


  /**
   * @dev Returns true if challenge period is active.
   * TODO: Needs implementation
   */
  function isChallengePeriodActive(
  ) public view
  returns (bool) {
    return true;
  }


  /**
   * @dev Returns true if the entry is currently in vote reveal period.
   * TODO: Needs implementation
   */
  function isVoteRevealPeriodActive(
  ) public view
  returns (bool) {
    return true;
  }


  /**
   * @dev Returns true if the entry is currently in vote commit period.
   * TODO: Needs implementation
   */
  function isVoteCommitPeriodActive(
  ) public view
  returns (bool) {
    return true;
  }


  /**
   * @dev Returns true if the revealed period is over.
   * TODO: Needs implementation
   */
  function isVoteRevealPeriodOver(
    uint _challengeIndex
  ) public view
  returns (bool) {
    return true;
  }


  /**
   * @dev Returns true if the voter revealed his vote.
   * TODO: Needs implementation
   */
  function isVoteRevealed(
    address _voter,
    uint _challengeIndex
  ) public view
  returns (bool) {
    return true;
  }


  /**
   * @dev Returns true if voter claimed his reward.
   * TODO: Needs implementation
   */
  function isVoteRewardClaimed(
    address _voter,
    uint _challengeIndex
  ) public view
  returns (bool) {
    return true;
  }


  /**
   * @dev Returns true if voter reclaimed his votes.
   * TODO: Needs implementation
   */
  function areVotesReclaimed(
    address _voter
  ) public view
  returns (bool) {
    return true;
  }


  /**
   * @dev Returns true if challenger claimed his reward.
   * TODO: Needs implementation
   */
  function isChallengerRewardClaimed(
  ) public view
  returns (bool) {
    return true;
  }


  /**
   * @dev Returns true if creator claimed his reward.
   * TODO: Needs implementation
   */
  function isCreatorRewardClaimed(
  ) public view
  returns (bool) {
    return true;
  }


  /**
   * @dev Returns true if entry is whitelisted
   * TODO: Needs implementation
   */
  function isWhitelisted(
  ) public view
  returns (bool) {
    return true;
  }


  /**
   * @dev Returns true if entry is blacklisted
   * TODO: Needs implementation
   */
  function isBlacklisted(
  ) public view
  returns (bool) {
    return true;
  }


  /**
   * @dev Returns true if VoteOption.Include won
   * TODO: Needs implementation
   */
  function isWinningOptionInclude(
    uint _challengeIndex
  ) public view
  returns (bool) {
    return true;
  }


  /**
   * @dev Returns true if given voter has voted
   * TODO: Needs implementation
   */
  function hasVoted(
    address _voter
  ) public view
  returns (bool) {
    return true;
  }


  /**
   * @dev Returns true if entry was challenged. (For CHALLENGABLE_ONCE)
   * TODO: Needs implementation
   */
  function wasChallenged(
  ) public view
  returns (bool) {
    return true;
  }


  /**
   * @dev Returns true if given voter has voter for winning vote option.
   * TODO: Needs implementation
   */
  function votedWinningVoteOption(
    address _voter
  ) public view
  returns (bool) {
    return true;
  }


  /**
   * @dev Returns status of the entry. TCR.EntryStatus
   * TODO: Needs implementation
   */
  function status(
  ) public view
  returns (TCR.EntryStatus _status) {
    return _status;
  }


  /**
   * @dev Returns amount of votes voter voted for VoteOption.Include
   * TODO: Needs implementation
   */
  function voteOptionIncludeVoterAmount(
    address _voter
  ) public view
  returns (uint) {
    return 0;
  }


  /**
   * @dev Returns amount of votes voter voted for VoteOption.Exclude
   * TODO: Needs implementation
   */
  function voteOptionExcludeVoterAmount(
    address _voter
  ) public view
  returns (uint) {
    return 0;
  }


  /**
   * @dev Returns amount of votes for VoteOption.Include
   * TODO: Needs implementation
   */
  function voteOptionIncludeAmount(
    uint _challengeIndex
  ) public view
  returns (uint) {
    return 0;
  }


  /**
   * @dev Returns amount of votes for VoteOption.Exclude
   * TODO: Needs implementation
   */
  function voteOptionExcludeAmount(
    uint _challengeIndex
  ) public view
  returns (uint) {
    return 0;
  }


  /**
   * @dev Returns the winning vote option
   * TODO: Needs implementation
   */
  function winningVoteOption(
    uint _challengeIndex
  ) public view
  returns (TCR.VoteOption _voteOption) {
    return _voteOption;
  }


  /**
   * @dev Returns amount of votes for the winning vote option
   * TODO: Needs implementation
   */
  function winningVoteOptionAmount(
    uint _challengeIndex
  ) public view
  returns (uint) {
    return 0;
  }


  /**
   * @dev This function is called automatically when this contract receives approval for ERC20 token
   * It transfers approved tokens into this contract.
   * If the entry is currently challenged, it decodes `_data` and calls {_commitVote}
   * If the entry is not currently challenged, it decodes `_data` and calls {_createChallenge}
   * It constructs `_transferredValue` from actually trasferred token
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
   * @dev This function is called automatically when this contract receives ERC1155 token
   * If the entry is currently challenged, it decodes `_data` and calls {_commitVote}
   * If the entry is not currently challenged, it decodes `_data` and calls {_createChallenge}
   * It constructs `_transferredValue` from actually trasferred token
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


  /**
   * @dev This function is called automatically when this contract receives multiple ERC1155 tokens
   * It always reverts, because voting cannot happen with multiple tokens.
   */
  function onERC1155BatchReceived(
    address operator,
    address from,
    uint256[] calldata ids,
    uint256[] calldata values,
    bytes calldata data
  ) external override returns (bytes4) {
    revert();
  }


}
