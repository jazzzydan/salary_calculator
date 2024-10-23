package org.example;

public class NetSalary extends Salary {

//    public void grossSalaryCalculationFromNet(double netSalary) {
//
//    }

    @Override
    public String toString() {
        return String.format("%-30s %-10s %s", "Netopalk:", getSalary(), "per cent");
    }
}
