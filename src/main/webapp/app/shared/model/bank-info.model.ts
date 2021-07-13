export interface IBankInfo {
  id?: number;
  accountHolderName?: string | null;
  accountNumber?: string | null;
  ibanNumber?: string | null;
  swiftCode?: string | null;
  bankAddress?: string | null;
  country?: string | null;
  referenceNo?: string | null;
}

export const defaultValue: Readonly<IBankInfo> = {};
