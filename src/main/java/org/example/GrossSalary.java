package org.example;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class GrossSalary {

    private double grossSalary;

    public void setGrossSalary(double grossSalary) {
        this.grossSalary = grossSalary;
    }

    public BigDecimal calculateTaxFreeIncome(double grossSalary) {
        if (grossSalary < 1200.00) {
            return BigDecimal.valueOf(654).setScale(2, RoundingMode.HALF_UP);
        } else if (grossSalary < 2100.00) {
            double taxFreeIncome = (654.00 - 0.72667 * (grossSalary - 1200.00));
            return BigDecimal.valueOf(taxFreeIncome).setScale(2, RoundingMode.HALF_UP);
        }
        return BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public String toString() {
        return String.format("%-30s %-10s %s", "Brutopalk:", grossSalary, "100");
    }
}
