pragma solidity >=0.4.22 <0.7.0;

contract DistrictDesigner {

  event PermissionsInitializedEvent(
    bytes16 indexed _district,
    bytes32[] _permissionIds,
    bytes16[][] _permissionUserRoles,
    bytes16[] _userRoles,
    address[][] _userRoleaddresses,
    bytes16 _adminUserRole
  );

  event PermissionsUpdatedEvent(
    bytes16 indexed _district,
    bytes16 _district,
    bytes32[] _permissionIds,
    bytes16[][] _userRoles
  );

  event UserRolesUpdatedEvent(
    bytes16 indexed _district,
    bytes16[] _userRoles,
    address[][] _addresses,
    bytes16[] _removeUserRoles
  );

  event DistrictsEmergencyEvent(
    bytes16[] _districts,
    bool _isEmergency
  );

  constructor(
    bytes32[] _DDPermissionIds,
    bytes16[][] _DDPermissionUserRoles,
    bytes16[] _DDUserRoles,
    address[][] _DDUserRoleaddresses,
    bytes16 _DDAdminUserRole)
  public {
  }

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
    bytes32[] _permissionIds,
    bytes16[][] _permissionUserRoles,
    bytes16[] _userRoles,
    address[][] _userRoleaddresses,
    bytes16 _adminUserRole
  ) public
  {}

  function updatePermissions(
    bytes16 _district,
    bytes32[] _permissionIds,
    bytes16[][] _userRoles
  ) public
  {}

  function updateUserRoles(
    bytes16 _district,
    bytes16[] _userRoles,
    address[][] _addresses,
    bytes16[] _removeUserRoles
  ) public
  {}

  function adminUserRole(
    bytes16 _district
  ) public view
  returns (bytes16 _userRole) {
    return;
  }

  function districtTreasury(
    bytes16 _district
  ) public view
  returns (address _treasury) {
    return;
  }

  function setDistrictTreasury(
    bytes16 _district,
    address _treasury
  ) public
  {}

  function isDistrictEmergency(
    bytes16 _district
  ) public view
  returns (bool) {
    return;
  }

  function setDistrictEmergency(
    bytes16 _district,
    address _isEmergency
  ) public
  {}

  function setDistrictsEmergency(
    bytes16[] _district,
    address _isEmergency
  ) public
  {}
}
