package com.ay.payitbit.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;

/**
 * A Invoice.
 */
@Entity
@Table(name = "invoice")
public class Invoice implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "seq_no")
    private String seqNo;

    @Column(name = "created_on")
    private Long createdOn;

    @Column(name = "invoice_number")
    private String invoiceNumber;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "action")
    private String action;

    @ManyToOne
    @JsonIgnoreProperties(value = { "wallets", "invoices", "transactions" }, allowSetters = true)
    private Customer invoice;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Invoice id(Long id) {
        this.id = id;
        return this;
    }

    public String getSeqNo() {
        return this.seqNo;
    }

    public Invoice seqNo(String seqNo) {
        this.seqNo = seqNo;
        return this;
    }

    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo;
    }

    public Long getCreatedOn() {
        return this.createdOn;
    }

    public Invoice createdOn(Long createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public void setCreatedOn(Long createdOn) {
        this.createdOn = createdOn;
    }

    public String getInvoiceNumber() {
        return this.invoiceNumber;
    }

    public Invoice invoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
        return this;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getCompanyName() {
        return this.companyName;
    }

    public Invoice companyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public Invoice createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getAction() {
        return this.action;
    }

    public Invoice action(String action) {
        this.action = action;
        return this;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Customer getInvoice() {
        return this.invoice;
    }

    public Invoice invoice(Customer customer) {
        this.setInvoice(customer);
        return this;
    }

    public void setInvoice(Customer customer) {
        this.invoice = customer;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Invoice)) {
            return false;
        }
        return id != null && id.equals(((Invoice) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Invoice{" +
            "id=" + getId() +
            ", seqNo='" + getSeqNo() + "'" +
            ", createdOn=" + getCreatedOn() +
            ", invoiceNumber='" + getInvoiceNumber() + "'" +
            ", companyName='" + getCompanyName() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", action='" + getAction() + "'" +
            "}";
    }
}
