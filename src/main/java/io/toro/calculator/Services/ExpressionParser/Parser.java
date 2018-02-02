package io.toro.calculator.Services.ExpressionParser;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface Parser {

    void setLists();

    List<String> getInfixVariables();

    List<String> getInfixOperations();

}
