package org.example;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.example.TaxParameters.*;

public abstract class Salary {

    private BigDecimal netSalary;
    private BigDecimal incomeTax;
    private BigDecimal pensionAmount;
    private BigDecimal employeeUnemploymentPaymentAmount;
    private BigDecimal grossSalary;
    private BigDecimal employerUnemploymentPaymentAmount;
    private BigDecimal socialTaxAmount;
    private BigDecimal totalSalary;

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
        this.socialTaxAmount = socialTaxAmount();
        this.employerUnemploymentPaymentAmount = employerUnemploymentPaymentAmount();
        this.totalSalary = totalSalaryCalculation();
        this.pensionAmount = pensionAmount();
        this.employeeUnemploymentPaymentAmount = employeeUnemploymentPaymentAmount();
        //incomeTax
        this.netSalary = netSalaryCalculation(salary);
    }

    public BigDecimal pensionAmount() {
        return grossSalary.multiply(PENSION_RATE).setScale( 2, RoundingMode.HALF_UP);
    }

    public BigDecimal employeeUnemploymentPaymentAmount() {
        return grossSalary.multiply(EMPLOYEE_UNEMPLOYMENT_RATE).setScale( 2, RoundingMode.HALF_UP);
    }

    public BigDecimal employerUnemploymentPaymentAmount() {
        return grossSalary.multiply(EMPLOYER_UNEMPLOYMENT_TAX_RATE).setScale( 2, RoundingMode.HALF_UP);
    }

    public BigDecimal socialTaxAmount() {
        return grossSalary.multiply(SOCIAL_TAX_RATE).setScale( 2, RoundingMode.HALF_UP);
    }

    public abstract BigDecimal calculateGrossSalary(BigDecimal salary);

    public BigDecimal getGrossSalary() {
        return grossSalary;
    }

    BigDecimal netSalaryCalculation(BigDecimal grossSalary) {
        BigDecimal amountAfterDeductions = calculateAmountBeforeIncomeTax();
        BigDecimal taxFreeIncome = calculateTaxFreeIncome(grossSalary);
        BigDecimal taxableIncome = amountAfterDeductions.subtract(taxFreeIncome);
        if (taxableIncome.compareTo(BigDecimal.ZERO) < 0) {
            taxableIncome = BigDecimal.ZERO;
        }
        BigDecimal incomeTax = taxableIncome.multiply(INCOME_TAX_RATE);
        this.incomeTax = incomeTax;
        return amountAfterDeductions.subtract(incomeTax).setScale(2, RoundingMode.HALF_UP);
    }

    BigDecimal totalSalaryCalculation() {
        return grossSalary.add(totalAdditionsToGrossSalary()).setScale(2, RoundingMode.HALF_UP);
    }

    BigDecimal totalAdditionsToGrossSalary() {
        BigDecimal socialTaxAmount = socialTaxAmount();
        BigDecimal employerUnemploymentPaymentAmount = employerUnemploymentPaymentAmount();
        return socialTaxAmount.add(employerUnemploymentPaymentAmount);
    }

    BigDecimal calculateAmountBeforeIncomeTax() {
        BigDecimal unemploymentPaymentAmount = employeeUnemploymentPaymentAmount();
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
                .append(String.format("%-35s %-10s", "Tööandja kulu kokku (palgafond):", totalSalary))
                .append(System.lineSeparator())
                .append(String.format("%-35s %-10s", "Sotsiaalmaks:", socialTaxAmount))
                .append(System.lineSeparator())
                .append(String.format("%-35s %-10s", "Töötuskindlustusmakse (tööandja):", employerUnemploymentPaymentAmount))
                .append(System.lineSeparator())
                .append(String.format("%-35s %-10s", "Brutopalk:", grossSalary))
                .append(System.lineSeparator())
                .append(String.format("%-35s %-10s", "Kogumispension (II sammas):", pensionAmount))
                .append(System.lineSeparator())
                .append(String.format("%-35s %-10s", "Töötuskindlustusmakse (töötaja):", employeeUnemploymentPaymentAmount))
                .append(System.lineSeparator())
                .append(String.format("%-35s %-10s", "Tulumaks:", incomeTax.setScale(2,RoundingMode.HALF_UP)))
                .append(System.lineSeparator())
                .append(String.format("%-35s %-10s", "Netopalk:", netSalary));
        return table.toString();
    }
}
