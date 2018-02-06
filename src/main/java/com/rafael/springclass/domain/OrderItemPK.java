package com.rafael.springclass.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
public class OrderItemPK implements Serializable {

    private static final long serialVersionUID = -3327355228711863117L;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "order_id")
    private PurchaseOrder purchaseOrder;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
