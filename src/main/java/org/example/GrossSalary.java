package org.example;

import java.math.BigDecimal;

public class GrossSalary extends Salary {

    public GrossSalary(BigDecimal grossSalary, boolean usePension, boolean useUnemployment, boolean useTaxFreeIncome) {
        super(grossSalary, usePension, useUnemployment, useTaxFreeIncome);
    }

    @Override
    public BigDecimal calculateGrossSalary(BigDecimal grossSalary) {
        return grossSalary;
    }
}
