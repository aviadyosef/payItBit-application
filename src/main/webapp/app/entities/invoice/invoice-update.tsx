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
          <CreateOrEditInvoice />
        </Col>

        <Col md="8">
          <div className="form-row mb-4"></div>
        </Col>
      </Row>

      <Row className="justify-content-lg-center">
        <button type="submit" className="btn-payit-white btn-light font-face-raleway-green">
          ADD NEW INVOICE
        </button>
        <button type="submit" className="btn-payit btn-light font-face-raleway">
          SUBMIT INVOICE
        </button>
      </Row>
    </div>
  );
};

export default InvoiceUpdate;
