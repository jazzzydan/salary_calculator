package org.example;

public class Calculator {

    static final double PENSION_RATE = 2.0;
    static final double UNEMPLOYMENT_RATE = 1.6;
    static final double INCOME_TAX_RATE = 20.0;

    private double grossSalary;
    private double taxFreeIncome;

    double pensionAmount(double grossSalary) {
        return grossSalary * (PENSION_RATE / 100);
    }

    double unemploymentPaymentAmount(double grossSalary) {
        return grossSalary * (UNEMPLOYMENT_RATE / 100);
    }

    public double extractPensionAndUnemployment(double grossSalary) {
        return grossSalary - (pensionAmount(grossSalary) + unemploymentPaymentAmount(grossSalary));
    }

    public double netSalaryCalculation(double grossSalary, double taxFreeIncome) {
        this.grossSalary = grossSalary;
        this.taxFreeIncome = taxFreeIncome;
        double intermediateResult = extractPensionAndUnemployment(grossSalary);
        return intermediateResult - ((intermediateResult - taxFreeIncome) * (INCOME_TAX_RATE / 100));
    }

    @Override
    public String toString() {
        String header = String.format("%-15s %-10s %-5s", "TULEMUS", "EUR", "%");
        StringBuilder outputTable = new StringBuilder();
        outputTable.append(header).append("\n")
                .append(String.format("%-15s %-10.2f %-5s", "Brutopalk", grossSalary, "100"))
                .append("\n");
        return outputTable.toString();
    }

}
