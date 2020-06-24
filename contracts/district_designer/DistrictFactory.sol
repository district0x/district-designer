// SPDX-License-Identifier: MIT
pragma solidity >=0.4.22 <0.7.0;
pragma experimental ABIEncoderV2;

import "@openzeppelin/contracts/access/Ownable.sol";
import "./proxy/ProxyFactory.sol";
import "./District.sol";

/**
 * @dev Factory contract for creating {District} contracts.
 * It also emits all events related to District Designer core module
 * This contract is used through a proxy, therefore its address will never change.
 * No breaking changes will be introduced for events, so they all stay accessible from a single contract.
 * Also provides emergency state for modules, based on which code execution can be temporarily restricted
 * in other modules.
 */

contract DistrictFactory is Ownable, UpdateTargetAndCallFallBack {

  uint public constant version = 1;

  mapping(address => bool) public isDistrict;

  event DistrictCreated(
    address indexed district,
    District.Permission[] permissions,
    District.UserRole[] userRoles,
    bytes16 adminUserRoleId,
    address treasury,
    bytes ipfsData,
    uint timestamp
  );

  event PermissionsUpdated(
    address indexed district,
    District.Permission[] permissions,
    uint timestamp
  );

  event UserRolesUpdated(
    District.UserRole[] userRoles,
    bytes ipfsData,
    uint timestamp
  );

  event TreasuryUpdated(
    address district,
    address treasury,
    uint timestamp
  );

  event EmergencyUpdated(
    bytes32 moduleId,
    bool isEmergency,
    uint timestamp
  );

  event ProxyTargetsUpdated(
    ProxyFactory.ProxyTarget districtTarget,
    uint timestamp
  );


  modifier onlyDistrict() {
    require(isDistrict[msg.sender]);
    _;
  }


  /**
   * @dev Contract initialization
   * It is manually called instead of native contructor,
   * because this contract is used through a proxy.
   * This function cannot be called twice.
   *
   * Requirements:
   *
   * - `_proxyFactory` cannot be zero address
   * - `_districtTarget` cannot be empty
   * TODO: Needs implementation
   */
  function initialize(
    ProxyFactory _proxyFactory,
    ProxyFactory.ProxyTarget memory _districtTarget
  ) external {
    // Making sure owner of proxy is the same as the caller of `initialize`
    transferOwnership(msg.sender);
  }


  /**
   * @dev Creates a new {District}
   * It creates {DistrictAdminProxy} forwarding to {District} contract
   *
   * When creating DistrictAdminProxy contract it passes zero address as a district,
   * so it uses its own address as a district.
   *
   * It calls {initialize} on newly created district
   *
   * Emits an {DistrictCreated} event
   * TODO: Needs implementation
   */
  function createDistrict(
    District.Permission[] memory _permissions,
    District.UserRole[] memory _userRoles,
    bytes16 _adminUserRoleId,
    address _treasury,
    bytes memory _ipfsData
  ) external {
  }


  /**
   * @dev Returns true if a module is in emergency state
   * TODO: Needs implementation
   */
  function isEmergency(
    bytes32 _moduleId
  ) external view
  returns (bool _isEmergency) {
    return _isEmergency;
  }


  /**
   * @dev Updates emergency state for a module
   *
   * Only owner of {DistrictFactory} can call this function
   *
   * Emits an {EmergencyUpdated} event
   *
   * Requirements:
   *
   * - `_moduleId` cannot be empty
   * TODO: Needs implementation
   */
  function updateEmergency(
    bytes32 _moduleId,
    bool _isEmergency
  ) external onlyOwner {
  }


  /**
   * @dev Updates proxy target for {District} contract that new proxies will forward to.
   * It's meant to be called only by {targetUpdated}
   *
   * Emits an {ProxyTargetsUpdated} event
   * TODO: Needs implementation
   */
  function _updateProxyTargets(
    ProxyFactory.ProxyTarget memory _districtTarget
  ) internal {
  }


  /**
   * @dev This function is called automatically when proxy forwarding to this contract
   * update its target.
   * It should decode `_data` into arguments of {_updateProxyTargets} and call it
   * TODO: Needs implementation
   */
  function targetUpdated(
    ProxyFactory.ProxyTarget memory _newDistrictFactoryTarget,
    bytes memory _data
  ) external override onlySelf {
  }


  /**
   * TODO: Needs implementation
   */
  function firePermissionsUpdated(
    District.Permission[] memory _permissions
  ) external onlyDistrict {
  }


  /**
   * TODO: Needs implementation
   */
  function fireUserRolesUpdated(
    District.UserRole[] memory _userRoles,
    bytes memory _ipfsData
  ) external onlyDistrict {
  }


  /**
   * TODO: Needs implementation
   */
  function fireTreasuryUpdated(
    address treasury
  ) external onlyDistrict {
  }
}
