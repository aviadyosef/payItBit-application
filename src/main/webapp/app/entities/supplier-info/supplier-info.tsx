import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntities } from './supplier-info.reducer';
import { ISupplierInfo } from 'app/shared/model/supplier-info.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const SupplierInfo = (props: RouteComponentProps<{ url: string }>) => {
  const dispatch = useAppDispatch();

  const supplierInfoList = useAppSelector(state => state.supplierInfo.entities);
  const loading = useAppSelector(state => state.supplierInfo.loading);

  useEffect(() => {
    dispatch(getEntities({}));
  }, []);

  const handleSyncList = () => {
    dispatch(getEntities({}));
  };

  const { match } = props;

  return (
    <div>
      <h2 id="supplier-info-heading" data-cy="SupplierInfoHeading">
        Supplier Infos
        <div className="d-flex justify-content-end">
          <Button className="mr-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} /> Refresh List
          </Button>
          <Link to={`${match.url}/new`} className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Create new Supplier Info
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {supplierInfoList && supplierInfoList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>ID</th>
                <th>Company Name</th>
                <th>Number</th>
                <th>Address</th>
                <th>Website</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {supplierInfoList.map((supplierInfo, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`${match.url}/${supplierInfo.id}`} color="link" size="sm">
                      {supplierInfo.id}
                    </Button>
                  </td>
                  <td>{supplierInfo.companyName}</td>
                  <td>{supplierInfo.number}</td>
                  <td>{supplierInfo.address}</td>
                  <td>{supplierInfo.website}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${supplierInfo.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${supplierInfo.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`${match.url}/${supplierInfo.id}/delete`}
                        color="danger"
                        size="sm"
                        data-cy="entityDeleteButton"
                      >
                        <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && <div className="alert alert-warning">No Supplier Infos found</div>
        )}
      </div>
    </div>
  );
};

export default SupplierInfo;
