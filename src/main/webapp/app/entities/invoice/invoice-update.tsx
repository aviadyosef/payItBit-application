import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { ICustomer } from 'app/shared/model/customer.model';
import { getEntities as getCustomers } from 'app/entities/customer/customer.reducer';
import { getEntity, updateEntity, createEntity, reset } from './invoice.reducer';
import { IInvoice } from 'app/shared/model/invoice.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const InvoiceUpdate = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const customers = useAppSelector(state => state.customer.entities);
  const invoiceEntity = useAppSelector(state => state.invoice.entity);
  const loading = useAppSelector(state => state.invoice.loading);
  const updating = useAppSelector(state => state.invoice.updating);
  const updateSuccess = useAppSelector(state => state.invoice.updateSuccess);

  const handleClose = () => {
    props.history.push('/invoice' + props.location.search);
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(props.match.params.id));
    }

    dispatch(getCustomers({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    const entity = {
      ...invoiceEntity,
      ...values,
      invoice: customers.find(it => it.id.toString() === values.invoiceId.toString()),
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
          ...invoiceEntity,
          invoiceId: invoiceEntity?.invoice?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="payItBitApplicationApp.invoice.home.createOrEditLabel" data-cy="InvoiceCreateUpdateHeading">
            Create or edit a Invoice
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? <ValidatedField name="id" required readOnly id="invoice-id" label="ID" validate={{ required: true }} /> : null}
              <ValidatedField label="Seq No" id="invoice-seqNo" name="seqNo" data-cy="seqNo" type="text" />
              <ValidatedField label="Created On" id="invoice-createdOn" name="createdOn" data-cy="createdOn" type="text" />
              <ValidatedField label="Invoice Number" id="invoice-invoiceNumber" name="invoiceNumber" data-cy="invoiceNumber" type="text" />
              <ValidatedField label="Company Name" id="invoice-companyName" name="companyName" data-cy="companyName" type="text" />
              <ValidatedField label="Created By" id="invoice-createdBy" name="createdBy" data-cy="createdBy" type="text" />
              <ValidatedField label="Action" id="invoice-action" name="action" data-cy="action" type="text" />
              <ValidatedField id="invoice-invoice" name="invoiceId" data-cy="invoice" label="Invoice" type="select">
                <option value="" key="0" />
                {customers
                  ? customers.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/invoice" replace color="info">
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

export default InvoiceUpdate;
