import dayjs from 'dayjs';
import { IInvoiceInfo } from 'app/shared/model/invoice-info.model';
import { ISupplierInfo } from 'app/shared/model/supplier-info.model';
import { IBankInfo } from 'app/shared/model/bank-info.model';
import { IUserInfo } from 'app/shared/model/user-info.model';

export interface IInvoice {
  id?: number;
  seqNo?: string | null;
  createdOn?: string | null;
  createdBy?: string | null;
  action?: string | null;
  invoiceInfo?: IInvoiceInfo | null;
  supplierInfo?: ISupplierInfo | null;
  bankInfo?: IBankInfo | null;
  invoice?: IUserInfo | null;
}

export const defaultValue: Readonly<IInvoice> = {};
