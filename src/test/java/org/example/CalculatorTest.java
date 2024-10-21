package org.example;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    Calculator calc = new Calculator();

    @Test
    public void pensionAmountTest() {
        assertEquals(new BigDecimal("20.00"), calc.pensionAmount(BigDecimal.valueOf(1000)));
    }

    @Test
    public void unemploymentPaymentTest() {
        assertEquals(new BigDecimal("16.00"), calc.unemploymentPaymentAmount(BigDecimal.valueOf(1000)));
    }

    @Test
    public void subtractPensionAndUnemploymentTest() {
        assertEquals(new BigDecimal("964.00"), calc.subtractPensionAndUnemployment(BigDecimal.valueOf(1000)));
    }

    @Test
    public void netSalaryCalculationTest() {
        assertEquals(new BigDecimal("771.20"), calc.netSalaryCalculation(BigDecimal.valueOf(1000), BigDecimal.valueOf(0)));
    }

    @Test
    public void netSalaryCalculationWithTaxFreeIncomeTest() {
        assertEquals(new BigDecimal("791.20"), calc.netSalaryCalculation(BigDecimal.valueOf(1000), BigDecimal.valueOf(100)));
    }

//    @Test
//    public void toStringTest() {
//        assertEquals(new BigDecimal("771.20"), calc.netSalaryCalculation(BigDecimal.valueOf(1000), BigDecimal.valueOf(0)));
//        assertEquals("TULEMUS         EUR        %    \n" +
//                              "Brutopalk:      1000,00    100  \n" +
//                              "", calc.toString().stripTrailing());
//    }


}

