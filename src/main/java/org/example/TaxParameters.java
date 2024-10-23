package org.example;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TaxParameters {

    static final BigDecimal PENSION_RATE = new BigDecimal("0.020");
    static final BigDecimal EMPLOYEE_UNEMPLOYMENT_RATE = new BigDecimal ("0.016");
    static final BigDecimal INCOME_TAX_RATE = new BigDecimal("0.200");
    static final BigDecimal SOCIAL_TAX_RATE = new BigDecimal("0.330");
    static final BigDecimal EMPLOYER_UNEMPLOYMENT_TAX_RATE = new BigDecimal("0.008");

    public static BigDecimal pensionAmount(BigDecimal grossSalary) {
        return grossSalary.multiply(PENSION_RATE).setScale( 2, RoundingMode.HALF_UP);
    }

    public static BigDecimal employeeUnemploymentPaymentAmount(BigDecimal grossSalary) {
        return grossSalary.multiply(EMPLOYEE_UNEMPLOYMENT_RATE).setScale( 2, RoundingMode.HALF_UP);
    }

    public static BigDecimal socialTaxAmount(BigDecimal grossSalary) {
        return grossSalary.multiply(SOCIAL_TAX_RATE).setScale( 2, RoundingMode.HALF_UP);
    }

    public static BigDecimal employerUnemploymentPaymentAmount(BigDecimal grossSalary) {
        return grossSalary.multiply(EMPLOYER_UNEMPLOYMENT_TAX_RATE).setScale( 2, RoundingMode.HALF_UP);
    }
}
