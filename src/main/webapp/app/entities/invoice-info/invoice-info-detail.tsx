import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { openFile, byteSize } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntity } from './invoice-info.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const InvoiceInfoDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const invoiceInfoEntity = useAppSelector(state => state.invoiceInfo.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="invoiceInfoDetailsHeading">InvoiceInfo</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{invoiceInfoEntity.id}</dd>
          <dt>
            <span id="amount">Amount</span>
          </dt>
          <dd>{invoiceInfoEntity.amount}</dd>
          <dt>
            <span id="invoiceNumber">Invoice Number</span>
          </dt>
          <dd>{invoiceInfoEntity.invoiceNumber}</dd>
          <dt>
            <span id="currency">Currency</span>
          </dt>
          <dd>{invoiceInfoEntity.currency}</dd>
          <dt>
            <span id="invoicePicture">Invoice Picture</span>
          </dt>
          <dd>
            {invoiceInfoEntity.invoicePicture ? (
              <div>
                {invoiceInfoEntity.invoicePictureContentType ? (
                  <a onClick={openFile(invoiceInfoEntity.invoicePictureContentType, invoiceInfoEntity.invoicePicture)}>
                    <img
                      src={`data:${invoiceInfoEntity.invoicePictureContentType};base64,${invoiceInfoEntity.invoicePicture}`}
                      style={{ maxHeight: '30px' }}
                    />
                  </a>
                ) : null}
                <span>
                  {invoiceInfoEntity.invoicePictureContentType}, {byteSize(invoiceInfoEntity.invoicePicture)}
                </span>
              </div>
            ) : null}
          </dd>
          <dt>
            <span id="agreement">Agreement</span>
          </dt>
          <dd>
            {invoiceInfoEntity.agreement ? (
              <div>
                {invoiceInfoEntity.agreementContentType ? (
                  <a onClick={openFile(invoiceInfoEntity.agreementContentType, invoiceInfoEntity.agreement)}>
                    <img
                      src={`data:${invoiceInfoEntity.agreementContentType};base64,${invoiceInfoEntity.agreement}`}
                      style={{ maxHeight: '30px' }}
                    />
                  </a>
                ) : null}
                <span>
                  {invoiceInfoEntity.agreementContentType}, {byteSize(invoiceInfoEntity.agreement)}
                </span>
              </div>
            ) : null}
          </dd>
          <dt>
            <span id="serviceDescription">Service Description</span>
          </dt>
          <dd>{invoiceInfoEntity.serviceDescription}</dd>
        </dl>
        <Button tag={Link} to="/invoice-info" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/invoice-info/${invoiceInfoEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

export default InvoiceInfoDetail;
