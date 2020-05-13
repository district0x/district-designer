pragma solidity >=0.4.22 <0.7.0;

import "@openzeppelin/contracts/token/ERC20/ERC20Snapshot.sol";
import "@openzeppelin/contracts/access/Ownable.sol";
import "./ApproveAndCallFallback.sol";


contract DDERC20 is ERC20Snapshot, Ownable {

  constructor(
    string memory _tokenName,
    string memory _tokenSymbol
  )
  ERC20(_tokenName, _tokenSymbol)
  public
  {}


  /**
   * @dev Creates `amount` new tokens for `to`.
   *
   * See {ERC20-_mint}.
   *
   * Requirements:
   *
   * - the caller must have be the owner.
   */
  function mint(
    address to,
    uint256 amount
  ) onlyOwner public virtual {
    _mint(to, amount);
  }


  /**
   * @dev Destroys `amount` tokens from the caller.
   *
   * See {ERC20-_burn}.
   */
  function burn(
    uint256 amount
  ) public virtual {
    _burn(_msgSender(), amount);
  }


  /**
   * @dev Destroys `amount` tokens from `account`, deducting from the caller's
   * allowance.
   *
   * See {ERC20-_burn} and {ERC20-allowance}.
   *
   * Requirements:
   *
   * - the caller must have allowance for ``accounts``'s tokens of at least
   * `amount`.
   */
  function burnFrom(
    address account,
    uint256 amount
  ) public virtual {
    uint256 decreasedAllowance = allowance(account, _msgSender()).sub(amount, "ERC20: burn amount exceeds allowance");

    _approve(account, _msgSender(), decreasedAllowance);
    _burn(account, amount);
  }


  /**
   * @notice `msg.sender` approves `_spender` to send `_amount` tokens on
   *  its behalf, and then a function is triggered in the contract that is
   *  being approved, `_spender`. This allows users to use their tokens to
   *  interact with contracts in one function call instead of two
   * @param _spender The address of the contract able to transfer the tokens
   * @param _amount The amount of tokens to be approved for transfer
   * @return True if the function call was successful
   */
  function approveAndCall(
    ApproveAndCallFallBack _spender, 
    uint256 _amount, 
    bytes memory _extraData
  ) public returns (bool) {
    require(approve(address(_spender), _amount));

    _spender.receiveApproval(
      msg.sender,
      _amount,
      address(this),
      _extraData
    );

    return true;
  }

}
