package org.example;

import java.math.BigDecimal;

public class GrossSalary extends Salary {

    public GrossSalary(BigDecimal grossSalary, InputConditions conditions) {
        super(grossSalary, conditions);
    }

    @Override
    public BigDecimal calculateGrossSalary(BigDecimal grossSalary) {
        return grossSalary;
    }
}
