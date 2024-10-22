package org.example;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.example.TaxParameters.*;

public class Calculator {

    public BigDecimal netSalaryCalculation(double grossSalary) {
        GrossSalary grossSalaryObject = new GrossSalary();

        double amountAfterDeductions = totalDeductions(grossSalary);
        double taxableIncome = amountAfterDeductions - grossSalaryObject.calculateTaxFreeIncome(grossSalary).doubleValue();
        BigDecimal incomeTax = BigDecimal.valueOf(taxableIncome * INCOME_TAX_RATE);
        return BigDecimal.valueOf(amountAfterDeductions).subtract(incomeTax).setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal totalSalaryCalculation(double grossSalary) {
        return BigDecimal.valueOf(grossSalary + totalAddition(grossSalary)).setScale(2, RoundingMode.HALF_UP);
    }

    double totalDeductions(double grossSalary) {
        BigDecimal pensionAmount = pensionAmount(grossSalary);
        BigDecimal unemploymentPaymentAmount = employeeUnemploymentPaymentAmount(grossSalary);
        double totalDeductions = pensionAmount.add(unemploymentPaymentAmount).doubleValue();
        return grossSalary - totalDeductions;
    }

    double totalAddition(double grossSalary) {
        BigDecimal socialTaxAmount = socialTaxAmount(grossSalary);
        BigDecimal employerUnemploymentPaymentAmount = employerUnemploymentPaymentAmount(grossSalary);
        return socialTaxAmount.add(employerUnemploymentPaymentAmount).doubleValue();
    }


//    @Override
//    public String toString() {
//        String header = String.format("%-15s %-10s %s", "TULEMUS", "EUR", "%");
//        StringBuilder outputTable = new StringBuilder();
//        outputTable.append(header).append("\n")
//                .append(String.format("%-15s %-10.2f %-5s", "Brutopalk:", grossSalary, "100"))
//                .append("\n")
//                .append(String.format("%-15s %-10.2f %-5s", "Kogumispension:", pensionAmount(grossSalary),
//                        PENSION_RATE.multiply(new BigDecimal("100")).setScale(2, RoundingMode.HALF_UP)))
//                .append("\n")
//                .append(String.format("%-15s %-10.2f %-5s", "Töötuskindlustusmakse:", unemploymentPaymentAmount(grossSalary),
//                        UNEMPLOYMENT_RATE.multiply(new BigDecimal("100")).setScale(2, RoundingMode.HALF_UP)))
//                .append("\n")
//                .append(String.format("%-15s %-10.2f %-5s", "Tulumaks:", incomeTax,
//                        INCOME_TAX_RATE.multiply(new BigDecimal("100")).setScale(2, RoundingMode.HALF_UP)))
//                .append("\n")
//                .append(String.format("%-15s %-10.2f %-5s", "Netopalk:", netSalaryCalculation(grossSalary, taxFreeIncome),
//                        "77.1"));
//        return outputTable.toString();
//    }

}
