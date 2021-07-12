import { ICustomer } from 'app/shared/model/customer.model';

export interface ITransaction {
  id?: number;
  title?: string | null;
  createdOn?: number | null;
  senderAddress?: string | null;
  receiverAddress?: string | null;
  amount?: string | null;
  description?: string | null;
  transaction?: ICustomer | null;
}

export const defaultValue: Readonly<ITransaction> = {};
