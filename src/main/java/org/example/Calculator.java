package org.example;

public class Calculator {

    static final double PENSION_RATE = 2.0;
    static final double UNEMPLOYMENT_RATE = 1.6;
    static final double INCOME_TAX_RATE = 20.0;

    private double grossSalary;
    private double netSalary;
    private double taxFreeIncome = 0;

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
        double result = extractPensionAndUnemployment(grossSalary);
        return result - ((result - taxFreeIncome) * (INCOME_TAX_RATE / 100));
    }
}
