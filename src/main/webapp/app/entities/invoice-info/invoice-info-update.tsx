import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, ValidatedField, ValidatedForm, ValidatedBlobField } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntity, updateEntity, createEntity, reset } from './invoice-info.reducer';
import { IInvoiceInfo } from 'app/shared/model/invoice-info.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const InvoiceInfoUpdate = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const invoiceInfoEntity = useAppSelector(state => state.invoiceInfo.entity);
  const loading = useAppSelector(state => state.invoiceInfo.loading);
  const updating = useAppSelector(state => state.invoiceInfo.updating);
  const updateSuccess = useAppSelector(state => state.invoiceInfo.updateSuccess);

  const handleClose = () => {
    props.history.push('/invoice-info');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(props.match.params.id));
    }
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    const entity = {
      ...invoiceInfoEntity,
      ...values,
    };

    if (isNew) {
      dispatch(createEntity(entity));
    } else {
      dispatch(updateEntity(entity));
    }
  };

  const defaultValues = () =>
    isNew
      ? {}
      : {
          ...invoiceInfoEntity,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="payItBitApplicationApp.invoiceInfo.home.createOrEditLabel" data-cy="InvoiceInfoCreateUpdateHeading">
            Create or edit a InvoiceInfo
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? <ValidatedField name="id" required readOnly id="invoice-info-id" label="ID" validate={{ required: true }} /> : null}
              <ValidatedField label="Amount" id="invoice-info-amount" name="amount" data-cy="amount" type="text" />
              <ValidatedField
                label="Invoice Number"
                id="invoice-info-invoiceNumber"
                name="invoiceNumber"
                data-cy="invoiceNumber"
                type="text"
              />
              <ValidatedField label="Currency" id="invoice-info-currency" name="currency" data-cy="currency" type="text" />
              <ValidatedBlobField
                label="Invoice Picture"
                id="invoice-info-invoicePicture"
                name="invoicePicture"
                data-cy="invoicePicture"
                isImage
                accept="image/*"
              />
              <ValidatedBlobField
                label="Agreement"
                id="invoice-info-agreement"
                name="agreement"
                data-cy="agreement"
                isImage
                accept="image/*"
              />
              <ValidatedField
                label="Service Description"
                id="invoice-info-serviceDescription"
                name="serviceDescription"
                data-cy="serviceDescription"
                type="text"
              />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/invoice-info" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">Back</span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" data-cy="entityCreateSaveButton" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp; Save
              </Button>
            </ValidatedForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

export default InvoiceInfoUpdate;
