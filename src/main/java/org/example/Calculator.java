package org.example;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Calculator {

    static final BigDecimal PENSION_RATE = new BigDecimal("0.020"); // substitute BigDecimal with var
    static final BigDecimal UNEMPLOYMENT_RATE = new BigDecimal("0.016");
    static final BigDecimal INCOME_TAX_RATE = new BigDecimal("0.200");

    private BigDecimal grossSalary;
    private BigDecimal taxFreeIncome;
    private BigDecimal incomeTax;

    BigDecimal pensionAmount(BigDecimal grossSalary) {
        return grossSalary.multiply(PENSION_RATE).setScale( 2, RoundingMode.HALF_UP);
    }

    BigDecimal unemploymentPaymentAmount(BigDecimal grossSalary) {
        return grossSalary.multiply(UNEMPLOYMENT_RATE).setScale( 2, RoundingMode.HALF_UP);
    }

    public BigDecimal subtractPensionAndUnemployment(BigDecimal grossSalary) {
        return grossSalary.subtract((pensionAmount(grossSalary).add(unemploymentPaymentAmount(grossSalary)))).
                setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal netSalaryCalculation(BigDecimal grossSalary, BigDecimal taxFreeIncome) {
        this.grossSalary = grossSalary;
        this.taxFreeIncome = taxFreeIncome;
        BigDecimal amountForCalculation = subtractPensionAndUnemployment(grossSalary);
        BigDecimal taxableIncome = amountForCalculation.subtract(taxFreeIncome);
        BigDecimal incomeTax = taxableIncome.multiply(INCOME_TAX_RATE);
        this.incomeTax = incomeTax;
        return amountForCalculation.subtract(incomeTax).setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public String toString() {
        String header = String.format("%-15s %-10s %s", "TULEMUS", "EUR", "%");
        StringBuilder outputTable = new StringBuilder();
        outputTable.append(header).append("\n")
                .append(String.format("%-15s %-10.2f %-5s", "Brutopalk:", grossSalary, "100"))
                .append("\n")
                .append(String.format("%-15s %-10.2f %-5s", "Kogumispension:", pensionAmount(grossSalary),
                        PENSION_RATE.multiply(new BigDecimal("100")).setScale(2, RoundingMode.HALF_UP)))
                .append("\n")
                .append(String.format("%-15s %-10.2f %-5s", "Töötuskindlustusmakse:", unemploymentPaymentAmount(grossSalary),
                        UNEMPLOYMENT_RATE.multiply(new BigDecimal("100")).setScale(2, RoundingMode.HALF_UP)))
                .append("\n")
                .append(String.format("%-15s %-10.2f %-5s", "Tulumaks:", incomeTax,
                        INCOME_TAX_RATE.multiply(new BigDecimal("100")).setScale(2, RoundingMode.HALF_UP)))
                .append("\n")
                .append(String.format("%-15s %-10.2f %-5s", "Netopalk:", netSalaryCalculation(grossSalary, taxFreeIncome),
                        "77.1"));
        return outputTable.toString();
    }

}
