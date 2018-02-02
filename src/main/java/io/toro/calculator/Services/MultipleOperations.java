package io.toro.calculator.Services;

import io.toro.calculator.Exceptions.CannotDivideByZeroException;

public interface MultipleOperations {

    Double calculateExpression(String expression) throws CannotDivideByZeroException;


}
