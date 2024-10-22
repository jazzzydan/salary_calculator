package org.example;

import java.util.Scanner;

import static org.example.Salary.Type.*;

public class InputHandler {

    Calculator calc = new Calculator();
    public void processInput() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter Salary type (NET, GROSS, TOTAL): ");
        String typeInput = scanner.nextLine().toUpperCase();
        Salary.Type type = Salary.Type.valueOf(typeInput);

        //todo: handle wrong input for enum, negative input, wrong input for amount

        System.out.println("Please enter Amount: ");
        String amountInput = scanner.nextLine();
        amountInput = amountInput.replace(",", ".");
        double salaryValue = Double.parseDouble(amountInput.isEmpty() ? "0" : amountInput);
        scanner.close();

        switch (type) {
            case GROSS:
                calc.calculateUsingGross(salaryValue);
                break;
            case NET:
                calc.calculateUsingNet(salaryValue);
                break;
            case TOTAL:
                calc.calculateUsingTotal(salaryValue);
                break;
        }

        System.out.println(String.format("%-30s %-10s %s", "TULEMUS", "EUR", "%"));
        System.out.println(calc.getTotalSalary());
        System.out.println(calc.getGrossSalary());
        System.out.println(calc.getNetSalary());

    }
}
