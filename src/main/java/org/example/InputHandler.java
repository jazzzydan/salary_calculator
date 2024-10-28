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

        boolean usePension = true;
        while (true) {
            try {
                System.out.print("Do you want to use pension in calculation (Y / N): ");
                String useCaseInput = scanner.nextLine().toUpperCase();
                if (useCaseInput.equals("Y")) {
                    break;
                } else if (useCaseInput.equals("N")) {
                    usePension = false;
                    break;
                } else {
                    System.out.println("Invalid input. Please enter 'Y' or 'N'.");
                }
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
        }

        boolean useUnemployment = true;
        while (true) {
            try {
                System.out.print("Do you want to use unemployment tax in calculation (Y / N): ");
                String unemploymentInput = scanner.nextLine().toUpperCase();
                if (unemploymentInput.equals("Y")) {
                    break;
                } else if (unemploymentInput.equals("N")) {
                    useUnemployment = false;
                    break;
                } else {
                    System.out.println("Invalid input. Please enter 'Y' or 'N'.");
                }
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
        }

        boolean useTaxFreeIncome = true;
        while (true) {
            try {
                System.out.print("Do you want to use tax-free income in calculation (Y / N): ");
                String taxFreeInput = scanner.nextLine().toUpperCase();
                if (taxFreeInput.equals("Y")) {
                    break;
                } else if (taxFreeInput.equals("N")) {
                    useTaxFreeIncome = false;
                    break;
                } else {
                    System.out.println("Invalid input. Please enter 'Y' or 'N'.");
                }
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
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
