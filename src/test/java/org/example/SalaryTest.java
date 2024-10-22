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


}




