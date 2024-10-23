package org.example;

import java.math.BigDecimal;

import static org.example.TaxParameters.INCOME_TAX_RATE;

public class NetSalary extends Salary {

    public void grossSalaryCalculation(double netSalary) {

    }

    @Override
    public String toString() {
        return String.format("%-30s %-10s %s", "Netopalk:", getSalary(), "per cent");
    }
}
