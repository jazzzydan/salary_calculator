package org.example;

public class GrossSalary extends Salary {

    @Override
    public String toString() {
        return String.format("%-30s %-10s %s", "Brutopalk:", getSalary(), "XXX");
    }

}
