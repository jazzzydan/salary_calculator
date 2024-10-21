package org.example;

import java.math.BigDecimal;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Calculator calc = new Calculator();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter Gross Salary: ");
        String input = scanner.nextLine();
        input = input.replace(",", "."); // Replaces commas with dots to allow for decimal numbers in case of mistyped input
        BigDecimal grossSalary = BigDecimal.valueOf(input.isEmpty() ? 0 : Double.parseDouble(input));

        // This is a ternary operator, which acts like a concise if-else statement:
        // It evaluates the boolean expression (input.isEmpty()) and returns one of two values based on the result.

        // input.isEmpty()              // Checks if the input string is empty. If input is "", this returns true, otherwise false.
        // ? 0                          // If the string is empty (input.isEmpty() is true), it returns 0. This is the value when the condition is true.
        // : Double.parseDouble(input)  // If the string is NOT empty (input.isEmpty() is false),

        // it converts the input string to a double using Double.parseDouble(input).
        // This method assumes input is a valid numeric string and converts it into a double.

        System.out.println("Please enter Tax Free Income: ");
        input = scanner.nextLine();
        input = input.replace(",", "."); // Replaces commas with dots to allow for decimal numbers in case of mistyped input
        BigDecimal taxFreeIncome = BigDecimal.valueOf(input.isEmpty() ? 0 : Double.parseDouble(input));

        System.out.println(calc);

    }

}