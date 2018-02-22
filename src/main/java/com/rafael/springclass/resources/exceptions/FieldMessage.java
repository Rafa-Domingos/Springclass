package com.rafael.springclass.resources.exceptions;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

public class FieldMessage implements Serializable {

    private static final long serialVersionUID = -3428814661223894676L;

    @Getter
    @Setter
    private String fieldName;

    @Getter
    @Setter
    private String message;

    public FieldMessage() {
    }

    public FieldMessage(final String fieldName, final String message) {
        this.fieldName = fieldName;
        this.message = message;
    }
}
