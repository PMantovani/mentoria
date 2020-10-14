package com.mantovani;

import java.io.*;

public class Main {

    public static void main(String[] args) {
        try {
            double firstOperator = readDouble("Primeiro operador: ");
            double secondOperator = readDouble("Segundo operador: ");
            Operation operator = readOperation("Operador: ");

            double result = Calculator.calculate(firstOperator, secondOperator, operator);
            System.out.format("Resultado: %f", result);
        } catch (IOException e) {
            System.err.println("Error reading input");
        }
    }

    public static double readDouble(String inMsg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            try {
                System.out.print(inMsg);
                String lineRead = br.readLine();
                return Double.parseDouble(lineRead);
            } catch (NumberFormatException e) {
                // Retry if failed to parse
            }
        }
    }

    public static Operation readOperation(String inMsg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.print(inMsg);
            String lineRead = br.readLine();

            switch (lineRead.toLowerCase()) {
                case "addition":
                    return Operation.ADDITION;
                case "subtraction":
                    return Operation.SUBTRACTION;
                case "multiplication":
                    return Operation.MULTIPLICATION;
                case "division":
                    return Operation.DIVISION;
                default:
            }
        }
    }
}
