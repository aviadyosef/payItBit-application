import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import {} from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntity } from './supplier-info.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const SupplierInfoDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const supplierInfoEntity = useAppSelector(state => state.supplierInfo.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="supplierInfoDetailsHeading">SupplierInfo</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{supplierInfoEntity.id}</dd>
          <dt>
            <span id="companyName">Company Name</span>
          </dt>
          <dd>{supplierInfoEntity.companyName}</dd>
          <dt>
            <span id="number">Number</span>
          </dt>
          <dd>{supplierInfoEntity.number}</dd>
          <dt>
            <span id="address">Address</span>
          </dt>
          <dd>{supplierInfoEntity.address}</dd>
          <dt>
            <span id="website">Website</span>
          </dt>
          <dd>{supplierInfoEntity.website}</dd>
        </dl>
        <Button tag={Link} to="/supplier-info" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/supplier-info/${supplierInfoEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

export default SupplierInfoDetail;
