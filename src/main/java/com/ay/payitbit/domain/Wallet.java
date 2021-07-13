package com.ay.payitbit.domain;

import com.ay.payitbit.domain.enumeration.WalletType;
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
    @Column(name = "wallet_type")
    private WalletType walletType;

    @ManyToOne
    @JsonIgnoreProperties(value = { "wallets", "invoices", "transactions" }, allowSetters = true)
    private UserInfo wallet;

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

    public WalletType getWalletType() {
        return this.walletType;
    }

    public Wallet walletType(WalletType walletType) {
        this.walletType = walletType;
        return this;
    }

    public void setWalletType(WalletType walletType) {
        this.walletType = walletType;
    }

    public UserInfo getWallet() {
        return this.wallet;
    }

    public Wallet wallet(UserInfo userInfo) {
        this.setWallet(userInfo);
        return this;
    }

    public void setWallet(UserInfo userInfo) {
        this.wallet = userInfo;
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
            ", walletType='" + getWalletType() + "'" +
            "}";
    }
}
