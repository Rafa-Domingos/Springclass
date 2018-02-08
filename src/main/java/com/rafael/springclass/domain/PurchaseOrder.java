package com.rafael.springclass.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@EqualsAndHashCode(of = {"id"})
public class PurchaseOrder implements Serializable {

    private static final long serialVersionUID = -810830591273063226L;

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Getter
    @Setter
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date instant;

    @Getter
    @Setter
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "purchaseOrder")
    @JsonManagedReference
    private Payment payment;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonManagedReference
    private Customer customer;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "delivery_address_id")
    private Address deliveryAddress;

    @Getter
    @Setter
    @OneToMany(mappedBy = "id.purchaseOrder")
    private Set<OrderItem> items;

    public PurchaseOrder() {
        this.items = new HashSet<>();
    }

    public PurchaseOrder(final Date instant, final Customer customer, final Address deliveryAddress) {
        this.instant = instant;
        this.customer = customer;
        this.deliveryAddress = deliveryAddress;
        this.items = new HashSet<>();
    }
}
