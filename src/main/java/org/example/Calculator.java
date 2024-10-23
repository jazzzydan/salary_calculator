package org.example;

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

//    private final Salary salary = new Salary();
//todo: one Salary and one Getter


    public void calculateUsingGross(double salary) {
        netSalary.setSalary(grossSalary.netSalaryCalculation(salary));
        totalSalary.setSalary(grossSalary.totalSalaryCalculation(salary));
        grossSalary.setSalary(salary);
    }

    public void calculateUsingNet(double salary) {

    }

    public void calculateUsingTotal(double salary) {}

}
