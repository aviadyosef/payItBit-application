import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import {} from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntity } from './transaction.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const TransactionDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const transactionEntity = useAppSelector(state => state.transaction.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="transactionDetailsHeading">Transaction</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{transactionEntity.id}</dd>
          <dt>
            <span id="title">Title</span>
          </dt>
          <dd>{transactionEntity.title}</dd>
          <dt>
            <span id="createdOn">Created On</span>
          </dt>
          <dd>{transactionEntity.createdOn}</dd>
          <dt>
            <span id="senderAddress">Sender Address</span>
          </dt>
          <dd>{transactionEntity.senderAddress}</dd>
          <dt>
            <span id="receiverAddress">Receiver Address</span>
          </dt>
          <dd>{transactionEntity.receiverAddress}</dd>
          <dt>
            <span id="amount">Amount</span>
          </dt>
          <dd>{transactionEntity.amount}</dd>
          <dt>
            <span id="description">Description</span>
          </dt>
          <dd>{transactionEntity.description}</dd>
          <dt>Transaction</dt>
          <dd>{transactionEntity.transaction ? transactionEntity.transaction.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/transaction" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/transaction/${transactionEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

export default TransactionDetail;
