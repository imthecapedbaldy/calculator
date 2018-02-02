package io.toro.calculator.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.toro.calculator.Model.Expression;
import io.toro.calculator.Exceptions.CannotDivideByZeroException;
import io.toro.calculator.Services.CalculatorImpl;

@RequestMapping("/api/calculator")
@RestController
public class CalculatorController{

    private CalculatorImpl simpleOperations;

    @Autowired
    public CalculatorController( CalculatorImpl simpleOperations ) {
        this.simpleOperations = simpleOperations;
    }

    @GetMapping("add")
    public Double addDigits(Model model, Double x, Double y){
        return simpleOperations.add( x, y );
    }

    @GetMapping("subtract")
    public Double subtractDigits(Double x, Double y){
        return simpleOperations.subtract( x, y );
    }

    @GetMapping("multiply")
    public Double multiplyDigits(Double x, Double y){
        return simpleOperations.multiply( x, y );
    }

    @GetMapping("divide")
    public Double divideDigits(Double x, Double y) throws CannotDivideByZeroException {
        return simpleOperations.divide( x, y );
    }

    @GetMapping("expression")
    public String expressMe(@RequestParam(name = "expression") String givenExpression, Double x, Double y) throws CannotDivideByZeroException {
        return String.valueOf( simpleOperations.multiExpression(new Expression(givenExpression, x, y) ) );
    }
}