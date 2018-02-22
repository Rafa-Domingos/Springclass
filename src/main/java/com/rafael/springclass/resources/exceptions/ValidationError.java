package com.rafael.springclass.resources.exceptions;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {

    private static final long serialVersionUID = 4004372234367787438L;

    @Getter
    private List<FieldMessage> errors;

    public ValidationError(final Integer status, final String message, final Long timestamp) {
        super(status, message, timestamp);
        this.errors = new ArrayList<>();
    }

    public void addError(final String fieldName, final String message) {
        this.errors.add(new FieldMessage(fieldName, message));
    }
}
