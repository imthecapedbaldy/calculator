package io.toro.calculator;

import io.toro.calculator.Services.CalculatorImpl;

public class SimpleOperationsTest extends OperationsTest{

    public SimpleOperationsTest() {
        this.operations = new CalculatorImpl();
    }
}
