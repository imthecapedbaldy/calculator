package io.toro.calculator.Services;

import io.toro.calculator.Model.Expression;
import io.toro.calculator.Exceptions.CannotDivideByZeroException;

public interface Calculator {

    Double add(Double x, Double y);

    Double subtract(Double x, Double y);

    Double multiply(Double x, Double y);

    Double divide(Double x, Double y) throws CannotDivideByZeroException;

    Double multiExpression(Expression expression) throws CannotDivideByZeroException;

}
