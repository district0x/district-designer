pragma solidity >=0.4.22 <0.7.0;
pragma experimental ABIEncoderV2;

import "@openzeppelin/contracts/utils/EnumerableSet.sol";

contract DistrictDesigner {
  using EnumerableSet for EnumerableSet.AddressSet;

  uint public constant version = 1;

  event PermissionsInitializedEvent(
    bytes16 indexed district,
    bytes32[] permissionIds,
    bytes16[][] permissionUserRoles,
    bytes16[] userRoles,
    address[][] userRoleaddresses,
    bytes16 adminUserRole
  );

  event PermissionsUpdatedEvent(
    bytes16 indexed district,
    bytes32[] permissionIds,
    bytes16[][] userRoles
  );

  event UserRolesUpdatedEvent(
    bytes16 indexed district,
    bytes16[] userRoles,
    address[][] addresses,
    bytes16[] removeUserRoles
  );

  event DistrictsEmergencyEvent(
    bytes16[] districts,
    bool isEmergency
  );

  function initialize(
    bytes32[] memory _DDPermissionIds,
    bytes16[][] memory _DDPermissionUserRoles,
    bytes16[] memory _DDUserRoles,
    address[][] memory _DDUserRoleaddresses,
    bytes16 _DDAdminUserRole)
  public {}


  function isAllowed(
    bytes16 _district,
    bytes32 _permissionId,
    address _address
  ) public view
    returns (bool) {
    return true;
  }


  function initializePermissions(
    bytes16 _district,
    bytes32[] memory _permissionIds,
    bytes16[][] memory _permissionUserRoles,
    bytes16[] memory _userRoles,
    address[][] memory _userRoleaddresses,
    bytes16 _adminUserRole
  ) public
  {}


  function updatePermissions(
    bytes16 _district,
    bytes32[] memory _permissionIds,
    bytes16[][] memory _userRoles
  ) public
  {}


  function updatePermission(
    bytes16 _district,
    bytes32 _permissionId,
    bytes16[] memory _userRoles
  ) public
  {}


  function updateUserRoles(
    bytes16 _district,
    bytes16[] memory _userRoles,
    address[][] memory _addresses,
    bytes16[] memory _removeUserRoles
  ) public
  {}


  function updateUserRole(
    bytes16 _district,
    bytes16 _userRole,
    address[] memory _addresses
  ) public
  {}


  function adminUserRole(
    bytes16 _district
  ) public view
  returns (bytes16 _userRole) {
    return _userRole;
  }


  function districtTreasury(
    bytes16 _district
  ) public view
  returns (address _treasury) {
    return _treasury;
  }


  function setDistrictTreasury(
    bytes16 _district,
    address _treasury
  ) public
  {}


  function isDistrictEmergency(
    bytes16 _district
  ) public view
  returns (bool _isEmergency) {
    return _isEmergency;
  }


  function setDistrictEmergency(
    bytes16 _district,
    address _isEmergency
  ) public
  {}

}
