package org.example;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TaxParameters {

    static final BigDecimal PENSION_RATE = new BigDecimal("0.020");
    static final BigDecimal EMPLOYEE_UNEMPLOYMENT_RATE = new BigDecimal("0.016");
    static final BigDecimal INCOME_TAX_RATE = new BigDecimal("0.200");
    static final BigDecimal SOCIAL_TAX_RATE = new BigDecimal("0.330");
    static final BigDecimal EMPLOYER_UNEMPLOYMENT_TAX_RATE = new BigDecimal("0.008");

    static final BigDecimal NET_LOWER_LIMIT = new BigDecimal("1056.24");
    static final BigDecimal NET_UPPER_LIMIT = new BigDecimal("1619.52");
    static final BigDecimal GROSS_LOWER_LIMIT = new BigDecimal("1200.00");
    static final BigDecimal GROSS_UPPER_LIMIT = new BigDecimal("2100.00");
    static final BigDecimal BASE_TAX_FREE_INCOME = new BigDecimal("654.00");
    static final BigDecimal TAX_FREE_INCOME_CONVERSION_RATE = new BigDecimal("0.72667");
    static final BigDecimal GROSS_SALARY_CONVERSION_RATE = new BigDecimal("1.037344");

    static final BigDecimal SOCIAL_TAX_REVERSE = new BigDecimal("0.2466367713");
    static final BigDecimal EMPLOYER_UNEMPLOYMENT_TAX_REVERSE = new BigDecimal("0.00597907324");

}
