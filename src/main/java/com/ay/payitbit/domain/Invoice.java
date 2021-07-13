package com.ay.payitbit.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.ZonedDateTime;
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
    private ZonedDateTime createdOn;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "action")
    private String action;

    @OneToOne
    @JoinColumn(unique = true)
    private InvoiceInfo invoiceInfo;

    @OneToOne
    @JoinColumn(unique = true)
    private SupplierInfo supplierInfo;

    @OneToOne
    @JoinColumn(unique = true)
    private BankInfo bankInfo;

    @ManyToOne
    @JsonIgnoreProperties(value = { "wallets", "invoices", "transactions" }, allowSetters = true)
    private UserInfo invoice;

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

    public ZonedDateTime getCreatedOn() {
        return this.createdOn;
    }

    public Invoice createdOn(ZonedDateTime createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public void setCreatedOn(ZonedDateTime createdOn) {
        this.createdOn = createdOn;
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

    public InvoiceInfo getInvoiceInfo() {
        return this.invoiceInfo;
    }

    public Invoice invoiceInfo(InvoiceInfo invoiceInfo) {
        this.setInvoiceInfo(invoiceInfo);
        return this;
    }

    public void setInvoiceInfo(InvoiceInfo invoiceInfo) {
        this.invoiceInfo = invoiceInfo;
    }

    public SupplierInfo getSupplierInfo() {
        return this.supplierInfo;
    }

    public Invoice supplierInfo(SupplierInfo supplierInfo) {
        this.setSupplierInfo(supplierInfo);
        return this;
    }

    public void setSupplierInfo(SupplierInfo supplierInfo) {
        this.supplierInfo = supplierInfo;
    }

    public BankInfo getBankInfo() {
        return this.bankInfo;
    }

    public Invoice bankInfo(BankInfo bankInfo) {
        this.setBankInfo(bankInfo);
        return this;
    }

    public void setBankInfo(BankInfo bankInfo) {
        this.bankInfo = bankInfo;
    }

    public UserInfo getInvoice() {
        return this.invoice;
    }

    public Invoice invoice(UserInfo userInfo) {
        this.setInvoice(userInfo);
        return this;
    }

    public void setInvoice(UserInfo userInfo) {
        this.invoice = userInfo;
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
            ", createdOn='" + getCreatedOn() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", action='" + getAction() + "'" +
            "}";
    }
}
