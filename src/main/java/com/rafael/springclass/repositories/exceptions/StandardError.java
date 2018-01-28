package com.rafael.springclass.repositories.exceptions;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

public class StandardError implements Serializable {

    /**
     * Generated serial version.
     */
    private static final long serialVersionUID = -8574148168127904702L;

    @Getter
    @Setter
    private Integer status;

    @Getter
    @Setter
    private String message;

    @Getter
    @Setter
    private Long timestamp;

    public StandardError(final Integer status, final String message, final Long timestamp) {
        this.status = status;
        this.message = message;
        this.timestamp = timestamp;
    }
}
