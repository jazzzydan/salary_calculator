package org.example;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SalaryTest {

    GrossSalary grossSalary = new GrossSalary();
    NetSalary netSalary = new NetSalary();

    @Test
    public void calculateTaxFreeIncomeTest() {
        assertEquals(new BigDecimal("654.00"), grossSalary.calculateTaxFreeIncome(BigDecimal.valueOf(1100)));
        assertEquals(new BigDecimal("654.00"), grossSalary.calculateTaxFreeIncome(BigDecimal.valueOf(1200)));
        assertEquals(new BigDecimal("508.67"), grossSalary.calculateTaxFreeIncome(BigDecimal.valueOf(1400)));
        assertEquals(new BigDecimal("0.00"), grossSalary.calculateTaxFreeIncome(BigDecimal.valueOf(2100)));
        assertEquals(new BigDecimal("0.00"), grossSalary.calculateTaxFreeIncome(BigDecimal.valueOf(2400)));
    }
    @Test
    public void totalSalaryCalculationTest() {
        assertEquals(new BigDecimal("1338.00"), grossSalary.totalSalaryCalculation(BigDecimal.valueOf(1000)));
    }

    @Test
    public void totalDeductionsTest() {
        assertEquals(new BigDecimal( "964.00"), grossSalary.calculateAmountBeforeIncomeTax(BigDecimal.valueOf(1000)));

    }

    @Test
    public void netSalaryCalculationTest() {
        assertEquals(new BigDecimal("902.00"), grossSalary.netSalaryCalculation(BigDecimal.valueOf(1000)));
    }

    @Test
    public void negativeTaxableIncomeTest() {
        assertEquals(new BigDecimal("645.88"),grossSalary.netSalaryCalculation(BigDecimal.valueOf(670)));
    }

}




