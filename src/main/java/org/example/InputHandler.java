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

        Salary salary = Salary.getNewSalary(salaryValue, type);
        System.out.println(salary);

        //todo: finish table
//        System.out.println(String.format("%-30s %-10s", "TULEMUS", "EUR"));
//        System.out.println(calc.getTotalSalary());
//        // Sotsiaalmaks:
//        // Töötuskindlustusmakse (tööandja):
//        System.out.println(calc.getGrossSalary());
//
//        System.out.println(calc.getSalary().pensionAmountToString());
//        System.out.println(String.format("%-30s %-10s", "Kogumispension (II sammas):", calc.getSalary().getPensionAmount()));
//
//        System.out.println(calc.getNetSalary());

    }
}
