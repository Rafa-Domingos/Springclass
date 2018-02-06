package com.rafael.springclass.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

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
    private Date instant;

    @Getter
    @Setter
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "purchaseOrder")
    private Payment payment;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "delivery_address_id")
    private Address deliveryAddress;

    public PurchaseOrder() {
    }

    public PurchaseOrder(final Date instant, final Customer customer, final Address deliveryAddress) {
        this.instant = instant;
        this.customer = customer;
        this.deliveryAddress = deliveryAddress;
    }
}
