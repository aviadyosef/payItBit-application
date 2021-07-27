package com.ay.payitbit.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

/**
 * The Customer entity.
 */
@ApiModel(description = "The Customer entity.")
@Entity
@Table(name = "customer")
public class Customer implements Serializable {

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

    @Column(name = "email")
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
    @JsonIgnoreProperties(value = { "invoice" }, allowSetters = true)
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

    public Customer id(Long id) {
        this.id = id;
        return this;
    }

    public Long getSeqNo() {
        return this.seqNo;
    }

    public Customer seqNo(Long seqNo) {
        this.seqNo = seqNo;
        return this;
    }

    public void setSeqNo(Long seqNo) {
        this.seqNo = seqNo;
    }

    public Long getCreatedOn() {
        return this.createdOn;
    }

    public Customer createdOn(Long createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public void setCreatedOn(Long createdOn) {
        this.createdOn = createdOn;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public Customer firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public Customer lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public Customer email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public Customer phoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCompanyName() {
        return this.companyName;
    }

    public Customer companyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getTelegramName() {
        return this.telegramName;
    }

    public Customer telegramName(String telegramName) {
        this.telegramName = telegramName;
        return this;
    }

    public void setTelegramName(String telegramName) {
        this.telegramName = telegramName;
    }

    public Set<Wallet> getWallets() {
        return this.wallets;
    }

    public Customer wallets(Set<Wallet> wallets) {
        this.setWallets(wallets);
        return this;
    }

    public Customer addWallet(Wallet wallet) {
        this.wallets.add(wallet);
        wallet.setWallet(this);
        return this;
    }

    public Customer removeWallet(Wallet wallet) {
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

    public Customer invoices(Set<Invoice> invoices) {
        this.setInvoices(invoices);
        return this;
    }

    public Customer addInvoice(Invoice invoice) {
        this.invoices.add(invoice);
        invoice.setInvoice(this);
        return this;
    }

    public Customer removeInvoice(Invoice invoice) {
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

    public Customer transactions(Set<Transaction> transactions) {
        this.setTransactions(transactions);
        return this;
    }

    public Customer addTransaction(Transaction transaction) {
        this.transactions.add(transaction);
        transaction.setTransaction(this);
        return this;
    }

    public Customer removeTransaction(Transaction transaction) {
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
        if (!(o instanceof Customer)) {
            return false;
        }
        return id != null && id.equals(((Customer) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Customer{" +
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
