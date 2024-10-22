package org.example;

import java.math.BigDecimal;

public class Calculator {

    private final TotalSalary totalSalary = new TotalSalary();
    private final GrossSalary grossSalary = new GrossSalary();
    private final NetSalary netSalary = new NetSalary();

    public GrossSalary getGrossSalary() {
        return grossSalary;
    }

    public TotalSalary getTotalSalary() {
        return totalSalary;
    }

    public NetSalary getNetSalary() {
        return netSalary;
    }

    public void calculateUsingGross(double salary) {
        netSalary.setNetSalary(grossSalary.netSalaryCalculation(salary));
        totalSalary.setTotalSalary(grossSalary.totalSalaryCalculation(salary));
        grossSalary.setGrossSalary(salary);
    }

    public void calculateUsingNet(double salary) {}

    public void calculateUsingTotal(double salary) {}

}
