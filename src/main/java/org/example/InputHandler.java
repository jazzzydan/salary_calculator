package org.example;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class InputHandler {

   private final Calculator calc = new Calculator();

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

        switch (type) {
            case NET:
                calc.calculateUsingNet(salaryValue);
                break;
            case GROSS:
                calc.calculateUsingGross(salaryValue);
                break;
            case TOTAL:
                calc.calculateUsingTotal(salaryValue);
                break;
            default:
                throw new IllegalArgumentException("Unknown salary type: " + type);
        }

        //todo: finish table

        System.out.println(calc.getSalary());

//        System.out.println(String.format("%-35s %-10s %s", "TULEMUS", "EUR", "%"));
//        System.out.println(calc.getTotalSalary());
//        // Sotsiaalmaks:
//        // Töötuskindlustusmakse (tööandja):
//        System.out.println(calc.getGrossSalary());
//        System.out.println(calc.getSalary().pensionAmountToString());
//        System.out.println(calc.getSalary().unemploymentPaymentAmountToString());
////        System.out.println(calc.getSalary().netTaxAmountToString());
//        System.out.println(calc.getNetSalary());

    }
}
