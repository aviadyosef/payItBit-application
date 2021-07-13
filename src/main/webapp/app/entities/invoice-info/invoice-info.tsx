import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { openFile, byteSize, Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntities } from './invoice-info.reducer';
import { IInvoiceInfo } from 'app/shared/model/invoice-info.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const InvoiceInfo = (props: RouteComponentProps<{ url: string }>) => {
  const dispatch = useAppDispatch();

  const invoiceInfoList = useAppSelector(state => state.invoiceInfo.entities);
  const loading = useAppSelector(state => state.invoiceInfo.loading);

  useEffect(() => {
    dispatch(getEntities({}));
  }, []);

  const handleSyncList = () => {
    dispatch(getEntities({}));
  };

  const { match } = props;

  return (
    <div>
      <h2 id="invoice-info-heading" data-cy="InvoiceInfoHeading">
        Invoice Infos
        <div className="d-flex justify-content-end">
          <Button className="mr-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} /> Refresh List
          </Button>
          <Link to={`${match.url}/new`} className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Create new Invoice Info
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {invoiceInfoList && invoiceInfoList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>ID</th>
                <th>Amount</th>
                <th>Invoice Number</th>
                <th>Currency</th>
                <th>Invoice Picture</th>
                <th>Agreement</th>
                <th>Service Description</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {invoiceInfoList.map((invoiceInfo, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`${match.url}/${invoiceInfo.id}`} color="link" size="sm">
                      {invoiceInfo.id}
                    </Button>
                  </td>
                  <td>{invoiceInfo.amount}</td>
                  <td>{invoiceInfo.invoiceNumber}</td>
                  <td>{invoiceInfo.currency}</td>
                  <td>
                    {invoiceInfo.invoicePicture ? (
                      <div>
                        {invoiceInfo.invoicePictureContentType ? (
                          <a onClick={openFile(invoiceInfo.invoicePictureContentType, invoiceInfo.invoicePicture)}>
                            <img
                              src={`data:${invoiceInfo.invoicePictureContentType};base64,${invoiceInfo.invoicePicture}`}
                              style={{ maxHeight: '30px' }}
                            />
                            &nbsp;
                          </a>
                        ) : null}
                        <span>
                          {invoiceInfo.invoicePictureContentType}, {byteSize(invoiceInfo.invoicePicture)}
                        </span>
                      </div>
                    ) : null}
                  </td>
                  <td>
                    {invoiceInfo.agreement ? (
                      <div>
                        {invoiceInfo.agreementContentType ? (
                          <a onClick={openFile(invoiceInfo.agreementContentType, invoiceInfo.agreement)}>
                            <img
                              src={`data:${invoiceInfo.agreementContentType};base64,${invoiceInfo.agreement}`}
                              style={{ maxHeight: '30px' }}
                            />
                            &nbsp;
                          </a>
                        ) : null}
                        <span>
                          {invoiceInfo.agreementContentType}, {byteSize(invoiceInfo.agreement)}
                        </span>
                      </div>
                    ) : null}
                  </td>
                  <td>{invoiceInfo.serviceDescription}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${invoiceInfo.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${invoiceInfo.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${invoiceInfo.id}/delete`} color="danger" size="sm" data-cy="entityDeleteButton">
                        <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && <div className="alert alert-warning">No Invoice Infos found</div>
        )}
      </div>
    </div>
  );
};

export default InvoiceInfo;
