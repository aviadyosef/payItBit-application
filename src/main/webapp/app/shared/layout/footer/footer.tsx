import './footer.scss';

import React from 'react';

import { Col, Row } from 'reactstrap';

const Footer = props => (
  <div className="footer p-4">
    <div className="container-fluid">
      <div className="row">
        <div className="col-md-12 text-center">
          <p className="text-large">
            {' '}
            © <script>document.write(new Date().getFullYear())</script>2021 PayItBit.
          </p>
        </div>
      </div>
    </div>
  </div>
);

export default Footer;
