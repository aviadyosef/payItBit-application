import React from 'react';
import MenuItem from 'app/shared/layout/menus/menu-item';

import { NavDropdown } from './menu-components';

export const TransactionsMenu = props => (
  <NavDropdown name="TRANSACTIONS" id="transaction-menu" data-cy="transaction" style={{ maxHeight: '80vh', overflow: 'auto' }}>
    <>{/* to avoid warnings when empty */}</>
    <MenuItem icon="bolt" to="/transaction">
      BTC TRANSACTION
    </MenuItem>
    <MenuItem icon="bolt" to="/transaction-all">
      USDT TRANSACTION
    </MenuItem>
    {/* jhipster-needle-add-entity-to-menu - JHipster will add Invoices to the menu here */}
  </NavDropdown>
);
