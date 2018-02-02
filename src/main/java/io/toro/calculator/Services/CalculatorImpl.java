package io.toro.calculator.Services;

import io.toro.calculator.Model.Expression;
import io.toro.calculator.Exceptions.CannotDivideByZeroException;

public class CalculatorImpl implements Calculator {

    @Override
    public Double add( Double x, Double y ) {
        return x+y;
    }

    @Override
    public Double subtract( Double x, Double y ) {
        return x-y;
    }

    @Override
    public Double multiply( Double x, Double y ) {
        return x*y;
    }

    @Override
    public Double divide( Double x, Double y ) throws CannotDivideByZeroException{
        if(y==0)
            throw new CannotDivideByZeroException( "Cannot Divide by Zero!!!" );
        return x/y;
    }

    @Override
    public Double multiExpression( Expression givenExpression) throws CannotDivideByZeroException {
        return new Evaluator( ).apply( givenExpression );
    }

}
