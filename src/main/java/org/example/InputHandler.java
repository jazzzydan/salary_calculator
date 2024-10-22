package org.example;

import java.math.BigDecimal;
import java.util.Scanner;

public class InputHandler {

    Calculator calc = new Calculator();
    Scanner scanner = new Scanner(System.in);

    public void processInput() {
        System.out.println("Please enter Gross Salary: ");
        String input = scanner.nextLine();
        // Replaces commas with dots to allow for decimal numbers in case of mistyped input
        input = input.replace(",", ".");
        double grossSalary = Double.parseDouble(input.isEmpty() ? "0" : input);

        System.out.println("Please enter Tax Free Income: ");
        input = scanner.nextLine();
        input = input.replace(",", ".");
        BigDecimal taxFreeIncome = new BigDecimal(input.isEmpty() ? "0" : input);

        calc.netSalaryCalculation(grossSalary);
        System.out.println(calc);
    }
}
