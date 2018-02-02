package io.toro.calculator.Exceptions;

public class CannotDivideByZeroException extends Exception {

    public CannotDivideByZeroException(String message) {
        super(message);
    }

    public CannotDivideByZeroException(String message, Throwable throwable) {
        super(message, throwable);
    }

}