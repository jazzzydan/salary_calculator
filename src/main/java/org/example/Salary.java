package org.example;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.example.TaxParameters.*;

public class Salary {
    private BigDecimal salary;

    enum Type {
        NET,
        GROSS,
        TOTAL
    }

    public Salary() {}

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public BigDecimal netSalaryCalculation(BigDecimal grossSalary) {
        BigDecimal amountAfterDeductions = calculateAmountBeforeIncomeTax(grossSalary);
        BigDecimal taxFreeIncome = calculateTaxFreeIncome(grossSalary);
        BigDecimal taxableIncome = amountAfterDeductions.subtract(taxFreeIncome);
        if (taxableIncome.compareTo(BigDecimal.ZERO) < 0) {
            taxableIncome = BigDecimal.ZERO;
        }
        BigDecimal incomeTax = taxableIncome.multiply(INCOME_TAX_RATE);
        return amountAfterDeductions.subtract(incomeTax).setScale(2, RoundingMode.HALF_UP);
        //todo: set to Salary value
    }

    public BigDecimal totalSalaryCalculation(BigDecimal grossSalary) {
        return  grossSalary.add(totalAdditionsToGrossSalary(grossSalary)).setScale(2, RoundingMode.HALF_UP);
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

    public BigDecimal calculateTaxFreeIncome(BigDecimal grossSalary) {
        if (grossSalary.compareTo(GROSS_LOWER_LIMIT) <= 0) {
            return BASE_TAX_FREE_INCOME.setScale(2, RoundingMode.HALF_UP);
        } else if (grossSalary.compareTo(GROSS_UPPER_LIMIT) <= 0) {
            BigDecimal taxableAmount = grossSalary.subtract(GROSS_LOWER_LIMIT);
            BigDecimal taxFreeIncome = BASE_TAX_FREE_INCOME.subtract(TAX_FREE_INCOME_CONVERSION_RATE.multiply(taxableAmount));
            return taxFreeIncome.setScale(2, RoundingMode.HALF_UP);
        }
        return BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public String toString() {
        return String.format("%-30s %-10s %s", "", salary, "XXX");
    }
}
