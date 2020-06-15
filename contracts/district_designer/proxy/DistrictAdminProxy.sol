// SPDX-License-Identifier: MIT
pragma solidity >=0.4.22 <0.7.0;
pragma experimental ABIEncoderV2;

import "./../District.sol";
import "./BaseProxy.sol";

/**
 * @dev Extending {BaseProxy} with {DistrictDesigner} permissions
 */

contract DistrictAdminProxy is BaseProxy {

  District public district;

  /**
   * @dev Constructor
   * If zero address is passed as `_district` it'll use address of `this`
   *
   * Emits an {DistrictCreated} event
   * TODO: Needs implementation
   */
  constructor(
    address _target,
    address _district
  ) public BaseProxy(_target) {
    if (_district == address(0)) {
      _district = address(this);
    }
    district = District(_district);
    target = _target;
  }


  /**
   * @dev Restricts target updating only to district's admin user role
   */
  function _canUpdateTarget(
    address _sender
  ) internal view override returns(bool) {
    return district.adminUserRoleHasAddress(_sender);
  }

}
