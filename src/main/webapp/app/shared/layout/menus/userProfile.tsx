import React from 'react';
import MenuItem from 'app/shared/layout/menus/menu-item';

import { NavDropdown } from './menu-components';

export const UserProfile = props => (
  // <li className="nav-item dropdown menu-padding-bottom">
  //   <a className="nav-link profile-icon" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
  //     <img src="https://payitbit.co/myAssets/img/profile.png" alt="PAYITBIT" className="img-fluid"/>
  //   </a>
  // </li>

  // <li className="nav-item menu-padding-bottom">
  //   <a className="nav-link profile-icon" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
  //     <img src="https://payitbit.co/myAssets/img/profile.png" alt="PAYITBIT" className="img-fluid-user-profile"/>
  //   </a>
  //
  //   <div className="dropdown-menu" aria-labelledby="navbarDropdown">
  //     <a className="dropdown-item" href="https://payitbit.co/myProfile"><i className="fas fa-user"></i>&nbsp;My Profile</a>
  //       <a className="dropdown-item" href="#" data-toggle="modal" data-target="#logout">
  //         <i className="fas fa-power-off text-danger"></i>&nbsp;Logout
  //       </a>
  //   </div>
  // </li>
  <NavDropdown name="PROFILE" id="userprofile-menu" data-cy="profile" style={{ maxHeight: '80vh', overflow: 'auto' }}>
    <>{/* to avoid warnings when empty */}</>
    <MenuItem icon="user" to="/settings">
      MY PROFILE
    </MenuItem>
    <MenuItem icon="portal-exit" to="/logout">
      LOGOUT
    </MenuItem>
    {/* jhipster-needle-add-entity-to-menu - JHipster will add Invoices to the menu here */}
  </NavDropdown>
);
