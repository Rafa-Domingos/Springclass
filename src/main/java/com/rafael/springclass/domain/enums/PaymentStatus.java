package com.rafael.springclass.domain.enums;

import lombok.Getter;

public enum PaymentStatus {

    PENDING(0),
    SETTLED(1),
    CANCELLED(2);

    @Getter
    private Integer code;

    private PaymentStatus(final Integer code) {
        this.code = code;
    }

    public static PaymentStatus toEnum(final Integer code) {
        if (code == null) return null;

        for (final PaymentStatus paymentStatus : values()) {
            if (code.equals(paymentStatus.getCode()))
                return paymentStatus;
        }

        throw new IllegalArgumentException("Invalid payment status code: " + code);
    }
}
