package org.example;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.example.TaxParameters.*;

public class TotalSalary extends Salary {

    public BigDecimal grossSalaryCalculation(BigDecimal totalSalary) {
        BigDecimal socialTaxAmount = socialTaxAmountReverse(totalSalary);
        setSocialTaxAmount(socialTaxAmount);
        BigDecimal employerUnemploymentPaymentAmount = employerUnemploymentPaymentAmountReverse(totalSalary);
        setEmployerUnemploymentPaymentAmount(employerUnemploymentPaymentAmount);
        BigDecimal totalDeductions = socialTaxAmount.add(employerUnemploymentPaymentAmount);
        return totalSalary.subtract(totalDeductions).setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public String toString() {
        return String.format("%-30s %-10s %s", "Tööandja kulu kokku:", getSalary(), "XXX");
    }
}
