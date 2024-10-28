package org.example;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.example.TaxParameters.*;

public class NetSalary extends Salary {

    public NetSalary(BigDecimal netSalary, InputConditions conditions) {
        super(netSalary, conditions);
    }

    @Override
    public BigDecimal calculateGrossSalary(BigDecimal netSalary, InputConditions conditions) {
        BigDecimal taxFreeIncome = calculateTaxFreeIncome(netSalary, conditions);
        BigDecimal incomeTax = (netSalary.subtract(taxFreeIncome))
                .divide(BigDecimal.valueOf(4), 4, RoundingMode.HALF_UP);
        BigDecimal taxableIncome = incomeTax.multiply(BigDecimal.valueOf(5));
        BigDecimal amountBeforeIncomeTax = taxableIncome.add(taxFreeIncome);
        //todo: GROSS_SALARY_CONVERSION_RATE divide to two separate calculations and apply InputConditions conditions
        return amountBeforeIncomeTax.multiply(GROSS_SALARY_CONVERSION_RATE).setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal calculateTaxFreeIncome(BigDecimal netSalary, InputConditions conditions) {
        if(conditions.isUseTaxFreeIncome()) {
            if (netIsLessThanLowerLimit(netSalary)) {
                return BASE_TAX_FREE_INCOME.setScale(2, RoundingMode.HALF_UP);
            } else if (netIsLessThanUpperLimit(netSalary)) {
                BigDecimal upperMinusNetSalary = NET_UPPER_LIMIT.subtract(netSalary);
                BigDecimal upperMinusNetSalaryMultiplyBaseTax = BASE_TAX_FREE_INCOME.multiply(upperMinusNetSalary);
                BigDecimal upperNetLimitMinusLower = NET_UPPER_LIMIT.subtract(NET_LOWER_LIMIT);
                return upperMinusNetSalaryMultiplyBaseTax
                        .divide(upperNetLimitMinusLower, 2, RoundingMode.HALF_UP)
                        .setScale(2, RoundingMode.HALF_UP);
            }
            return BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        }
        return BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
    }

    private boolean netIsLessThanUpperLimit(BigDecimal netSalary) {
        return netSalary.compareTo(NET_UPPER_LIMIT) <= 0;
    }

    private boolean netIsLessThanLowerLimit(BigDecimal netSalary) {
        return netSalary.compareTo(NET_LOWER_LIMIT) <= 0;
    }
}
