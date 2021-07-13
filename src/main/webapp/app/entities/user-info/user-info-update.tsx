import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntity, updateEntity, createEntity, reset } from './user-info.reducer';
import { IUserInfo } from 'app/shared/model/user-info.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const UserInfoUpdate = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const userInfoEntity = useAppSelector(state => state.userInfo.entity);
  const loading = useAppSelector(state => state.userInfo.loading);
  const updating = useAppSelector(state => state.userInfo.updating);
  const updateSuccess = useAppSelector(state => state.userInfo.updateSuccess);

  const handleClose = () => {
    props.history.push('/user-info');
  };

  useEffect(() => {
    if (!isNew) {
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
      ...userInfoEntity,
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
          ...userInfoEntity,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="payItBitApplicationApp.userInfo.home.createOrEditLabel" data-cy="UserInfoCreateUpdateHeading">
            Create or edit a UserInfo
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? <ValidatedField name="id" required readOnly id="user-info-id" label="ID" validate={{ required: true }} /> : null}
              <ValidatedField label="Seq No" id="user-info-seqNo" name="seqNo" data-cy="seqNo" type="text" />
              <ValidatedField label="Created On" id="user-info-createdOn" name="createdOn" data-cy="createdOn" type="text" />
              <ValidatedField label="First Name" id="user-info-firstName" name="firstName" data-cy="firstName" type="text" />
              <ValidatedField label="Last Name" id="user-info-lastName" name="lastName" data-cy="lastName" type="text" />
              <ValidatedField label="Email" id="user-info-email" name="email" data-cy="email" type="text" />
              <ValidatedField label="Phone Number" id="user-info-phoneNumber" name="phoneNumber" data-cy="phoneNumber" type="text" />
              <ValidatedField label="Company Name" id="user-info-companyName" name="companyName" data-cy="companyName" type="text" />
              <ValidatedField label="Telegram Name" id="user-info-telegramName" name="telegramName" data-cy="telegramName" type="text" />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/user-info" replace color="info">
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

export default UserInfoUpdate;
