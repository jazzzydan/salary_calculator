package org.example;

import java.math.BigDecimal;

public class GrossSalary extends Salary {

    public GrossSalary(BigDecimal grossSalary) {
        super(grossSalary);
    }

    @Override
    public BigDecimal calculateGrossSalary(BigDecimal grossSalary) {
        return grossSalary;
    }
}
