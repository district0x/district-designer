pragma solidity >=0.4.22 <0.7.0;
pragma experimental ABIEncoderV2;

contract Challengable {

  enum VoteOption {
    Include,
    Exclude,
    NoVote
  }


  constructor()
  public {
  }

  function createChallenge(
    address _challenger,
    bytes16 _challenge
  ) public
  {}


  function commitVote(
    address _voter,
    uint _amount,
    bytes32 _secretHash
  ) public
  {}


  function revealVote(
    address _voter,
    VoteOption _voteOption,
    string memory _salt
  ) public
  {}


  function reclaimVotes(
    address _voter
  ) public
  {}


  function claimVoteReward(
    address _voter
  ) public
  {}


  function claimChallengeReward(
    address _voter
  ) public
  {}


  function claimCreatorReward(
    address _voter
  ) public
  {}


  function claimRewards(
    address _voter
  ) public
  {}


}
