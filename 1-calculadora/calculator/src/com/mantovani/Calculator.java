package com.mantovani;

public class Calculator {

    private Calculator() {}

    public static double calculate(double firstOperator, double secondOperator, Operation operation) {
        switch (operation) {
            case ADDITION:
                return firstOperator + secondOperator;
            case SUBTRACTION:
                return firstOperator - secondOperator;
            case MULTIPLICATION:
                return firstOperator * secondOperator;
            case DIVISION:
            default:
                return firstOperator / secondOperator;
        }
    }
}
