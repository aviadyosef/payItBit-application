import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntity } from './invoice.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const InvoiceDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const invoiceEntity = useAppSelector(state => state.invoice.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="invoiceDetailsHeading">Invoice</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{invoiceEntity.id}</dd>
          <dt>
            <span id="seqNo">Seq No</span>
          </dt>
          <dd>{invoiceEntity.seqNo}</dd>
          <dt>
            <span id="createdOn">Created On</span>
          </dt>
          <dd>{invoiceEntity.createdOn ? <TextFormat value={invoiceEntity.createdOn} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="createdBy">Created By</span>
          </dt>
          <dd>{invoiceEntity.createdBy}</dd>
          <dt>
            <span id="action">Action</span>
          </dt>
          <dd>{invoiceEntity.action}</dd>
          <dt>Invoice Info</dt>
          <dd>{invoiceEntity.invoiceInfo ? invoiceEntity.invoiceInfo.id : ''}</dd>
          <dt>Supplier Info</dt>
          <dd>{invoiceEntity.supplierInfo ? invoiceEntity.supplierInfo.id : ''}</dd>
          <dt>Bank Info</dt>
          <dd>{invoiceEntity.bankInfo ? invoiceEntity.bankInfo.id : ''}</dd>
          <dt>Invoice</dt>
          <dd>{invoiceEntity.invoice ? invoiceEntity.invoice.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/invoice" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/invoice/${invoiceEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

export default InvoiceDetail;
