import { ICustomer } from 'app/shared/model/customer.model';
import { WallteType } from 'app/shared/model/enumerations/wallte-type.model';

export interface IWallet {
  id?: number;
  readableName?: string | null;
  wallteType?: WallteType | null;
  idWallet?: string | null;
  wallet?: ICustomer | null;
}

export const defaultValue: Readonly<IWallet> = {};
