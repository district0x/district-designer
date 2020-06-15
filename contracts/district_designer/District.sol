// SPDX-License-Identifier: MIT
pragma solidity >=0.4.22 <0.7.0;
pragma experimental ABIEncoderV2;

import "../district_designer/proxy/ProxyFactory.sol";
import "./DistrictFactory.sol";

/**
 * @dev Main contract indentifying each district
 * Maintains permissions and user roles
 * Maintains treasury address
 */

contract District {

  uint public constant version = 1;

  struct Permission {
    bytes32 permissionId;
    bytes16[] userRoleIds;
  }

  struct UserRole {
    bytes16 userRoleId;
    address[] addresses;
    bool isRemoved;
  }


  /**
   * @dev Returns true if given address has certain permission in a district
   * If a permission has no user roles assigned, only admin user role addresses are allowed
   * TODO: Needs implementation
   */
  function isAllowed(
    bytes32 _permission,
    address _address
  ) public view
    returns (bool) {
    return true;
  }


  /**
   * @dev Contract initialization
   * It is manually called instead of native contructor,
   * because this contract is used through a proxy.
   * This function cannot be called twice.
   *
   * It considers msg.sender to be {DistrictFactory}
   *
   * Requirements:
   *
   * - `_userRoles` cannot be empty, must contain at least admin user role
   * - `_adminUserRoleId` cannot be empty, must be one of passed `_userRoles`
   * - `_treasury` cannot be zero address
   * - `_ipfsData` must be valid ipfs hash
   *
   * See spec :district-designer/district-initialized for format of _ipfsData file
   * TODO: Needs implementation
   */
  function initialize(
    Permission[] memory _permissions,
    UserRole[] memory _userRoles,
    bytes16 _adminUserRoleId,
    address _treasury,
    bytes memory _ipfsData
  ) public {
  }


  /**
   * @dev Updates district's permissions with new user roles
   *
   * It updates only passed permissions, the ones that are not passed
   * are left unchanged in given district.
   *
   * Only admin user role is allowed to update permissions for a district
   *
   * Emits an {PermissionsUpdated} event
   * TODO: Needs implementation
   */
  function updatePermissions(
    Permission[] memory _permissions
  ) public {
  }


  /**
   * @dev Updates user role addresses
   *
   * Only admin user role is allowed to update user roles for a district
   *
   * Emits an {UserRolesUpdated} event
   *
   * Requirements:
   *
   * - `_ipfsData` must be valid ipfs hash
   *
   * See spec :district-designer/user-roles-updated for format of _ipfsData file
   * TODO: Needs implementation
   */
  function updateUserRoles(
    UserRole[] memory _userRoles,
    bytes memory _ipfsData
  ) public {
  }


  /**
   * @dev Returns admin user role of a district
   */
  function adminUserRole(
  ) public view
  returns (UserRole memory _userRole) {
    return _userRole;
  }


  /**
   * @dev Returns true if admin user role for a given district contains given address
   * TODO: Needs implementation
   */
  function adminUserRoleHasAddress(
    address _address
  ) public view
  returns (bool) {
    return true;
  }


  /**
   * @dev Returns true if a user role contains an address
   * TODO: Needs implementation
   */
  function userRoleHasAddress(
    bytes16 _userRoleId,
    address _address
  ) public view
  returns (bool) {
    return true;
  }


  /**
   * @dev Returns true if an array of user roles contain an address
   * TODO: Needs implementation
   */
  function userRolesHaveAddress(
    bytes16[] memory _userRoleIds,
    address _address
  ) public view
  returns (bool) {
    return true;
  }


  /**
   * @dev Updates a district treasury address
   *
   * Only admin user role is allowed to update district treasury
   *
   * Emits an {DistrictTreasuryUpdated} event
   *
   * Requirements:
   *
   * - `_treasury` cannot be zero address
   * TODO: Needs implementation
   */
  function updateTreasury(
    address _treasury
  ) public {
  }


}
