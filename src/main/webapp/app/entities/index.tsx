import React from 'react';
import { Switch } from 'react-router-dom';

// eslint-disable-next-line @typescript-eslint/no-unused-vars
import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import UserInfo from './user-info';
import Transaction from './transaction';
import Invoice from './invoice';
import Wallet from './wallet';
import InvoiceInfo from './invoice-info';
import SupplierInfo from './supplier-info';
import BankInfo from './bank-info';
/* jhipster-needle-add-route-import - JHipster will add routes here */

const Routes = ({ match }) => (
  <div>
    <Switch>
      {/* prettier-ignore */}
      <ErrorBoundaryRoute path={`${match.url}user-info`} component={UserInfo} />
      <ErrorBoundaryRoute path={`${match.url}transaction`} component={Transaction} />
      <ErrorBoundaryRoute path={`${match.url}invoice`} component={Invoice} />
      <ErrorBoundaryRoute path={`${match.url}wallet`} component={Wallet} />
      <ErrorBoundaryRoute path={`${match.url}invoice-info`} component={InvoiceInfo} />
      <ErrorBoundaryRoute path={`${match.url}supplier-info`} component={SupplierInfo} />
      <ErrorBoundaryRoute path={`${match.url}bank-info`} component={BankInfo} />
      {/* jhipster-needle-add-route-path - JHipster will add routes here */}
    </Switch>
  </div>
);

export default Routes;
