package com.rafael.springclass.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@EqualsAndHashCode(of = {"id"})
public class OrderItem implements Serializable {

    private static final long serialVersionUID = -825405845976759422L;

    @EmbeddedId
    private OrderItemPK id;

    @Getter
    @Setter
    private BigDecimal discount;

    @Getter
    @Setter
    private Integer quanitty;

    @Getter
    @Setter
    private BigDecimal price;

    public OrderItem() {
        this.id = new OrderItemPK();
    }

    public OrderItem(final PurchaseOrder purchaseOrder, final Product product, final BigDecimal discount,
                     final Integer quanitty, final BigDecimal price) {
        this.id = new OrderItemPK();
        this.id.setPurchaseOrder(purchaseOrder);
        this.id.setProduct(product);
        this.discount = discount;
        this.quanitty = quanitty;
        this.price = price;
    }

    public PurchaseOrder getPurchaseOrder() {
        return this.id.getPurchaseOrder();
    }

    public Product getProduct() {
        return this.id.getProduct();
    }
}
