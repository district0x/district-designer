pragma solidity >=0.4.22 <0.7.0;
pragma experimental ABIEncoderV2;

import "../district_designer/DistrictDesigner.sol";
import "./TCR.sol";

contract TCRFactory {
  constructor(DistrictDesigner _districtDesigner) public {}

  function createTCR(
    bytes16 _district,
    bytes16 _tcr,
    address _votingToken,
    TCR.TCRType _tcrType,
    TCR.RegistryEntryRepresentation memory _regEntryRepr,
    TCR.PermissionIds memory _permissionIds,
    TCR.Parameters memory _regParameters,
    TCR.Parameters memory _paramChangeParameters
  ) public
  {}
}
