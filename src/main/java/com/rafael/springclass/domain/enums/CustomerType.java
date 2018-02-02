package com.rafael.springclass.domain.enums;

import lombok.Getter;

public enum CustomerType {

    NATURAL(0),

    LEGAL(1);

    @Getter
    private Integer code;

    private CustomerType(final Integer code) {
        this.code = code;
    }

    public static CustomerType toEnum(final Integer code) {
        if (code == null) return null;

        for (final CustomerType customerType : values()) {
            if (code.equals(customerType.getCode()))
                return customerType;
        }

        throw new IllegalArgumentException("Invalid customer type code: " + code);
    }
}
