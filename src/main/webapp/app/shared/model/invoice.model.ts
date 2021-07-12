import { ICustomer } from 'app/shared/model/customer.model';

export interface IInvoice {
  id?: number;
  seqNo?: string | null;
  createdOn?: number | null;
  invoiceNumber?: string | null;
  companyName?: string | null;
  createdBy?: string | null;
  action?: string | null;
  invoice?: ICustomer | null;
}

export const defaultValue: Readonly<IInvoice> = {};
