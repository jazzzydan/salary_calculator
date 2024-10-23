package org.example;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.example.TaxParameters.*;

public class GrossSalary extends Salary {

    public void setSalary(double grossSalary) {
      super.setSalary(BigDecimal.valueOf(grossSalary).setScale(2, RoundingMode.HALF_UP));
    }

    public BigDecimal netSalaryCalculation(double grossSalary) {
        double amountAfterDeductions = calculateAmountBeforeIncomeTax(grossSalary);
        double taxableIncome = Math.max(0, amountAfterDeductions - calculateTaxFreeIncome(grossSalary).doubleValue());
        BigDecimal incomeTax = BigDecimal.valueOf(taxableIncome * INCOME_TAX_RATE);

        return BigDecimal.valueOf(amountAfterDeductions).subtract(incomeTax).setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal totalSalaryCalculation(double grossSalary) {
        return BigDecimal.valueOf(grossSalary + totalAdditions(grossSalary)).setScale(2, RoundingMode.HALF_UP);
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

    double totalAdditions(double grossSalary) {
        BigDecimal socialTaxAmount = socialTaxAmount(grossSalary);
        BigDecimal employerUnemploymentPaymentAmount = employerUnemploymentPaymentAmount(grossSalary);
        return socialTaxAmount.add(employerUnemploymentPaymentAmount).doubleValue();
    }

    double calculateAmountBeforeIncomeTax(double grossSalary) {
        BigDecimal pensionAmount = pensionAmount(grossSalary);
        BigDecimal unemploymentPaymentAmount = employeeUnemploymentPaymentAmount(grossSalary);
        double totalDeductions = pensionAmount.add(unemploymentPaymentAmount).doubleValue();
        return grossSalary - totalDeductions;
    }

    @Override
    public String toString() {
        return String.format("%-30s %-10s %s", "Brutopalk:", getSalary(), "100");
    }

}
