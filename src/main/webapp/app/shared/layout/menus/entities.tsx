import React from 'react';
import MenuItem from 'app/shared/layout/menus/menu-item';

import { NavDropdown } from './menu-components';

export const EntitiesMenu = props => (
  <NavDropdown icon="th-list" name="Entities" id="entity-menu" data-cy="entity" style={{ maxHeight: '80vh', overflow: 'auto' }}>
    <>{/* to avoid warnings when empty */}</>
    <MenuItem icon="asterisk" to="/user-info">
      User Info
    </MenuItem>
    <MenuItem icon="asterisk" to="/transaction">
      Transaction
    </MenuItem>
    <MenuItem icon="asterisk" to="/invoice">
      Invoice
    </MenuItem>
    <MenuItem icon="asterisk" to="/wallet">
      Wallet
    </MenuItem>
    <MenuItem icon="asterisk" to="/invoice-info">
      Invoice Info
    </MenuItem>
    <MenuItem icon="asterisk" to="/supplier-info">
      Supplier Info
    </MenuItem>
    <MenuItem icon="asterisk" to="/bank-info">
      Bank Info
    </MenuItem>
    {/* jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here */}
  </NavDropdown>
);
