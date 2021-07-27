import './home.scss';

import React, { useEffect } from 'react';
import { Link } from 'react-router-dom';

import { Row, Col, Alert } from 'reactstrap';

import { useAppSelector } from 'app/config/store';

export const Home = () => {
  const account = useAppSelector(state => state.authentication.account);

  return (
    <Row>
      <Col md="3" className="pad"></Col>
      <Col md="9">
        <p className="welcome-text">Welcome, {account.login}</p>
        {/*{account && account.login ? (*/}
        {/*  <div>*/}
        {/*    <Alert color="success">You are logged in as user {account.login}.</Alert>*/}
        {/*  </div>*/}
        {/*) : (*/}
        {/*  <div>*/}
        {/*    <Alert color="warning">*/}
        {/*      If you want to*/}
        {/*      <span>&nbsp;</span>*/}
        {/*      <Link to="/login" className="alert-link">*/}
        {/*        {' '}*/}
        {/*        sign in*/}
        {/*      </Link>*/}
        {/*      , you can try the default accounts:*/}
        {/*      <br />- Administrator (login=&quot;admin&quot; and password=&quot;admin&quot;)*/}
        {/*      <br />- User (login=&quot;user&quot; and password=&quot;user&quot;).*/}
        {/*    </Alert>*/}

        {/*    <Alert color="warning">*/}
        {/*      You do not have an account yet?&nbsp;*/}
        {/*      <Link to="/account/register" className="alert-link">*/}
        {/*        Register a new account*/}
        {/*      </Link>*/}
        {/*    </Alert>*/}
        {/*  </div>*/}
        {/*)}*/}
        {/*/!*<p>If you have any question on JHipster:</p>*!/*/}

        {/*<ul>*/}
        {/*  <li>*/}
        {/*    <a href="https://www.jhipster.tech/" target="_blank" rel="noopener noreferrer">*/}
        {/*      JHipster homepage*/}
        {/*    </a>*/}
        {/*  </li>*/}
        {/*  <li>*/}
        {/*    <a href="https://stackoverflow.com/tags/jhipster/info" target="_blank" rel="noopener noreferrer">*/}
        {/*      JHipster on Stack Overflow*/}
        {/*    </a>*/}
        {/*  </li>*/}
        {/*  <li>*/}
        {/*    <a href="https://github.com/jhipster/generator-jhipster/issues?state=open" target="_blank" rel="noopener noreferrer">*/}
        {/*      JHipster bug tracker*/}
        {/*    </a>*/}
        {/*  </li>*/}
        {/*  <li>*/}
        {/*    <a href="https://gitter.im/jhipster/generator-jhipster" target="_blank" rel="noopener noreferrer">*/}
        {/*      JHipster public chat room*/}
        {/*    </a>*/}
        {/*  </li>*/}
        {/*  <li>*/}
        {/*    <a href="https://twitter.com/jhipster" target="_blank" rel="noopener noreferrer">*/}
        {/*      follow @jhipster on Twitter*/}
        {/*    </a>*/}
        {/*  </li>*/}
        {/*</ul>*/}

        <p className="getstart">LETS GET</p>
        <p className="getstart">STARTED!</p>
        <div className="col-lg-3 col-md-3 mb-4">
          <img src="../../../content/images/dashboard-image.svg" alt="PAYITBIT" className="img-fluid" />
        </div>
        <div className="col-lg-6 mt-2 mb-5">
          <p className="welcome-text text-large font-weight-700">Start paying your Invoices today </p>
        </div>
      </Col>
    </Row>
  );
};

export default Home;
