import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import {} from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntity } from './bank-info.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const BankInfoDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const bankInfoEntity = useAppSelector(state => state.bankInfo.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="bankInfoDetailsHeading">BankInfo</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{bankInfoEntity.id}</dd>
          <dt>
            <span id="accountHolderName">Account Holder Name</span>
          </dt>
          <dd>{bankInfoEntity.accountHolderName}</dd>
          <dt>
            <span id="accountNumber">Account Number</span>
          </dt>
          <dd>{bankInfoEntity.accountNumber}</dd>
          <dt>
            <span id="ibanNumber">Iban Number</span>
          </dt>
          <dd>{bankInfoEntity.ibanNumber}</dd>
          <dt>
            <span id="swiftCode">Swift Code</span>
          </dt>
          <dd>{bankInfoEntity.swiftCode}</dd>
          <dt>
            <span id="bankAddress">Bank Address</span>
          </dt>
          <dd>{bankInfoEntity.bankAddress}</dd>
          <dt>
            <span id="country">Country</span>
          </dt>
          <dd>{bankInfoEntity.country}</dd>
          <dt>
            <span id="referenceNo">Reference No</span>
          </dt>
          <dd>{bankInfoEntity.referenceNo}</dd>
        </dl>
        <Button tag={Link} to="/bank-info" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/bank-info/${bankInfoEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

export default BankInfoDetail;
