(ns server-tests.runner
  (:require
    [cljs.nodejs :as nodejs]
    [cljs.test :refer [run-tests]]
    [doo.runner :refer-macros [doo-tests]]
    [server-tests.district-designer.spec-tests]
    [server-tests.marketplace.spec-tests]
    [server-tests.tcr.spec-tests]
    [server-tests.tokens.spec-tests]
    [server-tests.users.spec-tests]))

(nodejs/enable-util-print!)

(run-tests
  'server-tests.district-designer.spec-tests
  'server-tests.marketplace.spec-tests
  'server-tests.tcr.spec-tests
  'server-tests.tokens.spec-tests
  'server-tests.users.spec-tests)