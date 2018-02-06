package com.rafael.springclass.domain;

import com.rafael.springclass.domain.enums.PaymentStatus;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@EqualsAndHashCode(of = {"id"})
public abstract class Payment implements Serializable {

    private static final long serialVersionUID = 4392876818364571062L;

    @Getter
    @Id
    private Integer id;

    private Integer status;

    @Getter
    @Setter
    @OneToOne
    @JoinColumn(name = "order_id")
    @MapsId
    private PurchaseOrder purchaseOrder;

    public Payment() {
    }

    public Payment(final PaymentStatus status, final PurchaseOrder purchaseOrder) {
        this.status = status.getCode();
        this.purchaseOrder = purchaseOrder;
    }

    public PaymentStatus getCustomerType() {
        return PaymentStatus.toEnum(this.status);
    }

    public void setCustomerType(final PaymentStatus status) {
        this.status = status.getCode();
    }
}
