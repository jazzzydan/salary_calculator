package org.example;

public class TotalSalary extends Salary {

    @Override
    public String toString() {
        return String.format("%-30s %-10s %s", "Tööandja kulu kokku:", getSalary(), "per cent");
    }

}
