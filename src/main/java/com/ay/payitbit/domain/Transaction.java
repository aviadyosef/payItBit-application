package com.ay.payitbit.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;

/**
 * A Transaction.
 */
@Entity
@Table(name = "transaction")
public class Transaction implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "created_on")
    private Long createdOn;

    @Column(name = "sender_address")
    private String senderAddress;

    @Column(name = "receiver_address")
    private String receiverAddress;

    @Column(name = "amount")
    private String amount;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JsonIgnoreProperties(value = { "wallets", "invoices", "transactions" }, allowSetters = true)
    private Customer transaction;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Transaction id(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return this.title;
    }

    public Transaction title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getCreatedOn() {
        return this.createdOn;
    }

    public Transaction createdOn(Long createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public void setCreatedOn(Long createdOn) {
        this.createdOn = createdOn;
    }

    public String getSenderAddress() {
        return this.senderAddress;
    }

    public Transaction senderAddress(String senderAddress) {
        this.senderAddress = senderAddress;
        return this;
    }

    public void setSenderAddress(String senderAddress) {
        this.senderAddress = senderAddress;
    }

    public String getReceiverAddress() {
        return this.receiverAddress;
    }

    public Transaction receiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
        return this;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public String getAmount() {
        return this.amount;
    }

    public Transaction amount(String amount) {
        this.amount = amount;
        return this;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return this.description;
    }

    public Transaction description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Customer getTransaction() {
        return this.transaction;
    }

    public Transaction transaction(Customer customer) {
        this.setTransaction(customer);
        return this;
    }

    public void setTransaction(Customer customer) {
        this.transaction = customer;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Transaction)) {
            return false;
        }
        return id != null && id.equals(((Transaction) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Transaction{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", createdOn=" + getCreatedOn() +
            ", senderAddress='" + getSenderAddress() + "'" +
            ", receiverAddress='" + getReceiverAddress() + "'" +
            ", amount='" + getAmount() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
