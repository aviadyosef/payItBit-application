package com.ay.payitbit.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * A SupplierInfo.
 */
@Entity
@Table(name = "supplier_info")
public class SupplierInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "number")
    private String number;

    @Column(name = "address")
    private String address;

    @Column(name = "website")
    private String website;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SupplierInfo id(Long id) {
        this.id = id;
        return this;
    }

    public String getCompanyName() {
        return this.companyName;
    }

    public SupplierInfo companyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getNumber() {
        return this.number;
    }

    public SupplierInfo number(String number) {
        this.number = number;
        return this;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAddress() {
        return this.address;
    }

    public SupplierInfo address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWebsite() {
        return this.website;
    }

    public SupplierInfo website(String website) {
        this.website = website;
        return this;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SupplierInfo)) {
            return false;
        }
        return id != null && id.equals(((SupplierInfo) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SupplierInfo{" +
            "id=" + getId() +
            ", companyName='" + getCompanyName() + "'" +
            ", number='" + getNumber() + "'" +
            ", address='" + getAddress() + "'" +
            ", website='" + getWebsite() + "'" +
            "}";
    }
}
