import { IUserInfo } from 'app/shared/model/user-info.model';

export interface ITransaction {
  id?: number;
  title?: string | null;
  createdOn?: number | null;
  senderAddress?: string | null;
  receiverAddress?: string | null;
  amount?: string | null;
  description?: string | null;
  transaction?: IUserInfo | null;
}

export const defaultValue: Readonly<ITransaction> = {};
