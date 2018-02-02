package io.toro.calculator;

import io.toro.calculator.Model.Expression;
import io.toro.calculator.Exceptions.CannotDivideByZeroException;
import io.toro.calculator.Services.Calculator;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public abstract class OperationsTest {


    protected Calculator operations;

    @Test
    public void testAdd() throws Exception {
        assertEquals( 4.0,operations.add( 1.0, 3.0 ),0.1 );
    }

    @Test
    public void testSubtract() throws Exception {
        assertEquals( 3.0, operations.subtract( 6.0, 3.0 ), 0.1 );
    }

    @Test
    public void testMultiply() throws Exception {
        assertEquals(  3.0, operations.multiply( 1.0, 3.0 ), 0.1);
    }

    @Test
    public void testDivide() throws Exception {
        assertEquals( 4.0, operations.divide( 16.0, 4.0 ) , 0.1 );

    }

    @Test
    public void testCalculateExpression() throws Exception {
        assertEquals( 17.9,  operations.multiExpression(new Expression("10-3*5+3/2+24-1*2.6", 0d, 0d)), 0.10  );
    }

    @Test
    public void testNegativeParameter1() throws Exception {
        assertEquals( -20,  operations.multiExpression(new Expression("10*(-2)", 0d, 0d)), 0.10  );
    }

    @Test
    public void testNegativeParameter2() throws Exception {
        assertEquals( -2,  operations.multiExpression(new Expression("8/(-4)+2*(2.5)-5", 0d, 0d)), 0.10  );

    }

    @Test
    public void testNegativeParameter3() throws Exception {
        assertEquals( 6.62, operations.multiExpression(new Expression("10+4-(-0.12)-3*5/2", 0d, 0d)), 0.10 );
    }

    @Test(expected = CannotDivideByZeroException.class)
    public void testDivideByZero() throws Exception {
        operations.multiExpression(new Expression( "10/0" , 0d, 0d));
    }

    @Test
    public void testXandYvariables1() throws Exception {
        assertEquals( 16, operations.multiExpression(new Expression( "5x + 2y", 2d, 3d )), 0.10 );
    }

    @Test
    public void testXandYvariables2() throws Exception {
        assertEquals( -7.6, operations.multiExpression(new Expression("x+5-3y", 1.2d, 4.6d )), 0.10 );
    }

    @Test
    public void testXAndYVariables3() throws Exception {
        assertEquals( 100, operations.multiExpression(new Expression("x/y*2x", 10d, 2d )), 0.10 );
    }

    @Test
    public void testXandYvariables4() throws Exception {
        assertEquals( 3, operations.multiExpression(new Expression("x+y-x*y/x", 3d, 2d )), 0.10 );
    }

    @Test
    public void testXandYvariables5() throws Exception {
        assertEquals( -3.34, operations.multiExpression(new Expression("10+4x-(-0.12y)-3*5/2", -1.14, -2d )), 0.10 );
    }

    @Test
    public void testXandYvariables6() throws Exception {
        assertEquals( -3.34, operations.multiExpression(new Expression("10+4*(-1.14)-(-0.12)*3*5/2", 71.14, 72d )), 0.10 );
    }

}
