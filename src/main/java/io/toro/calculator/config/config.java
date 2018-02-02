package io.toro.calculator.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.toro.calculator.Services.CalculatorImpl;

@Configuration
public class config {

    @Bean
    public CalculatorImpl calculatorImpl(){
        return new CalculatorImpl();
    }

}
