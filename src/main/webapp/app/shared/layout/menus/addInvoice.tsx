import React from 'react';
import MenuItem from 'app/shared/layout/menus/menu-item';

import { NavDropdown } from './menu-components';

export const AddInvoiceBtn = props => (
  <li className="nav-item menu-padding-bottom">
    <a className="btn font-face-raleway-green btn-light" href="http://localhost:9000/invoice/new">
      + ADD INVOICE
    </a>
  </li>
);
