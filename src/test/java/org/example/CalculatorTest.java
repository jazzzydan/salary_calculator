package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    Calculator calc = new Calculator();

    @Test
    public void pensionAmountTest() {
        assertEquals(20, calc.pensionAmount(1000));
    }

    @Test
    public void unemploymentPaymentTest() {
        assertEquals(16, calc.unemploymentPaymentAmount(1000));
    }
    @Test
    public void grossMinusPensionUnemploymentTest() {
        assertEquals(964,calc.grossMinusPensionUnemployment(1000));
    }

    @Test
    public void netSalaryCalculationTest() {
        assertEquals(771.2, calc.netSalaryCalculation(1000, 0));
    }

}