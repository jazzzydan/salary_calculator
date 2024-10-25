package org.example;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class InputHandler {

    public void processInput() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter Salary type (NET, GROSS, TOTAL): ");
        String typeInput = scanner.nextLine().toUpperCase();
        Salary.Type type;
        try {
            type = Salary.Type.valueOf(typeInput);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid salary type: " + typeInput);
            return;
        }

        System.out.println("Do you want to use pension in calculation (Y / N): ");
        String pension = scanner.nextLine().toUpperCase();
        boolean usePension = true;
        if (pension.equals("N")) {
            usePension = false;
        }

        System.out.println("Do you want to use unemployment tax in calculation (Y / N): ");
        String unemployment = scanner.nextLine().toUpperCase();
        boolean useUnemployment = true;
        if (unemployment.equals("N")) {
            useUnemployment = false;
        }

        System.out.println("Do you want to use taxfree income in calculation (Y / N): ");
        String taxfree = scanner.nextLine().toUpperCase();
        boolean useTaxFreeIncome = true;
        if (taxfree.equals("N")) {
            useTaxFreeIncome = false;
        }

        System.out.println("Please enter Amount: ");
        String amountInput = scanner.nextLine();
        amountInput = amountInput.replace(",", ".");
        BigDecimal salaryValue = BigDecimal.ZERO;
        try {
            salaryValue = new BigDecimal(amountInput.isEmpty() ? "0" : amountInput)
                    .setScale(2, RoundingMode.HALF_UP);
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format: " + amountInput);
            return;
        }
        scanner.close();
        InputConditions conditions = new InputConditions(usePension, useUnemployment, useTaxFreeIncome);
        Salary salary = Salary.getNewSalary(salaryValue, type, conditions);
        System.out.println(salary);
    }
}
