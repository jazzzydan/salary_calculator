package org.example;

import java.math.BigDecimal;

public class NetSalary extends Salary {

    @Override
    public String toString() {
        return String.format("%-30s %-10s %s", "Netopalk:", getSalary(), "per cent");
    }
}
