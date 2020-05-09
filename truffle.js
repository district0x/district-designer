'use strict';

const DISTRICT_DESIGNER_ENV = process.env.DISTRICT_DESIGNER_ENV || "dev";
require('dotenv').config()  // Store environment-specific variable from '.env' to process.env

module.exports = {
  env: DISTRICT_DESIGNER_ENV,
  contracts_build_directory: __dirname + '/resources/public/contracts/build/',
  networks: {
    "ganache": {
      host: 'localhost',
      port: 8545,
      gas: 6e6, // gas limit
      gasPrice: 20e9, // 20 gwei, default for ganache
      network_id: '*'
    }
  },
  compilers: {
       solc: {
         version: "^0.6.0"
       }
    }
};