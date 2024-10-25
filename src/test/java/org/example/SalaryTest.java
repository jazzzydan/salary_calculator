package org.example;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SalaryTest {

    private static BigDecimal bd(String val) {
        return new BigDecimal(val);
    }

    @Test
    public void calculateTaxFreeIncomeTest() {
        GrossSalary grossSalary = new GrossSalary(BigDecimal.valueOf(1100), true, true, true);
        assertEquals(bd("654.00"), new GrossSalary(bd("1100"), true, true, true).calculateTaxFreeIncome());
        assertEquals(bd("654.00"), new GrossSalary(bd("1200"), true, true, true).calculateTaxFreeIncome());
        assertEquals(bd("508.67"), new GrossSalary(bd("1400"), true, true, true).calculateTaxFreeIncome());
        assertEquals(bd("0.00"), new GrossSalary(bd("2100"), true, true, true).calculateTaxFreeIncome());
        assertEquals(bd("0.00"), new GrossSalary(bd("2400"), true, true, true).calculateTaxFreeIncome());
    }

    @Test
    public void totalSalaryCalculationTest() {
        assertEquals(bd("1338.00"), new GrossSalary(bd("1000"), true, true, true).totalSalaryCalculation());
    }

    @Test
    public void totalDeductionsTest() {
        GrossSalary grossSalary = new GrossSalary(bd("1400"), true, true, true);
        assertEquals(bd("1349.60"), grossSalary.calculateAmountBeforeIncomeTax());
    }

    @Test
    public void netSalaryCalculationTest() {
        GrossSalary grossSalary = new GrossSalary(bd("1000"), true, true, true);
        assertEquals(bd("902.00"), grossSalary.netSalaryCalculation());
    }

    @Test
    public void negativeTaxableIncomeTest() {
        GrossSalary grossSalary = new GrossSalary(bd("670"), true, true, true);
        assertEquals(bd("645.88"), grossSalary.netSalaryCalculation());
    }

    @Test
    public void calculateTaxFreeIncomeFromNetTest() {
        NetSalary netSalary = new NetSalary(bd("1500.00"), true, true, true);
        assertEquals(bd("138.77"), netSalary.calculateTaxFreeIncome(bd("1500")));
        assertEquals(bd("0.00"), netSalary.calculateTaxFreeIncome(bd("1619.52")));
        assertEquals(bd("654.00"), netSalary.calculateTaxFreeIncome(bd("1056.24")));
        assertEquals(bd("0.00"), netSalary.calculateTaxFreeIncome(bd("1700")));
        assertEquals(bd("654.00"), netSalary.calculateTaxFreeIncome(bd("1000")));
    }

    @Test
    public void calculateGrossSalaryTest() {
        NetSalary netSalary = new NetSalary(bd("1500.00"), true, true, true);
        assertEquals(bd("1909.03"), netSalary.calculateGrossSalary(bd("1500.00")));
        assertEquals(bd("2100.00"), netSalary.calculateGrossSalary(bd("1619.52")));
        assertEquals(bd("1200.00"), netSalary.calculateGrossSalary(bd("1056.24")));
        assertEquals(bd("2204.36"), netSalary.calculateGrossSalary(bd("1700")));
        assertEquals(bd("1127.07"), netSalary.calculateGrossSalary(bd("1000")));
    }

    @Test
    public void calculateGrossFromTotal() {
        TotalSalary totalSalary = new TotalSalary(bd("2007"), true, true, true);
        assertEquals(bd("1500.00"), totalSalary.calculateGrossSalary(bd("2007")));
    }

        @Test
    public void pensionAmountTest() {
        GrossSalary grossSalary = new GrossSalary(bd("1000"), true, true, true);
        assertEquals(bd("20.00"), grossSalary.pensionAmount());
    }

    @Test
    public void unemploymentPaymentTest() {
        GrossSalary grossSalary = new GrossSalary(bd("1000"), true, true, true);
        assertEquals(bd("16.00"), grossSalary.employeeUnemploymentPaymentAmount());
    }
}






