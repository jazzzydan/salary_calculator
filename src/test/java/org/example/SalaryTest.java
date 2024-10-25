//package org.example;
//
//import org.junit.jupiter.api.Test;
//
//import java.math.BigDecimal;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//public class SalaryTest {
//
//    private static BigDecimal bd(String val) {
//        return new BigDecimal(val);
//    }
//
//    @Test
//    public void calculateTaxFreeIncomeTest() {
//        GrossSalary grossSalary = new GrossSalary(BigDecimal.valueOf(1100));
//        assertEquals(bd("654.00"), new GrossSalary(bd("1100")).calculateTaxFreeIncome());
//        assertEquals(bd("654.00"), new GrossSalary(bd("1200")).calculateTaxFreeIncome());
//        assertEquals(bd("508.67"), new GrossSalary(bd("1400")).calculateTaxFreeIncome());
//        assertEquals(bd("0.00"), new GrossSalary(bd("2100")).calculateTaxFreeIncome());
//        assertEquals(bd("0.00"), new GrossSalary(bd("2400")).calculateTaxFreeIncome());
//    }
//
//    @Test
//    public void totalSalaryCalculationTest() {
//        assertEquals(bd("1338.00"), new GrossSalary(bd("1000")).totalSalaryCalculation());
//    }
//
//    @Test
//    public void totalDeductionsTest() {
//        assertEquals(bd("1349.60"), new GrossSalary(bd("1400")).calculateAmountBeforeIncomeTax());
//    }
////
////    @Test
////    public void netSalaryCalculationTest() {
////        assertEquals(new BigDecimal("902.00"), grossSalary.netSalaryCalculation(BigDecimal.valueOf(1000)));
//
////    }
////
////    @Test
////    public void negativeTaxableIncomeTest() {
////        assertEquals(new BigDecimal("645.88"), grossSalary.netSalaryCalculation(BigDecimal.valueOf(670)));
////    }
////
////    @Test
////    public void calculateTaxFreeIncomeFromNetTest() {
////        assertEquals(new BigDecimal("138.77"), netSalary.calculateTaxFreeIncome(BigDecimal.valueOf(1500.00)));
////        assertEquals(new BigDecimal("0.00"), netSalary.calculateTaxFreeIncome(BigDecimal.valueOf(1619.52)));
////        assertEquals(new BigDecimal("654.00"), netSalary.calculateTaxFreeIncome(BigDecimal.valueOf(1056.24)));
////        assertEquals(new BigDecimal("0.00"), netSalary.calculateTaxFreeIncome(BigDecimal.valueOf(1700)));
////        assertEquals(new BigDecimal("654.00"), netSalary.calculateTaxFreeIncome(BigDecimal.valueOf(1000)));
////    }
////
////    @Test
////    public void calculateGrossSalaryTest() {
////        assertEquals(new BigDecimal("1909.03"), netSalary.grossSalaryCalculation(BigDecimal.valueOf(1500.00)));
////        assertEquals(new BigDecimal("2100.00"), netSalary.grossSalaryCalculation(BigDecimal.valueOf(1619.52)));
////        assertEquals(new BigDecimal("1200.00"), netSalary.grossSalaryCalculation(BigDecimal.valueOf(1056.24)));
////        assertEquals(new BigDecimal("2204.36"), netSalary.grossSalaryCalculation(BigDecimal.valueOf(1700)));
////        assertEquals(new BigDecimal("1127.07"), netSalary.grossSalaryCalculation(BigDecimal.valueOf(1000)));
////    }
////
////    @Test
////    public void calculateGrossFromTotal() {
////        assertEquals(new BigDecimal("1500.00"), totalSalary.grossSalaryCalculation(BigDecimal.valueOf(2007)));
////    }
//
//    //    @Test
////    public void pensionAmountTest() {
////        assertEquals(new BigDecimal("20.00"), pensionAmount(BigDecimal.valueOf(1000)));
////    }
////
////    @Test
////    public void unemploymentPaymentTest() {
////        assertEquals(new BigDecimal("16.00"), employeeUnemploymentPaymentAmount(BigDecimal.valueOf(1000)));
////    }
//}
//
//
//
//
//
//
