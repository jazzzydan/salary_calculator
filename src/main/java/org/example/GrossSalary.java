package org.example;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.example.TaxParameters.*;

public class GrossSalary extends Salary {

    public BigDecimal netSalaryCalculation(BigDecimal grossSalary) {
        BigDecimal amountAfterDeductions = calculateAmountBeforeIncomeTax(grossSalary);
        BigDecimal taxFreeIncome = calculateTaxFreeIncome(grossSalary);
        BigDecimal taxableIncome = amountAfterDeductions.subtract(taxFreeIncome);
        if (taxableIncome.compareTo(BigDecimal.ZERO) < 0) {
            taxableIncome = BigDecimal.ZERO;
        }
        BigDecimal incomeTax = taxableIncome.multiply(INCOME_TAX_RATE);
        return amountAfterDeductions.subtract(incomeTax).setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal totalSalaryCalculation(BigDecimal grossSalary) {
        return  grossSalary.add(totalAdditionsToGrossSalary(grossSalary)).setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal calculateTaxFreeIncome(BigDecimal grossSalary) {
        BigDecimal lowerLimit = new BigDecimal("1200.00");
        BigDecimal upperLimit = new BigDecimal("2100.00");
        BigDecimal baseTaxFreeIncome = new BigDecimal("654.00");
        BigDecimal deductionRate = new BigDecimal("0.72667");

        if (grossSalary.compareTo(lowerLimit) < 0) {
            return baseTaxFreeIncome.setScale(2, RoundingMode.HALF_UP);
        } else if (grossSalary.compareTo(upperLimit) < 0) {
            BigDecimal taxableAmount = grossSalary.subtract(lowerLimit);
            BigDecimal taxFreeIncome = baseTaxFreeIncome.subtract(deductionRate.multiply(taxableAmount));
            return taxFreeIncome.setScale(2, RoundingMode.HALF_UP);
        }
        return BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
    }

    BigDecimal totalAdditionsToGrossSalary(BigDecimal grossSalary) {
        BigDecimal socialTaxAmount = socialTaxAmount(grossSalary);
        BigDecimal employerUnemploymentPaymentAmount = employerUnemploymentPaymentAmount(grossSalary);
        return socialTaxAmount.add(employerUnemploymentPaymentAmount);
    }

    BigDecimal calculateAmountBeforeIncomeTax(BigDecimal grossSalary) {
        BigDecimal pensionAmount = pensionAmount(grossSalary);
        BigDecimal unemploymentPaymentAmount = employeeUnemploymentPaymentAmount(grossSalary);
        BigDecimal totalDeductions = pensionAmount.add(unemploymentPaymentAmount);
        return grossSalary.subtract(totalDeductions);
    }

    @Override
    public String toString() {
        return String.format("%-30s %-10s %s", "Brutopalk:", getSalary(), "100");
    }

}
