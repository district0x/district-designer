// SPDX-License-Identifier: MIT
pragma solidity >=0.4.22 <0.7.0;
pragma experimental ABIEncoderV2;

import "./TCR.sol";

contract Challengable {

  function isVoteRevealPeriodActive(
  ) public view
  returns (bool) {
    return true;
  }


  function isVoteRevealed(
    address _voter
  ) public view
  returns (bool) {
    return true;
  }


  function isVoteRewardClaimed(
    address _voter
  ) public view
  returns (bool) {
    return true;
  }


  function areVotesReclaimed(
    address _voter
  ) public view
  returns (bool) {
    return true;
  }


  function isChallengerRewardClaimed(
  ) public view
  returns (bool) {
    return true;
  }


  function isCreatorRewardClaimed(
  ) public view
  returns (bool) {
    return true;
  }


  function isChallengePeriodActive(
  ) public view
  returns (bool) {
    return true;
  }


  function isWhitelisted(
  ) public view
  returns (bool) {
    return true;
  }


  function isVoteCommitPeriodActive(
  ) public view
  returns (bool) {
    return true;
  }


  function isVoteRevealPeriodOver(
  ) public view
  returns (bool) {
    return true;
  }


  function isWinningOptionInclude(
  ) public view
  returns (bool) {
    return true;
  }


  function hasVoted(
    address _voter
  ) public view
  returns (bool) {
    return true;
  }


  function wasChallenged(
  ) public view
  returns (bool) {
    return true;
  }


  function votedWinningVoteOption(
    address _voter
  ) public view
  returns (bool) {
    return true;
  }


  function status(
  ) public view
  returns (TCR.EntryStatus _status) {
    return _status;
  }


  function challengerReward(
  ) public view
  returns (uint) {
    return 0;
  }


  function creatorReward(
  ) public view
  returns (uint) {
    return 0;
  }


  function voteOptionIncludeVoterAmount(
    address _voter
  ) public view
  returns (uint) {
    return 0;
  }


  function voteOptionExcludeVoterAmount(
    address _voter
  ) public view
  returns (uint) {
    return 0;
  }


  function voteReward(
    address _voter
  ) public view
  returns (uint) {
    return 0;
  }


  function isBlacklisted(
  ) public view
  returns (bool) {
    return true;
  }


  function voteOptionIncludeAmount(
  ) public view
  returns (uint) {
    return 0;
  }


  function voteOptionExcludeAmount(
  ) public view
  returns (uint) {
    return 0;
  }


  function winningVoteOption(
  ) public view
  returns (TCR.VoteOption _voteOption) {
    return _voteOption;
  }


  function winningVoteOptionAmount(
  ) public view
  returns (uint) {
    return 0;
  }


  function createChallenge(
    address _challenger,
    uint _challengeIndex,
    bytes calldata _ipfsData
  ) public {
  }


  function commitVote(
    address _voter,
    uint _amount,
    bytes32 _secretHash
  ) public {
  }


  function revealVote(
    address _voter,
    TCR.VoteOption _voteOption,
    string calldata _salt
  ) public {
  }


  function reclaimVotes(
    address _voter
  ) public {
  }


  function claimVoteReward(
    address _voter
  ) public {
  }


  function claimChallengeReward(
    address _voter
  ) public {
  }


  function claimCreatorReward(
    address _voter
  ) public {
  }


  function claimRewards(
    address _voter
  ) public {
  }


}
