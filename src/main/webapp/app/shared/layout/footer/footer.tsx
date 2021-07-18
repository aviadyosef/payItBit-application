import './footer.scss';
import '../../../../content/fonts/Raleway-100.woff2';

import React from 'react';

import { Col, Row } from 'reactstrap';

const Footer = props => (
  <div className="footer p-4">
    <div className="container-fluid">
      <div className="row">
        <div className="col-md-12 text-center font-face-raleway">
          <p className="text-large">
            {' '}
            © <script>document.write(new Date().getFullYear())</script>2021 Payitbit.
          </p>
        </div>
      </div>
    </div>
  </div>
);

export default Footer;
