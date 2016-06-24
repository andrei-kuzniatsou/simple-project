package org.gradle.exception;

/**
 * Not full required field
 * @author Artsiom_Buyevich
 */
public class NoRequiredAttributesException extends RuntimeException {

    public NoRequiredAttributesException() {
    }

    public NoRequiredAttributesException(String message) {
        super(message);
    }

    public NoRequiredAttributesException(Throwable cause) {
        super(cause);
    }

    public NoRequiredAttributesException(String message, Throwable cause) {
        super(message, cause);
    }
}
