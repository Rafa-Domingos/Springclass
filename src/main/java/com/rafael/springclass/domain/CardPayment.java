package com.rafael.springclass.domain;

import com.rafael.springclass.domain.enums.PaymentStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
public class CardPayment extends Payment {

    private static final long serialVersionUID = 8337307659121309954L;

    @Getter
    @Setter
    private Integer numberOfInstallments;

    public CardPayment() {}

    public CardPayment(final PaymentStatus status, final PurchaseOrder purchaseOrder, final Integer numberOfInstallments) {
        super(status, purchaseOrder);
        this.numberOfInstallments = numberOfInstallments;
    }
}
