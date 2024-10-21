package org.example;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Calculator calc = new Calculator();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter Gross Salary: ");
        double grossSalary = scanner.nextDouble();
        System.out.println("Please enter Tax Free Income: ");
        double taxFreeIncome = scanner.nextDouble();
        System.out.println("Net Salary: " + calc.netSalaryCalculation(grossSalary, taxFreeIncome));

    }

}