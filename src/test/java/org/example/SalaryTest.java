package org.example;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SalaryTest {

    GrossSalary grossSalary = new GrossSalary();

    @Test
    public void calculateTaxFreeIncomeTest() {
        assertEquals(new BigDecimal("654.00"), grossSalary.calculateTaxFreeIncome(1100));
        assertEquals(new BigDecimal("654.00"), grossSalary.calculateTaxFreeIncome(1200));
        assertEquals(new BigDecimal("508.67"), grossSalary.calculateTaxFreeIncome(1400));
        assertEquals(new BigDecimal("0.00"), grossSalary.calculateTaxFreeIncome(2100));
        assertEquals(new BigDecimal("0.00"), grossSalary.calculateTaxFreeIncome(2400));
    }
    @Test
    public void totalSalaryCalculationTest() {
        assertEquals(new BigDecimal("1338.00"), grossSalary.totalSalaryCalculation(1000));
    }

    @Test
    public void totalDeductionsTest() {
        assertEquals(964.0, grossSalary.totalDeductions(1000));
    }

    @Test
    public void netSalaryCalculationTest() {
        assertEquals(new BigDecimal("902.00"), grossSalary.netSalaryCalculation(1000));
    }

//    @Test
//    public void grossSalaryCalculationTest() {
//        assertEquals()
//    }

}




