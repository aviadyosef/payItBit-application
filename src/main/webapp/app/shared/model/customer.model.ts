import { IWallet } from 'app/shared/model/wallet.model';
import { IInvoice } from 'app/shared/model/invoice.model';
import { ITransaction } from 'app/shared/model/transaction.model';

export interface ICustomer {
  id?: number;
  seqNo?: number | null;
  createdOn?: number | null;
  firstName?: string | null;
  lastName?: string | null;
  email?: string | null;
  phoneNumber?: string | null;
  companyName?: string | null;
  telegramName?: string | null;
  wallets?: IWallet[] | null;
  invoices?: IInvoice[] | null;
  transactions?: ITransaction[] | null;
}

export const defaultValue: Readonly<ICustomer> = {};
