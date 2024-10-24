package org.example;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.example.TaxParameters.*;

public class NetSalary extends Salary {

    public BigDecimal calculateTaxFreeIncome(BigDecimal netSalary) {
        if (netSalary.compareTo(NET_LOWER_LIMIT) <= 0) {
            return BASE_TAX_FREE_INCOME.setScale(2, RoundingMode.HALF_UP);
        } else if (netSalary.compareTo(NET_UPPER_LIMIT) <= 0) {
            BigDecimal upperMinusNetSalary = NET_UPPER_LIMIT.subtract(netSalary);
            BigDecimal upperMinusNetSalaryMultiplyBaseTax = BASE_TAX_FREE_INCOME.multiply(upperMinusNetSalary);
            BigDecimal upperNetLimitMinusLower = NET_UPPER_LIMIT.subtract(NET_LOWER_LIMIT);
            return upperMinusNetSalaryMultiplyBaseTax
                    .divide(upperNetLimitMinusLower, 2, RoundingMode.HALF_UP)
                    .setScale(2, RoundingMode.HALF_UP);
        }
        return BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal grossSalaryCalculation(BigDecimal netSalary) {
        BigDecimal taxFreeIncome = calculateTaxFreeIncome(netSalary);
        BigDecimal incomeTax = (netSalary.subtract(taxFreeIncome))
                .divide(BigDecimal.valueOf(4), 9, RoundingMode.HALF_UP);
        BigDecimal taxableIncome = incomeTax.multiply(BigDecimal.valueOf(5));
        BigDecimal amountBeforeIncomeTax = taxableIncome.add(taxFreeIncome);
        return amountBeforeIncomeTax.multiply(GROSS_SALARY_CONVERSION_RATE).setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public String toString() {
        return String.format("%-30s %-10s %s", "Netopalk:", getSalary(), "XXX");
    }
}
