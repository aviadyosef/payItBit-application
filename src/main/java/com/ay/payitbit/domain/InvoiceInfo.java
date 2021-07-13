package com.ay.payitbit.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * A InvoiceInfo.
 */
@Entity
@Table(name = "invoice_info")
public class InvoiceInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "amount")
    private String amount;

    @Column(name = "invoice_number")
    private String invoiceNumber;

    @Column(name = "currency")
    private String currency;

    @Lob
    @Column(name = "invoice_picture")
    private byte[] invoicePicture;

    @Column(name = "invoice_picture_content_type")
    private String invoicePictureContentType;

    @Lob
    @Column(name = "agreement")
    private byte[] agreement;

    @Column(name = "agreement_content_type")
    private String agreementContentType;

    @Column(name = "service_description")
    private String serviceDescription;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public InvoiceInfo id(Long id) {
        this.id = id;
        return this;
    }

    public String getAmount() {
        return this.amount;
    }

    public InvoiceInfo amount(String amount) {
        this.amount = amount;
        return this;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getInvoiceNumber() {
        return this.invoiceNumber;
    }

    public InvoiceInfo invoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
        return this;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getCurrency() {
        return this.currency;
    }

    public InvoiceInfo currency(String currency) {
        this.currency = currency;
        return this;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public byte[] getInvoicePicture() {
        return this.invoicePicture;
    }

    public InvoiceInfo invoicePicture(byte[] invoicePicture) {
        this.invoicePicture = invoicePicture;
        return this;
    }

    public void setInvoicePicture(byte[] invoicePicture) {
        this.invoicePicture = invoicePicture;
    }

    public String getInvoicePictureContentType() {
        return this.invoicePictureContentType;
    }

    public InvoiceInfo invoicePictureContentType(String invoicePictureContentType) {
        this.invoicePictureContentType = invoicePictureContentType;
        return this;
    }

    public void setInvoicePictureContentType(String invoicePictureContentType) {
        this.invoicePictureContentType = invoicePictureContentType;
    }

    public byte[] getAgreement() {
        return this.agreement;
    }

    public InvoiceInfo agreement(byte[] agreement) {
        this.agreement = agreement;
        return this;
    }

    public void setAgreement(byte[] agreement) {
        this.agreement = agreement;
    }

    public String getAgreementContentType() {
        return this.agreementContentType;
    }

    public InvoiceInfo agreementContentType(String agreementContentType) {
        this.agreementContentType = agreementContentType;
        return this;
    }

    public void setAgreementContentType(String agreementContentType) {
        this.agreementContentType = agreementContentType;
    }

    public String getServiceDescription() {
        return this.serviceDescription;
    }

    public InvoiceInfo serviceDescription(String serviceDescription) {
        this.serviceDescription = serviceDescription;
        return this;
    }

    public void setServiceDescription(String serviceDescription) {
        this.serviceDescription = serviceDescription;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InvoiceInfo)) {
            return false;
        }
        return id != null && id.equals(((InvoiceInfo) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "InvoiceInfo{" +
            "id=" + getId() +
            ", amount='" + getAmount() + "'" +
            ", invoiceNumber='" + getInvoiceNumber() + "'" +
            ", currency='" + getCurrency() + "'" +
            ", invoicePicture='" + getInvoicePicture() + "'" +
            ", invoicePictureContentType='" + getInvoicePictureContentType() + "'" +
            ", agreement='" + getAgreement() + "'" +
            ", agreementContentType='" + getAgreementContentType() + "'" +
            ", serviceDescription='" + getServiceDescription() + "'" +
            "}";
    }
}
