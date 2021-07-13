export interface ISupplierInfo {
  id?: number;
  companyName?: string | null;
  number?: string | null;
  address?: string | null;
  website?: string | null;
}

export const defaultValue: Readonly<ISupplierInfo> = {};
