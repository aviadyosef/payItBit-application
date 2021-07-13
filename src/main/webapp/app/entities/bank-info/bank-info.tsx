import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntities } from './bank-info.reducer';
import { IBankInfo } from 'app/shared/model/bank-info.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const BankInfo = (props: RouteComponentProps<{ url: string }>) => {
  const dispatch = useAppDispatch();

  const bankInfoList = useAppSelector(state => state.bankInfo.entities);
  const loading = useAppSelector(state => state.bankInfo.loading);

  useEffect(() => {
    dispatch(getEntities({}));
  }, []);

  const handleSyncList = () => {
    dispatch(getEntities({}));
  };

  const { match } = props;

  return (
    <div>
      <h2 id="bank-info-heading" data-cy="BankInfoHeading">
        Bank Infos
        <div className="d-flex justify-content-end">
          <Button className="mr-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} /> Refresh List
          </Button>
          <Link to={`${match.url}/new`} className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Create new Bank Info
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {bankInfoList && bankInfoList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>ID</th>
                <th>Account Holder Name</th>
                <th>Account Number</th>
                <th>Iban Number</th>
                <th>Swift Code</th>
                <th>Bank Address</th>
                <th>Country</th>
                <th>Reference No</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {bankInfoList.map((bankInfo, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`${match.url}/${bankInfo.id}`} color="link" size="sm">
                      {bankInfo.id}
                    </Button>
                  </td>
                  <td>{bankInfo.accountHolderName}</td>
                  <td>{bankInfo.accountNumber}</td>
                  <td>{bankInfo.ibanNumber}</td>
                  <td>{bankInfo.swiftCode}</td>
                  <td>{bankInfo.bankAddress}</td>
                  <td>{bankInfo.country}</td>
                  <td>{bankInfo.referenceNo}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${bankInfo.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${bankInfo.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${bankInfo.id}/delete`} color="danger" size="sm" data-cy="entityDeleteButton">
                        <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && <div className="alert alert-warning">No Bank Infos found</div>
        )}
      </div>
    </div>
  );
};

export default BankInfo;
