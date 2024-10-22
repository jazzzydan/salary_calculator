package org.example;

import java.math.BigDecimal;

public class NetSalary {
    private BigDecimal netSalary;

    public BigDecimal getNetSalary() {
        return netSalary;
    }

    public void setNetSalary(BigDecimal netSalary) {
        this.netSalary = netSalary;
    }

    @Override
    public String toString() {
        return String.format("%-30s %-10s %s", "Netopalk:", netSalary, "100");
    }
}
