package org.example;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.example.TaxParameters.*;

public class TotalSalary extends Salary {

    public TotalSalary(BigDecimal totalSalary) {
        super(totalSalary);
    }

    @Override
    public BigDecimal calculateGrossSalary(BigDecimal totalSalary) {
        BigDecimal socialTax = socialTaxAmountReverse(totalSalary);
        BigDecimal employerUnemploymentPaymentAmount = employerUnemploymentPaymentAmountReverse(totalSalary);
        BigDecimal totalDeductions = socialTax.add(employerUnemploymentPaymentAmount);
        return totalSalary.subtract(totalDeductions).setScale(2, RoundingMode.HALF_UP);
    }
}
