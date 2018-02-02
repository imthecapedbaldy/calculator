package io.toro.calculator.Services.ExpressionParser;

import java.util.ArrayList;
import java.util.List;
import java.util.function.DoubleConsumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.toro.calculator.Model.Expression;

public class ExpressionParser implements Parser{

    private Expression expression;

    private final String NUMBERPATTERN = "([0-9.xy])+|\\(\\-([0-9.xy])+\\)";

    private final String OPERATIONPATTERN = "([+*/-])+";

    private List<String> numbers = new ArrayList<>();

    private List<String> operations = new ArrayList<>();

    private int indexOfParenthesis = 0;

    private int parenthesisIndexAssignee = 1;

    public void setExpression( Expression expression ) {
        this.expression = expression;
    }

    @Override
    public void setLists() {
        setNumbersAndOperators();
        ifParenthesisWereFound();
    }

    @Override
    public List<String> getInfixVariables() {
        return numbers;
    }

    @Override
    public List<String> getInfixOperations() {
        return operations;
    }

    private void setNumbersAndOperators() {
        Matcher numberMatch = setMatches( NUMBERPATTERN );
        Matcher operationMatch = setMatches( OPERATIONPATTERN );
        findMatches( numberMatch, numbers );
        findMatches( operationMatch, operations );
    }

    private Matcher setMatches( String stringPattern ) {
        return Pattern.compile( stringPattern ).matcher( expression.getExpression() );
    }

    private void findMatches( Matcher matches, List<String> list ) {
        while(matches.find())
            assignMatches( matches, list );
    }

    private void ifParenthesisWereFound() {
        if(numbers.size() == operations.size())
            operations.remove( indexOfParenthesis );
    }

    public void fixVariables(){
        for ( int index = 0; index < numbers.size(); index++ )
            checkForVariables( index );
    }

    private void checkForVariables( int index ) {
        if(numbers.get( index ).charAt( getLastIndex( numbers, index ) )=='x' )
            reArrangeLists( index, expression.getX(),
                    whatDoesThisDoDevHasForgotten( index ) );
        if(numbers.get( index ).charAt( getLastIndex( numbers, index ) )=='y' )
            reArrangeLists(  index, expression.getY(),
                    whatDoesThisDoDevHasForgotten( index ) );
    }

    private DoubleConsumer whatDoesThisDoDevHasForgotten( int index ) {//my brain hurts idk why i did this will update as soon as understood (*if)
        return (double s) -> numbers.add( index + 1 , String.valueOf( s ) );
    }

    private void reArrangeLists(  int index, double x,  DoubleConsumer c ) {
        c.accept( x );
        if(Character.isDigit( numbers.get( index ).charAt( 0 )))
            refactorLists( index );
        else
            numbers.remove( index + 0 );
    }

    private void refactorLists( int index ) {
        operations.add( index + 0, String.valueOf( '*' ) );
        numbers.add( index + 0,  numbers.get( index ).substring( 0, numbers.get( index).length()-1 ));
        numbers.remove( index + 1 );
    }

    private int getLastIndex( List<String> variables, int index ) {
        return variables.get( index ).length()-1;
    }

    private void foundParenthesis( Matcher matches, List<String> list ) {
        list.add(matches.group().substring( 1, matches.group().length()-1 ));
        parenthesisIndexAssignee = 0;
    }

    private void assignMatches( Matcher matches, List<String> list ) {
        if(matches.group().charAt( 0 )=='(')
            foundParenthesis( matches, list );
        else
            list.add( matches.group() );
        indexOfParenthesis += parenthesisIndexAssignee;
    }
}
