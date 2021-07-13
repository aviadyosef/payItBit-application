export interface IInvoiceInfo {
  id?: number;
  amount?: string | null;
  invoiceNumber?: string | null;
  currency?: string | null;
  invoicePictureContentType?: string | null;
  invoicePicture?: string | null;
  agreementContentType?: string | null;
  agreement?: string | null;
  serviceDescription?: string | null;
}

export const defaultValue: Readonly<IInvoiceInfo> = {};
