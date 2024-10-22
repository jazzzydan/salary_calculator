package org.example;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    Calculator calc = new Calculator();


    @Test
    public void totalDeductionsTest() {
        assertEquals(964.0, calc.totalDeductions(1000));
    }

    @Test
    public void netSalaryCalculationTest() {
        assertEquals(new BigDecimal("902.00"), calc.netSalaryCalculation(1000));
    }

    @Test
    public void totalSalaryCalculationTest() {
        assertEquals(new BigDecimal("1338.00"), calc.totalSalaryCalculation(1000));
    }

//    @Test
//    public void toStringTest() {
//        assertEquals(new BigDecimal("771.20"), calc.netSalaryCalculation(BigDecimal.valueOf(1000), BigDecimal.valueOf(0)));
//        assertEquals("TULEMUS         EUR        %    \n" +
//                              "Brutopalk:      1000,00    100  \n" +
//                              "", calc.toString().stripTrailing());
//    }

}

