pragma solidity >=0.4.22 <0.7.0;
pragma experimental ABIEncoderV2;

import "@openzeppelin/contracts/utils/EnumerableSet.sol";
import "@openzeppelin/contracts/access/Ownable.sol";

contract DistrictDesigner is Ownable {
  using EnumerableSet for EnumerableSet.AddressSet;

  uint public constant version = 1;

  event DistrictInitializedEvent(
    bytes16 indexed district,
    bytes32[] permissionIds,
    bytes16[][] permissionUserRoles,
    bytes16[] userRoles,
    address[][] userRoleAddresses,
    bytes16 adminUserRole,
    address treasury,
    bytes ipfsData
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
    bytes16[] removeUserRoles,
    bytes ipfsData
  );

  event DistrictTreasuryChangedEvent(
    bytes16 district,
    address treasury
  );

  event EmergencyChangedEvent(
    bytes32 moduleId,
    bool isEmergency
  );


  function initialize(
  ) public {
    transferOwnership(msg.sender);
  }


  function isAllowed(
    bytes16 _district,
    bytes32 _permissionId,
    address _address
  ) public view
    returns (bool) {
    return true;
  }


  function initializeDistrict(
    bytes16 _district,
    bytes32[] memory _permissionIds,
    bytes16[][] memory _permissionUserRoles,
    bytes16[] memory _userRoles,
    address[][] memory _userRoleAddresses,
    bytes16 _adminUserRole,
    address _treasury,
    bytes memory _ipfsData
  ) public {
  }


  function updatePermissions(
    bytes16 _district,
    bytes32[] memory _permissionIds,
    bytes16[][] memory _userRoles
  ) public {
  }


  function updateUserRoles(
    bytes16 _district,
    bytes16[] memory _userRoles,
    address[][] memory _addresses,
    bytes16[] memory _removeUserRoles,
    bytes memory _ipfsData
  ) public {
  }


  function adminUserRole(
    bytes16 _district
  ) public view
  returns (bytes16 _userRole) {
    return _userRole;
  }


  function adminUserRoleHasAddress(
    bytes16 _district,
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


  function setDistrictTreasury(
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


  function setEmergency(
    bytes32 _moduleId,
    address _isEmergency
  ) public onlyOwner {
  }

}
