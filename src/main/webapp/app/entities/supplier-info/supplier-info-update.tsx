import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntity, updateEntity, createEntity, reset } from './supplier-info.reducer';
import { ISupplierInfo } from 'app/shared/model/supplier-info.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const SupplierInfoUpdate = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const supplierInfoEntity = useAppSelector(state => state.supplierInfo.entity);
  const loading = useAppSelector(state => state.supplierInfo.loading);
  const updating = useAppSelector(state => state.supplierInfo.updating);
  const updateSuccess = useAppSelector(state => state.supplierInfo.updateSuccess);

  const handleClose = () => {
    props.history.push('/supplier-info');
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
      ...supplierInfoEntity,
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
          ...supplierInfoEntity,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="payItBitApplicationApp.supplierInfo.home.createOrEditLabel" data-cy="SupplierInfoCreateUpdateHeading">
            Create or edit a SupplierInfo
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? (
                <ValidatedField name="id" required readOnly id="supplier-info-id" label="ID" validate={{ required: true }} />
              ) : null}
              <ValidatedField label="Company Name" id="supplier-info-companyName" name="companyName" data-cy="companyName" type="text" />
              <ValidatedField label="Number" id="supplier-info-number" name="number" data-cy="number" type="text" />
              <ValidatedField label="Address" id="supplier-info-address" name="address" data-cy="address" type="text" />
              <ValidatedField label="Website" id="supplier-info-website" name="website" data-cy="website" type="text" />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/supplier-info" replace color="info">
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

export default SupplierInfoUpdate;
