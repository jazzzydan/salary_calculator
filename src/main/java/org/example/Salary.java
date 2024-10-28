package org.example;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.example.TaxParameters.*;

public abstract class Salary {

    private final BigDecimal netSalary;
    private BigDecimal incomeTax;
    private final BigDecimal pensionAmount;
    private final BigDecimal employeeUnemploymentPaymentAmount;
    private final BigDecimal grossSalary;
    private final BigDecimal employerUnemploymentPaymentAmount;
    private final BigDecimal socialTaxAmount;
    private final BigDecimal totalSalary;

    private boolean usePension;
    private boolean useUnemployment;
    private boolean useTaxFreeIncome;

    enum Type {
        NET,
        GROSS,
        TOTAL
    }

    public static Salary getNewSalary(BigDecimal salary, Salary.Type type, InputConditions conditions) {
        return switch (type) {
            case NET:
                yield new NetSalary(salary, conditions);
            case GROSS:
                yield new GrossSalary(salary, conditions);
            case TOTAL:
                yield new TotalSalary(salary, conditions);
        };
    }

    public Salary(BigDecimal salary, InputConditions conditions) {
        this.grossSalary = calculateGrossSalary(salary);
        this.socialTaxAmount = socialTaxAmount();
        this.employerUnemploymentPaymentAmount = employerUnemploymentPaymentAmount();
        this.totalSalary = totalSalaryCalculation();

        this.usePension = conditions.isUsePension();
        this.useUnemployment = conditions.isUseUnemployment();
        this.useTaxFreeIncome = conditions.isUseTaxFreeIncome();

        this.pensionAmount = pensionAmount();
        this.employeeUnemploymentPaymentAmount = employeeUnemploymentPaymentAmount();
        this.netSalary = netSalaryCalculation();
    }

    public BigDecimal pensionAmount() {
        if (usePension) {
            return grossSalary.multiply(PENSION_RATE).setScale(2, RoundingMode.HALF_UP);
        }
        return BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal employeeUnemploymentPaymentAmount() {
        if (useUnemployment) {
            return grossSalary.multiply(EMPLOYEE_UNEMPLOYMENT_RATE).setScale(2, RoundingMode.HALF_UP);
        }
        return BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal employerUnemploymentPaymentAmount() {
        return grossSalary.multiply(EMPLOYER_UNEMPLOYMENT_TAX_RATE).setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal socialTaxAmount() {
        return grossSalary.multiply(SOCIAL_TAX_RATE).setScale(2, RoundingMode.HALF_UP);
    }

    public abstract BigDecimal calculateGrossSalary(BigDecimal salary);

    BigDecimal netSalaryCalculation() {
        BigDecimal amountAfterDeductions = calculateAmountBeforeIncomeTax();
        BigDecimal taxFreeIncome = calculateTaxFreeIncome();
        BigDecimal taxableIncome = calculateTaxableIncome(amountAfterDeductions, taxFreeIncome);
        BigDecimal incomeTax = taxableIncome.multiply(INCOME_TAX_RATE);
        this.incomeTax = incomeTax;
        return amountAfterDeductions.subtract(incomeTax).setScale(2, RoundingMode.HALF_UP);
    }

    private BigDecimal calculateTaxableIncome(BigDecimal amountAfterDeductions, BigDecimal taxFreeIncome) {
        BigDecimal taxableIncome = amountAfterDeductions.subtract(taxFreeIncome);
        if (calculatedTaxableIncomeIsPositive(taxableIncome)) {
            return taxableIncome;
        } else {
            return BigDecimal.ZERO;
        }
    }

    private boolean calculatedTaxableIncomeIsPositive(BigDecimal taxableIncome) {
        return taxableIncome.compareTo(BigDecimal.ZERO) > 0;
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

    BigDecimal calculateTaxFreeIncome() {
        if (useTaxFreeIncome) {
            if (grossSalary.compareTo(GROSS_LOWER_LIMIT) <= 0) {
                return BASE_TAX_FREE_INCOME.setScale(2, RoundingMode.HALF_UP);
            } else if (grossSalary.compareTo(GROSS_UPPER_LIMIT) <= 0) {
                BigDecimal taxableAmount = grossSalary.subtract(GROSS_LOWER_LIMIT);
                BigDecimal taxFreeIncome = BASE_TAX_FREE_INCOME.subtract(TAX_FREE_INCOME_CONVERSION_RATE.multiply(taxableAmount));
                return taxFreeIncome.setScale(2, RoundingMode.HALF_UP);
            }
            return BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        }
        return BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public String toString() {
        var table = new StringBuilder();
        String[][] rows = {
                {"TULEMUS", "EUR"},
                {"Tööandja kulu kokku (palgafond):", totalSalary.toString()},
                {"Sotsiaalmaks:", socialTaxAmount.toString()},
                {"Töötuskindlustusmakse (tööandja):", employerUnemploymentPaymentAmount.toString()},
                {"Brutopalk:", grossSalary.toString()},
                {"Kogumispension (II sammas):", pensionAmount.toString()},
                {"Töötuskindlustusmakse (töötaja):", employeeUnemploymentPaymentAmount.toString()},
                {"Tulumaks:", incomeTax.setScale(2, RoundingMode.HALF_UP).toString()},
                {"Netopalk:", netSalary.toString()}
        };

        for (String[] row : rows) {
            table.append(String.format("%-35s %-10s", row[0], row[1]))
                    .append(System.lineSeparator());
        }
        return table.toString();
    }
}
