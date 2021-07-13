import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import InvoiceInfo from './invoice-info';
import InvoiceInfoDetail from './invoice-info-detail';
import InvoiceInfoUpdate from './invoice-info-update';
import InvoiceInfoDeleteDialog from './invoice-info-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={InvoiceInfoUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={InvoiceInfoUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={InvoiceInfoDetail} />
      <ErrorBoundaryRoute path={match.url} component={InvoiceInfo} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={InvoiceInfoDeleteDialog} />
  </>
);

export default Routes;
