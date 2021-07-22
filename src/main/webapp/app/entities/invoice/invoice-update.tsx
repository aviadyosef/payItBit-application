import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IInvoiceInfo } from 'app/shared/model/invoice-info.model';
import { getEntities as getInvoiceInfos } from 'app/entities/invoice-info/invoice-info.reducer';
import { ISupplierInfo } from 'app/shared/model/supplier-info.model';
import { getEntities as getSupplierInfos } from 'app/entities/supplier-info/supplier-info.reducer';
import { IBankInfo } from 'app/shared/model/bank-info.model';
import { getEntities as getBankInfos } from 'app/entities/bank-info/bank-info.reducer';
import { IUserInfo } from 'app/shared/model/user-info.model';
import { getEntities as getUserInfos } from 'app/entities/user-info/user-info.reducer';
import { getEntity, updateEntity, createEntity, reset } from './invoice.reducer';
import { IInvoice } from 'app/shared/model/invoice.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';
import { CreateOrEditInvoice } from 'app/entities/invoice/create-or-edit-invoice';

export const InvoiceUpdate = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const invoiceInfos = useAppSelector(state => state.invoiceInfo.entities);
  const supplierInfos = useAppSelector(state => state.supplierInfo.entities);
  const bankInfos = useAppSelector(state => state.bankInfo.entities);
  const userInfos = useAppSelector(state => state.userInfo.entities);
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

    dispatch(getInvoiceInfos({}));
    dispatch(getSupplierInfos({}));
    dispatch(getBankInfos({}));
    dispatch(getUserInfos({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    values.createdOn = convertDateTimeToServer(values.createdOn);

    const entity = {
      ...invoiceEntity,
      ...values,
      invoiceInfo: invoiceInfos.find(it => it.id.toString() === values.invoiceInfoId.toString()),
      supplierInfo: supplierInfos.find(it => it.id.toString() === values.supplierInfoId.toString()),
      bankInfo: bankInfos.find(it => it.id.toString() === values.bankInfoId.toString()),
      invoice: userInfos.find(it => it.id.toString() === values.invoiceId.toString()),
    };

    if (isNew) {
      dispatch(createEntity(entity));
    } else {
      dispatch(updateEntity(entity));
    }
  };

  const defaultValues = () =>
    isNew
      ? {
          createdOn: displayDefaultDateTime(),
        }
      : {
          ...invoiceEntity,
          createdOn: convertDateTimeFromServer(invoiceEntity.createdOn),
          invoiceInfoId: invoiceEntity?.invoiceInfo?.id,
          supplierInfoId: invoiceEntity?.supplierInfo?.id,
          bankInfoId: invoiceEntity?.bankInfo?.id,
          invoiceId: invoiceEntity?.invoice?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2
            id="payItBitApplicationApp.invoice.home.createOrEditLabel"
            className="font-face-raleway-green"
            data-cy="InvoiceCreateUpdateHeading"
          >
            Create or edit a Invoice
          </h2>
        </Col>
      </Row>

      <Row className="justify-content-center font-face-raleway-green">
        {/*<CreateOrEditInvoice/>*/}
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? <ValidatedField name="id" required readOnly id="invoice-id" label="ID" validate={{ required: true }} /> : null}
              <ValidatedField label="Seq No" id="invoice-seqNo" name="seqNo" data-cy="seqNo" type="text" />
              <ValidatedField
                label="Created On"
                id="invoice-createdOn"
                name="createdOn"
                data-cy="createdOn"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField label="Created By" id="invoice-createdBy" name="createdBy" data-cy="createdBy" type="text" />
              <ValidatedField label="Action" id="invoice-action" name="action" data-cy="action" type="text" />
              <ValidatedField id="invoice-invoiceInfo" name="invoiceInfoId" data-cy="invoiceInfo" label="Invoice Info" type="select">
                <option value="" key="0" />
                {invoiceInfos
                  ? invoiceInfos.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField id="invoice-supplierInfo" name="supplierInfoId" data-cy="supplierInfo" label="Supplier Info" type="select">
                <option value="" key="0" />
                {supplierInfos
                  ? supplierInfos.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField id="invoice-bankInfo" name="bankInfoId" data-cy="bankInfo" label="Bank Info" type="select">
                <option value="" key="0" />
                {bankInfos
                  ? bankInfos.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField id="invoice-invoice" name="invoiceId" data-cy="invoice" label="Invoice" type="select">
                <option value="" key="0" />
                {userInfos
                  ? userInfos.map(otherEntity => (
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
