package com.rafael.springclass.domain;

import com.rafael.springclass.domain.enums.PaymentStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class TicketPayment extends Payment {

    private static final long serialVersionUID = -6189307406001061741L;

    @Getter
    @Setter
    private Date expireDate;

    @Getter
    @Setter
    private Date paymentDate;

    public TicketPayment() {}

    public TicketPayment(final PaymentStatus status, final PurchaseOrder purchaseOrder, final Date expireDate, final Date paymentDate) {
        super(status, purchaseOrder);
        this.expireDate = expireDate;
        this.paymentDate = paymentDate;
    }
}
