package org.example;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TaxParameters {

    static final double PENSION_RATE = 0.020;
    static final double EMPLOYEE_UNEMPLOYMENT_RATE = 0.016;
    static final double INCOME_TAX_RATE = 0.200;
    static final double SOCIAL_TAX_RATE = 0.330;
    static final double EMPLOYER_UNEMPLOYMENT_TAX_RATE = 0.008;


    public static BigDecimal pensionAmount(double grossSalary) {
        return BigDecimal.valueOf(grossSalary * PENSION_RATE).setScale( 2, RoundingMode.HALF_UP);
    }

    public static BigDecimal employeeUnemploymentPaymentAmount(double grossSalary) {
        return BigDecimal.valueOf(grossSalary * EMPLOYEE_UNEMPLOYMENT_RATE).setScale( 2, RoundingMode.HALF_UP);
    }

    public static BigDecimal socialTaxAmount(double grossSalary) {
        return BigDecimal.valueOf(grossSalary * SOCIAL_TAX_RATE).setScale( 2, RoundingMode.HALF_UP);
    }

    public static BigDecimal employerUnemploymentPaymentAmount(double grossSalary) {
        return BigDecimal.valueOf(grossSalary * EMPLOYER_UNEMPLOYMENT_TAX_RATE).setScale( 2, RoundingMode.HALF_UP);
    }


}
