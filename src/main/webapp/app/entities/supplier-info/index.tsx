import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import SupplierInfo from './supplier-info';
import SupplierInfoDetail from './supplier-info-detail';
import SupplierInfoUpdate from './supplier-info-update';
import SupplierInfoDeleteDialog from './supplier-info-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={SupplierInfoUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={SupplierInfoUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={SupplierInfoDetail} />
      <ErrorBoundaryRoute path={match.url} component={SupplierInfo} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={SupplierInfoDeleteDialog} />
  </>
);

export default Routes;
