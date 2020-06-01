pragma solidity >=0.4.22 <0.7.0;
pragma experimental ABIEncoderV2;

import "@openzeppelin/contracts/utils/EnumerableSet.sol";
import "@openzeppelin/contracts/access/Ownable.sol";

contract DistrictDesigner is Ownable {
  using EnumerableSet for EnumerableSet.AddressSet;

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

  event DistrictInitialized(
    bytes16 indexed district,
    Permission[] permissions,
    UserRole[] userRoles,
    bytes16 adminUserRoleId,
    address treasury,
    bytes ipfsData,
    uint timestamp
  );

  event PermissionsUpdated(
    bytes16 indexed district,
    Permission[] permissions,
    uint timestamp
  );

  event UserRolesUpdated(
    bytes16 indexed district,
    UserRole[] userRoles,
    bytes ipfsData,
    uint timestamp
  );

  event DistrictTreasuryUpdated(
    bytes16 district,
    address treasury,
    uint timestamp
  );

  event EmergencyUpdated(
    bytes32 moduleId,
    bool isEmergency,
    uint timestamp
  );


  function isAllowed(
    bytes16 _district,
    bytes32 _permission,
    address _address
  ) public view
    returns (bool) {
    return true;
  }


  function initializeDistrict(
    bytes16 _district,
    Permission[] memory _permissions,
    UserRole[] memory _userRoles,
    bytes16 _adminUserRoleId,
    address _treasury,
    bytes memory _ipfsData
  ) public {
  }


  function updatePermissions(
    bytes16 _district,
    Permission[] memory _permissions
  ) public {
  }


  function updateUserRoles(
    bytes16 _district,
    UserRole[] memory _userRoles,
    bytes memory _ipfsData
  ) public {
  }


  function adminUserRole(
    bytes16 _district
  ) public view
  returns (UserRole memory _userRole) {
    return _userRole;
  }


  function adminUserRoleHasAddress(
    bytes16 _district,
    address _address
  ) public view
  returns (bool) {
    return true;
  }


  function userRoleHasAddress(
    bytes16 _userRoleId,
    address _address
  ) public view
  returns (bool) {
    return true;
  }


  function userRolesHaveAddress(
    bytes16[] memory _userRoleIds,
    address _address
  ) public view
  returns (bool) {
    return true;
  }


  function districtTreasury(
    bytes16 _district
  ) public view
  returns (address _treasury) {
    return _treasury;
  }


  function updateDistrictTreasury(
    bytes16 _district,
    address _treasury
  ) public {
  }


  function isEmergency(
    bytes32 _moduleId
  ) public view
  returns (bool _isEmergency) {
    return _isEmergency;
  }


  function updateEmergency(
    bytes32 _moduleId,
    address _isEmergency
  ) public onlyOwner {
  }

}
