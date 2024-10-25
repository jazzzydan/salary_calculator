package org.example;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.example.TaxParameters.*;

public abstract class Salary {
    private BigDecimal grossSalary;
    private BigDecimal incomeTax;
    private BigDecimal pensionAmount;
    private BigDecimal unemploymentPaymentAmount;
    private BigDecimal socialTaxAmount;
    private BigDecimal employerUnemploymentPaymentAmount;

    enum Type {
        NET,
        GROSS,
        TOTAL
    }


    public static Salary getNewSalary(BigDecimal salary, Salary.Type type) {
        return switch (type) {
            case NET:
                yield new NetSalary(salary);
            case GROSS:
                yield new GrossSalary(salary);
            case TOTAL:
                yield new TotalSalary(salary);
        };
    }


    public Salary(BigDecimal salary) {
        this.grossSalary = calculateGrossSalary(salary);

        this.pensionAmount = pensionAmount();
    }

    public BigDecimal pensionAmount() {
        return grossSalary.multiply(PENSION_RATE).setScale( 2, RoundingMode.HALF_UP);
    }

    public abstract BigDecimal calculateGrossSalary(BigDecimal salary);

    public BigDecimal getGrossSalary() {
        return grossSalary;
    }

    public void setGrossSalary(BigDecimal grossSalary) {
        this.grossSalary = grossSalary;
    }

    public BigDecimal getPensionAmount() {
        return pensionAmount;
    }

    BigDecimal netSalaryCalculation(BigDecimal grossSalary) {
        BigDecimal amountAfterDeductions = calculateAmountBeforeIncomeTax();
        BigDecimal taxFreeIncome = calculateTaxFreeIncome(grossSalary);
        BigDecimal taxableIncome = amountAfterDeductions.subtract(taxFreeIncome);
        if (taxableIncome.compareTo(BigDecimal.ZERO) < 0) {
            taxableIncome = BigDecimal.ZERO;
        }
        BigDecimal incomeTax = taxableIncome.multiply(INCOME_TAX_RATE);
        return amountAfterDeductions.subtract(incomeTax).setScale(2, RoundingMode.HALF_UP);
        //todo: set to Salary value
    }

    BigDecimal totalSalaryCalculation(BigDecimal grossSalary) {
        return grossSalary.add(totalAdditionsToGrossSalary(grossSalary)).setScale(2, RoundingMode.HALF_UP);
    }

    BigDecimal totalAdditionsToGrossSalary(BigDecimal grossSalary) {
        BigDecimal socialTaxAmount = socialTaxAmount(grossSalary);
        this.socialTaxAmount = socialTaxAmount;
        BigDecimal employerUnemploymentPaymentAmount = employerUnemploymentPaymentAmount(grossSalary);
        this.unemploymentPaymentAmount = employerUnemploymentPaymentAmount;
        return socialTaxAmount.add(employerUnemploymentPaymentAmount);
    }

    BigDecimal calculateAmountBeforeIncomeTax() {
        BigDecimal pensionAmount = pensionAmount(grossSalary);
        this.pensionAmount = pensionAmount;
        BigDecimal unemploymentPaymentAmount = employeeUnemploymentPaymentAmount(grossSalary);
        this.unemploymentPaymentAmount = unemploymentPaymentAmount;
        BigDecimal totalDeductions = pensionAmount.add(unemploymentPaymentAmount);
        return grossSalary.subtract(totalDeductions);
    }

    BigDecimal calculateTaxFreeIncome(BigDecimal grossSalary) {
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
        return String.format("%-35s %-10s", "", grossSalary);
    }

//    public String toString() {
//        return String.format("%-30s %-10s %s", "", salary, "XXX");
//    }
//    public String toString() {
//        return String.format("%-30s %-10s %s", "", salary, "XXX");
//    }
//    public String toString() {
//        return String.format("%-30s %-10s %s", "", salary, "XXX");
//    }

//    private BigDecimal pensionAmount;
//    private BigDecimal unemploymentPaymentAmount;
//    private BigDecimal socialTaxAmount;
//    private BigDecimal employerUnemploymentPaymentAmount;

}
