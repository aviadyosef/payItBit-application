import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import {} from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntity } from './user-info.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const UserInfoDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const userInfoEntity = useAppSelector(state => state.userInfo.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="userInfoDetailsHeading">UserInfo</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{userInfoEntity.id}</dd>
          <dt>
            <span id="seqNo">Seq No</span>
          </dt>
          <dd>{userInfoEntity.seqNo}</dd>
          <dt>
            <span id="createdOn">Created On</span>
          </dt>
          <dd>{userInfoEntity.createdOn}</dd>
          <dt>
            <span id="firstName">First Name</span>
          </dt>
          <dd>{userInfoEntity.firstName}</dd>
          <dt>
            <span id="lastName">Last Name</span>
          </dt>
          <dd>{userInfoEntity.lastName}</dd>
          <dt>
            <span id="email">Email</span>
          </dt>
          <dd>{userInfoEntity.email}</dd>
          <dt>
            <span id="phoneNumber">Phone Number</span>
          </dt>
          <dd>{userInfoEntity.phoneNumber}</dd>
          <dt>
            <span id="companyName">Company Name</span>
          </dt>
          <dd>{userInfoEntity.companyName}</dd>
          <dt>
            <span id="telegramName">Telegram Name</span>
          </dt>
          <dd>{userInfoEntity.telegramName}</dd>
        </dl>
        <Button tag={Link} to="/user-info" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/user-info/${userInfoEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

export default UserInfoDetail;
