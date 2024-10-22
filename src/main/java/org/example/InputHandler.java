package org.example;

import java.math.BigDecimal;
import java.util.Scanner;

public class InputHandler {

    Calculator calc = new Calculator();
    Scanner scanner = new Scanner(System.in);

    public void processInput() {
        System.out.println("Please enter Gross Salary: ");
        String input = scanner.nextLine();
        input = input.replace(",", ".");
        double grossSalaryValue = Double.parseDouble(input.isEmpty() ? "0" : input);

        calc.calculateUsingGross(grossSalaryValue);
        System.out.println(String.format("%-30s %-10s %s", "TULEMUS", "EUR", "%"));
        System.out.println(calc.getGrossSalary());
        System.out.println(calc.getNetSalary());
        System.out.println(calc.getTotalSalary());
        //todo: correct null return in grossSalary and netSalary toString
    }
}
