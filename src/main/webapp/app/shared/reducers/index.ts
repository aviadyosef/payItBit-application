import { loadingBarReducer as loadingBar } from 'react-redux-loading-bar';

import authentication, { AuthenticationState } from './authentication';
import applicationProfile, { ApplicationProfileState } from './application-profile';

import administration, { AdministrationState } from 'app/modules/administration/administration.reducer';
import userManagement, { UserManagementState } from 'app/modules/administration/user-management/user-management.reducer';
import register, { RegisterState } from 'app/modules/account/register/register.reducer';
import activate, { ActivateState } from 'app/modules/account/activate/activate.reducer';
import password, { PasswordState } from 'app/modules/account/password/password.reducer';
import settings, { SettingsState } from 'app/modules/account/settings/settings.reducer';
import passwordReset, { PasswordResetState } from 'app/modules/account/password-reset/password-reset.reducer';
// prettier-ignore
import userInfo from 'app/entities/user-info/user-info.reducer';
// prettier-ignore
import transaction from 'app/entities/transaction/transaction.reducer';
// prettier-ignore
import invoice from 'app/entities/invoice/invoice.reducer';
// prettier-ignore
import wallet from 'app/entities/wallet/wallet.reducer';
// prettier-ignore
import invoiceInfo from 'app/entities/invoice-info/invoice-info.reducer';
// prettier-ignore
import supplierInfo from 'app/entities/supplier-info/supplier-info.reducer';
// prettier-ignore
import bankInfo from 'app/entities/bank-info/bank-info.reducer';
/* jhipster-needle-add-reducer-import - JHipster will add reducer here */

const rootReducer = {
  authentication,
  applicationProfile,
  administration,
  userManagement,
  register,
  activate,
  passwordReset,
  password,
  settings,
  userInfo,
  transaction,
  invoice,
  wallet,
  invoiceInfo,
  supplierInfo,
  bankInfo,
  /* jhipster-needle-add-reducer-combine - JHipster will add reducer here */
  loadingBar,
};

export default rootReducer;
