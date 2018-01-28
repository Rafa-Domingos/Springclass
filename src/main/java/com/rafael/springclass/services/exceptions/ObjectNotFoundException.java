package com.rafael.springclass.services.exceptions;

public class ObjectNotFoundException extends RuntimeException {

    /**
     * Generated serial version.
     */
    private static final long serialVersionUID = 4019575721565833670L;

    public ObjectNotFoundException(final String message) {
        super(message);
    }

    public ObjectNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
