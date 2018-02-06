package com.rafael.springclass.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.rafael.springclass.domain.enums.CustomerType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@EqualsAndHashCode(of = {"id"})
public class Customer implements Serializable {


    private static final long serialVersionUID = -5418599027150357684L;

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    private String document;

    private Integer customerType;

    @Getter
    @Setter
    @JsonManagedReference
    @OneToMany(mappedBy = "customer")
    private List<Address> addresses;

    /**
     * {@link ElementCollection} - Maps a table for a list of non-entities
     *
     * {@link CollectionTable} - Specifies a table
     */
    @Getter
    @Setter
    @ElementCollection
    @CollectionTable(name = "phone_numbers")
    private Set<String> phoneNumbers;

    @Getter
    @Setter
    @OneToMany(mappedBy = "customer")
    private List<PurchaseOrder> purchaseOrders;

    public Customer() {
        this.addresses = new ArrayList<>();
        this.phoneNumbers = new HashSet<>();
        this.purchaseOrders = new ArrayList<>();
    }

    public Customer(final String name, final String email, final String document, final CustomerType customerType) {
        this.name = name;
        this.email = email;
        this.document = document;
        this.customerType = customerType.getCode();
        this.addresses = new ArrayList<>();
        this.phoneNumbers = new HashSet<>();
        this.purchaseOrders = new ArrayList<>();
    }

    public CustomerType getCustomerType() {
        return CustomerType.toEnum(this.customerType);
    }

    public void setCustomerType(final CustomerType customerType) {
        this.customerType = customerType.getCode();
    }
}
