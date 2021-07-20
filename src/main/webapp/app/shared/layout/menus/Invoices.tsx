import React from 'react';
import MenuItem from 'app/shared/layout/menus/menu-item';

import { NavDropdown } from './menu-components';

export const InvoicesMenu = props => (
  <NavDropdown name="INVOICES" id="invoice-menu" data-cy="invoice" style={{ maxHeight: '80vh', overflow: 'auto' }}>
    <>{/* to avoid warnings when empty */}</>
    <MenuItem icon="bolt" to="/invoice/new">
      + ADD INVOICES
    </MenuItem>
    <MenuItem icon="bolt" to="/invoice-all">
      ALL INVOICES
    </MenuItem>
    <MenuItem icon="bolt" to="/invoice-pending">
      PENDING INVOICES
    </MenuItem>
    <MenuItem icon="bolt" to="/invoice-approved">
      APPROVED INVOICES
    </MenuItem>
    {/* jhipster-needle-add-entity-to-menu - JHipster will add Invoices to the menu here */}
  </NavDropdown>
);
