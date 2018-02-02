package io.toro.calculator.Services;

import java.util.ArrayList;

import java.util.List;

import java.util.function.Function;

import io.toro.calculator.Model.Expression;
import io.toro.calculator.Exceptions.CannotDivideByZeroException;
import io.toro.calculator.Services.ExpressionParser.ExpressionParser;

class Evaluator implements Function< Expression, Double> {

    private List<String> numbers = new ArrayList<>();

    private List<String> operations = new ArrayList<>();

    private Integer index;

    Evaluator(){}

    @Override
    public Double apply( Expression expression) {
        establishParser( expression );

        Double finalAnswer = null;
        try {
            finalAnswer = evaluateExpression();
        } catch ( CannotDivideByZeroException e ) {
            e.printStackTrace();
        }
        expression.setAnswer( finalAnswer );
            return finalAnswer;


    }

    private void establishParser( Expression expression ) {
        ExpressionParser parser = passContentsToParser( expression );
        numbers = parser.getInfixVariables();
        operations = parser.getInfixOperations();
    }

    private ExpressionParser passContentsToParser( Expression expression ) {
        ExpressionParser parser = new ExpressionParser();
        parser.setExpression( expression );
        parser.setLists();
        parser.fixVariables();
        return parser;
    }

    private Double evaluateExpression() throws CannotDivideByZeroException {
        return executeOperations( operations );
    }

    private Double executeOperations( List<String> operationsToBeExtracted) throws CannotDivideByZeroException {
        if ( performMDAS( operationsToBeExtracted ) )
            return getAnswer();
        return getAnswer();
    }

    private boolean performMDAS( List<String> operationsToBeExtracted ) throws CannotDivideByZeroException {
        return checkIfMultiplicationOrDivision( operationsToBeExtracted ) || checkIfAdditionOrSubtraction(
                operationsToBeExtracted );
    }

    private boolean checkIfAdditionOrSubtraction( List<String> operationsToBeExtracted )
            throws CannotDivideByZeroException {
        index = 0;
        for(String operation: operationsToBeExtracted )
            if ( lookForAdditionOrSubtractionOperands( operation ) )
                return true;
        return false;
    }

    private boolean lookForAdditionOrSubtractionOperands( String operation ) throws CannotDivideByZeroException {
        if ( operation.equals( "+" )|| operation.equals( "-" ))
            hasAddSubOperands( operation );
        return checkNumberSize();
    }

    private void hasAddSubOperands( String operation ) throws CannotDivideByZeroException {
        Double newValue;
        if( operation.equals( "+" ))
            newValue =  parsedNumber( index ) + parsedNumber( index + 1 );
        else
            newValue = parsedNumber( index ) - parsedNumber( index + 1 );
        fixExpression( newValue );
    }

    private boolean checkIfMultiplicationOrDivision( List<String> operationsToBeExtracted )
            throws CannotDivideByZeroException {
        index = 0;
        for(String operation: operationsToBeExtracted ){
            if ( operation.equals( "*" ) || operation.equals( "/" ))
                hasMulDivOperands( operation );
            if ( checkNumberSize() )
                return true;
        }
        return false;
    }

    private void hasMulDivOperands( String operation ) throws CannotDivideByZeroException {
        Double newValue;
        if( operation.equals( "*" ))
            newValue = parsedNumber( index ) * parsedNumber( index + 1 );
        else{
            if( parsedNumber( index+1)==0)
                throw new CannotDivideByZeroException("Cannot Divide by Zero!!!");
            newValue = parsedNumber( index ) / parsedNumber( index + 1 );
        }
        fixExpression( newValue );
    }

    private boolean checkNumberSize() {
        if(numbers.size() == 1)
            return true;
        index++;
        return false;
    }

    private void fixExpression( Double newValue ) throws CannotDivideByZeroException {
        alterInfixListVariables( newValue );
        executeOperations( operations );
    }

    private void alterInfixListVariables( Double newValue ) {
        numbers.remove( index + 1 );
        numbers.remove( index + 0); //note: causes errors if written as index alone. i'm not stupid. dont know why.
        numbers.add( index + 0, newValue.toString() );
        operations.remove( index + 0);//note: causes errors if written as index alone. i'm not stupid. dont know why.
    }

    private Double parsedNumber( Integer index ) {
        return Double.parseDouble( numbers.get( index ) );
    }

    private double getAnswer() {
        return Double.parseDouble( numbers.get( 0 ) );
    }

}