import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IUserInfo } from 'app/shared/model/user-info.model';
import { getEntities as getUserInfos } from 'app/entities/user-info/user-info.reducer';
import { getEntity, updateEntity, createEntity, reset } from './transaction.reducer';
import { ITransaction } from 'app/shared/model/transaction.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const TransactionUpdate = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const userInfos = useAppSelector(state => state.userInfo.entities);
  const transactionEntity = useAppSelector(state => state.transaction.entity);
  const loading = useAppSelector(state => state.transaction.loading);
  const updating = useAppSelector(state => state.transaction.updating);
  const updateSuccess = useAppSelector(state => state.transaction.updateSuccess);

  const handleClose = () => {
    props.history.push('/transaction');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(props.match.params.id));
    }

    dispatch(getUserInfos({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    const entity = {
      ...transactionEntity,
      ...values,
      transaction: userInfos.find(it => it.id.toString() === values.transactionId.toString()),
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
          ...transactionEntity,
          transactionId: transactionEntity?.transaction?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="payItBitApplicationApp.transaction.home.createOrEditLabel" data-cy="TransactionCreateUpdateHeading">
            Create or edit a Transaction
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? <ValidatedField name="id" required readOnly id="transaction-id" label="ID" validate={{ required: true }} /> : null}
              <ValidatedField label="Title" id="transaction-title" name="title" data-cy="title" type="text" />
              <ValidatedField label="Created On" id="transaction-createdOn" name="createdOn" data-cy="createdOn" type="text" />
              <ValidatedField
                label="Sender Address"
                id="transaction-senderAddress"
                name="senderAddress"
                data-cy="senderAddress"
                type="text"
              />
              <ValidatedField
                label="Receiver Address"
                id="transaction-receiverAddress"
                name="receiverAddress"
                data-cy="receiverAddress"
                type="text"
              />
              <ValidatedField label="Amount" id="transaction-amount" name="amount" data-cy="amount" type="text" />
              <ValidatedField label="Description" id="transaction-description" name="description" data-cy="description" type="text" />
              <ValidatedField id="transaction-transaction" name="transactionId" data-cy="transaction" label="Transaction" type="select">
                <option value="" key="0" />
                {userInfos
                  ? userInfos.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/transaction" replace color="info">
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

export default TransactionUpdate;
