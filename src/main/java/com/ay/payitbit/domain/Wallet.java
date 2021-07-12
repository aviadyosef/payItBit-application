package com.ay.payitbit.domain;

import com.ay.payitbit.domain.enumeration.WallteType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;

/**
 * A Wallet.
 */
@Entity
@Table(name = "wallet")
public class Wallet implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "readable_name")
    private String readableName;

    @Enumerated(EnumType.STRING)
    @Column(name = "wallte_type")
    private WallteType wallteType;

    @Column(name = "id_wallet")
    private String idWallet;

    @ManyToOne
    @JsonIgnoreProperties(value = { "wallets", "invoices", "transactions" }, allowSetters = true)
    private Customer wallet;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Wallet id(Long id) {
        this.id = id;
        return this;
    }

    public String getReadableName() {
        return this.readableName;
    }

    public Wallet readableName(String readableName) {
        this.readableName = readableName;
        return this;
    }

    public void setReadableName(String readableName) {
        this.readableName = readableName;
    }

    public WallteType getWallteType() {
        return this.wallteType;
    }

    public Wallet wallteType(WallteType wallteType) {
        this.wallteType = wallteType;
        return this;
    }

    public void setWallteType(WallteType wallteType) {
        this.wallteType = wallteType;
    }

    public String getIdWallet() {
        return this.idWallet;
    }

    public Wallet idWallet(String idWallet) {
        this.idWallet = idWallet;
        return this;
    }

    public void setIdWallet(String idWallet) {
        this.idWallet = idWallet;
    }

    public Customer getWallet() {
        return this.wallet;
    }

    public Wallet wallet(Customer customer) {
        this.setWallet(customer);
        return this;
    }

    public void setWallet(Customer customer) {
        this.wallet = customer;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Wallet)) {
            return false;
        }
        return id != null && id.equals(((Wallet) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Wallet{" +
            "id=" + getId() +
            ", readableName='" + getReadableName() + "'" +
            ", wallteType='" + getWallteType() + "'" +
            ", idWallet='" + getIdWallet() + "'" +
            "}";
    }
}
