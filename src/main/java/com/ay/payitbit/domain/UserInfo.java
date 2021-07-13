package com.ay.payitbit.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

/**
 * The UserInfo entity.
 */
@ApiModel(description = "The UserInfo entity.")
@Entity
@Table(name = "user_info")
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "seq_no")
    private Long seqNo;

    @Column(name = "created_on")
    private Long createdOn;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Email
    @Size(min = 5, max = 254)
    @Column(name = "email", length = 254, unique = true)
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "telegram_name")
    private String telegramName;

    @OneToMany(mappedBy = "wallet")
    @JsonIgnoreProperties(value = { "wallet" }, allowSetters = true)
    private Set<Wallet> wallets = new HashSet<>();

    @OneToMany(mappedBy = "invoice")
    @JsonIgnoreProperties(value = { "invoiceInfo", "supplierInfo", "bankInfo", "invoice" }, allowSetters = true)
    private Set<Invoice> invoices = new HashSet<>();

    @OneToMany(mappedBy = "transaction")
    @JsonIgnoreProperties(value = { "transaction" }, allowSetters = true)
    private Set<Transaction> transactions = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserInfo id(Long id) {
        this.id = id;
        return this;
    }

    public Long getSeqNo() {
        return this.seqNo;
    }

    public UserInfo seqNo(Long seqNo) {
        this.seqNo = seqNo;
        return this;
    }

    public void setSeqNo(Long seqNo) {
        this.seqNo = seqNo;
    }

    public Long getCreatedOn() {
        return this.createdOn;
    }

    public UserInfo createdOn(Long createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public void setCreatedOn(Long createdOn) {
        this.createdOn = createdOn;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public UserInfo firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public UserInfo lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public UserInfo email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public UserInfo phoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCompanyName() {
        return this.companyName;
    }

    public UserInfo companyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getTelegramName() {
        return this.telegramName;
    }

    public UserInfo telegramName(String telegramName) {
        this.telegramName = telegramName;
        return this;
    }

    public void setTelegramName(String telegramName) {
        this.telegramName = telegramName;
    }

    public Set<Wallet> getWallets() {
        return this.wallets;
    }

    public UserInfo wallets(Set<Wallet> wallets) {
        this.setWallets(wallets);
        return this;
    }

    public UserInfo addWallet(Wallet wallet) {
        this.wallets.add(wallet);
        wallet.setWallet(this);
        return this;
    }

    public UserInfo removeWallet(Wallet wallet) {
        this.wallets.remove(wallet);
        wallet.setWallet(null);
        return this;
    }

    public void setWallets(Set<Wallet> wallets) {
        if (this.wallets != null) {
            this.wallets.forEach(i -> i.setWallet(null));
        }
        if (wallets != null) {
            wallets.forEach(i -> i.setWallet(this));
        }
        this.wallets = wallets;
    }

    public Set<Invoice> getInvoices() {
        return this.invoices;
    }

    public UserInfo invoices(Set<Invoice> invoices) {
        this.setInvoices(invoices);
        return this;
    }

    public UserInfo addInvoice(Invoice invoice) {
        this.invoices.add(invoice);
        invoice.setInvoice(this);
        return this;
    }

    public UserInfo removeInvoice(Invoice invoice) {
        this.invoices.remove(invoice);
        invoice.setInvoice(null);
        return this;
    }

    public void setInvoices(Set<Invoice> invoices) {
        if (this.invoices != null) {
            this.invoices.forEach(i -> i.setInvoice(null));
        }
        if (invoices != null) {
            invoices.forEach(i -> i.setInvoice(this));
        }
        this.invoices = invoices;
    }

    public Set<Transaction> getTransactions() {
        return this.transactions;
    }

    public UserInfo transactions(Set<Transaction> transactions) {
        this.setTransactions(transactions);
        return this;
    }

    public UserInfo addTransaction(Transaction transaction) {
        this.transactions.add(transaction);
        transaction.setTransaction(this);
        return this;
    }

    public UserInfo removeTransaction(Transaction transaction) {
        this.transactions.remove(transaction);
        transaction.setTransaction(null);
        return this;
    }

    public void setTransactions(Set<Transaction> transactions) {
        if (this.transactions != null) {
            this.transactions.forEach(i -> i.setTransaction(null));
        }
        if (transactions != null) {
            transactions.forEach(i -> i.setTransaction(this));
        }
        this.transactions = transactions;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserInfo)) {
            return false;
        }
        return id != null && id.equals(((UserInfo) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UserInfo{" +
            "id=" + getId() +
            ", seqNo=" + getSeqNo() +
            ", createdOn=" + getCreatedOn() +
            ", firstName='" + getFirstName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", email='" + getEmail() + "'" +
            ", phoneNumber='" + getPhoneNumber() + "'" +
            ", companyName='" + getCompanyName() + "'" +
            ", telegramName='" + getTelegramName() + "'" +
            "}";
    }
}
