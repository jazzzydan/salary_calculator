package org.example;

import java.math.BigDecimal;

public class Calculator {
    private final TotalSalary totalSalary = new TotalSalary();
    private final GrossSalary grossSalary = new GrossSalary();
    private final NetSalary netSalary = new NetSalary();
    private Salary currentSalary;
    public GrossSalary getGrossSalary() {
        return grossSalary;
    }

    public TotalSalary getTotalSalary() {
        return totalSalary;
    }

    public NetSalary getNetSalary() {
        return netSalary;
    }

    public Salary getSalary() {
        return currentSalary;
    }

    //todo: one Salary and one Getter

    public void calculateUsingGross(BigDecimal salary) {
        netSalary.setSalary(grossSalary.netSalaryCalculation(salary));
        grossSalary.setSalary(salary);
        totalSalary.setSalary(grossSalary.totalSalaryCalculation(salary));
//        currentSalary = grossSalary;
    }

    public void calculateUsingNet(BigDecimal salary) {
        netSalary.setSalary(salary);
        grossSalary.setSalary(netSalary.grossSalaryCalculation(salary));
        totalSalary.setSalary(grossSalary.totalSalaryCalculation(grossSalary.getSalary()));
//        currentSalary = netSalary;
    }

    public void calculateUsingTotal(BigDecimal salary) {
        totalSalary.setSalary(salary);
        grossSalary.setSalary(totalSalary.grossSalaryCalculation(salary));
        netSalary.setSalary(grossSalary.netSalaryCalculation(grossSalary.getSalary()));
//        currentSalary = totalSalary;
    }
}
