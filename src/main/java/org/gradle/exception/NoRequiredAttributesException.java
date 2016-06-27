package org.gradle.exception;

/**
 * Not full required field
 * @author Artsiom_Buyevich
 */
public class NoRequiredAttributesException extends RuntimeException {

    public NoRequiredAttributesException(String message) {
        super(message);
    }
}
