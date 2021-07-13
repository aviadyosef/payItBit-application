import { IUserInfo } from 'app/shared/model/user-info.model';
import { WalletType } from 'app/shared/model/enumerations/wallet-type.model';

export interface IWallet {
  id?: number;
  readableName?: string | null;
  walletType?: WalletType | null;
  wallet?: IUserInfo | null;
}

export const defaultValue: Readonly<IWallet> = {};
