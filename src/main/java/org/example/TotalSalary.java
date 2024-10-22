package org.example;

import java.math.BigDecimal;

public class TotalSalary {

    private BigDecimal totalSalary;

    public BigDecimal getTotalSalary() {
        return totalSalary;
    }

    public void setTotalSalary(BigDecimal totalSalary) {
        this.totalSalary = totalSalary;
    }
    @Override
    public String toString() {
        return String.format("%-30s %-10s %s", "Tööandja kulu kokku:", totalSalary, "100");
    }

}
