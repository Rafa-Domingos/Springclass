package com.rafael.springclass.services.exceptions;

public class DataIntegrityException extends RuntimeException {

    /**
     * Generated serial version.
     */
    private static final long serialVersionUID = -7605152209637201650L;

    public DataIntegrityException(final String message) {
        super(message);
    }

    public DataIntegrityException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
