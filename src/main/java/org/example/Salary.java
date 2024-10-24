package org.example;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.example.TaxParameters.*;

public class Salary {
    private BigDecimal salary;

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

    public Salary() {}

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public void setIncomeTax(BigDecimal incomeTax) {
        this.incomeTax = incomeTax;
    }

    public void setPensionAmount(BigDecimal pensionAmount) {
        this.pensionAmount = pensionAmount;
    }

    public void setUnemploymentPaymentAmount(BigDecimal unemploymentPaymentAmount) {
        this.unemploymentPaymentAmount = unemploymentPaymentAmount;
    }

    public void setSocialTaxAmount(BigDecimal socialTaxAmount) {
        this.socialTaxAmount = socialTaxAmount;
    }

    public void setEmployerUnemploymentPaymentAmount(BigDecimal employerUnemploymentPaymentAmount) {
        this.employerUnemploymentPaymentAmount = employerUnemploymentPaymentAmount;
    }

    BigDecimal netSalaryCalculation(BigDecimal grossSalary) {
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

    BigDecimal totalSalaryCalculation(BigDecimal grossSalary) {
        return  grossSalary.add(totalAdditionsToGrossSalary(grossSalary)).setScale(2, RoundingMode.HALF_UP);
    }

    BigDecimal totalAdditionsToGrossSalary(BigDecimal grossSalary) {
        BigDecimal socialTaxAmount = socialTaxAmount(grossSalary);
        this.socialTaxAmount = socialTaxAmount;
        BigDecimal employerUnemploymentPaymentAmount = employerUnemploymentPaymentAmount(grossSalary);
        this.unemploymentPaymentAmount = employerUnemploymentPaymentAmount;
        return socialTaxAmount.add(employerUnemploymentPaymentAmount);
    }

    BigDecimal calculateAmountBeforeIncomeTax(BigDecimal grossSalary) {
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
        var table = new StringBuilder();
        table.append(String.format("%-35s %-10s", "TULEMUS", "EUR"))
                .append(System.lineSeparator())
//                .append(getTotalSalary().toString())
                .append(System.lineSeparator())
//                .append(getGrossSalary().toString())
                .append(System.lineSeparator())
                .append(String.format("%-35s %-10s", "Kogumispension (II sammas):", pensionAmount))
                .append(System.lineSeparator())
                .append(String.format("%-35s %-10s", "Töötuskindlustusmakse (töötaja):", unemploymentPaymentAmount))
                .append(System.lineSeparator())
                .append(String.format("%-35s %-10s", "Tulumaks:", incomeTax))
                .append(System.lineSeparator());
//                .append(getNetSalary().toString());
        return table.toString();
    }
}
