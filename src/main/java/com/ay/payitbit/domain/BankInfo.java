package com.ay.payitbit.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * A BankInfo.
 */
@Entity
@Table(name = "bank_info")
public class BankInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "account_holder_name")
    private String accountHolderName;

    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "iban_number")
    private String ibanNumber;

    @Column(name = "swift_code")
    private String swiftCode;

    @Column(name = "bank_address")
    private String bankAddress;

    @Column(name = "country")
    private String country;

    @Column(name = "reference_no")
    private String referenceNo;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BankInfo id(Long id) {
        this.id = id;
        return this;
    }

    public String getAccountHolderName() {
        return this.accountHolderName;
    }

    public BankInfo accountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
        return this;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public String getAccountNumber() {
        return this.accountNumber;
    }

    public BankInfo accountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
        return this;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getIbanNumber() {
        return this.ibanNumber;
    }

    public BankInfo ibanNumber(String ibanNumber) {
        this.ibanNumber = ibanNumber;
        return this;
    }

    public void setIbanNumber(String ibanNumber) {
        this.ibanNumber = ibanNumber;
    }

    public String getSwiftCode() {
        return this.swiftCode;
    }

    public BankInfo swiftCode(String swiftCode) {
        this.swiftCode = swiftCode;
        return this;
    }

    public void setSwiftCode(String swiftCode) {
        this.swiftCode = swiftCode;
    }

    public String getBankAddress() {
        return this.bankAddress;
    }

    public BankInfo bankAddress(String bankAddress) {
        this.bankAddress = bankAddress;
        return this;
    }

    public void setBankAddress(String bankAddress) {
        this.bankAddress = bankAddress;
    }

    public String getCountry() {
        return this.country;
    }

    public BankInfo country(String country) {
        this.country = country;
        return this;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getReferenceNo() {
        return this.referenceNo;
    }

    public BankInfo referenceNo(String referenceNo) {
        this.referenceNo = referenceNo;
        return this;
    }

    public void setReferenceNo(String referenceNo) {
        this.referenceNo = referenceNo;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BankInfo)) {
            return false;
        }
        return id != null && id.equals(((BankInfo) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BankInfo{" +
            "id=" + getId() +
            ", accountHolderName='" + getAccountHolderName() + "'" +
            ", accountNumber='" + getAccountNumber() + "'" +
            ", ibanNumber='" + getIbanNumber() + "'" +
            ", swiftCode='" + getSwiftCode() + "'" +
            ", bankAddress='" + getBankAddress() + "'" +
            ", country='" + getCountry() + "'" +
            ", referenceNo='" + getReferenceNo() + "'" +
            "}";
    }
}
