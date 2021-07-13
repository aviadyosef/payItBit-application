import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import {} from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntity } from './wallet.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const WalletDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const walletEntity = useAppSelector(state => state.wallet.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="walletDetailsHeading">Wallet</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{walletEntity.id}</dd>
          <dt>
            <span id="readableName">Readable Name</span>
          </dt>
          <dd>{walletEntity.readableName}</dd>
          <dt>
            <span id="walletType">Wallet Type</span>
          </dt>
          <dd>{walletEntity.walletType}</dd>
          <dt>Wallet</dt>
          <dd>{walletEntity.wallet ? walletEntity.wallet.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/wallet" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/wallet/${walletEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

export default WalletDetail;
