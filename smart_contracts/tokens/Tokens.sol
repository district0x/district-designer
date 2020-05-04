pragma solidity >=0.4.22 <0.7.0;

import "../district_designer/DistrictDesigner.sol";

contract Tokens {

  DistrictDesigner _;

  constructor() public {}

  event TokenCreatedEvent();

  function fireTokenCreatedEvent(
  ) public
  {}


}
