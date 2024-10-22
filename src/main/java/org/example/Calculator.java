package org.example;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.example.TaxParameters.*;

public class Calculator {

    TotalSalary totalSalary = new TotalSalary();
    GrossSalary grossSalary = new GrossSalary();
    NetSalary netSalary = new NetSalary();

    public void calculateUsingGross(double salary) {
        netSalary.setNetSalary(grossSalary.netSalaryCalculation(salary));
        totalSalary.setTotalSalary(grossSalary.totalSalaryCalculation(salary));
    }

    public void calculateUsingNet(double salary) {}

    public void calculateUsingTotal(double salary) {}



//    @Override
//    public String toString() {
//        String header = String.format("%-15s %-10s %s", "TULEMUS", "EUR", "%");
//        StringBuilder outputTable = new StringBuilder();
//        outputTable.append(header).append("\n")
//                .append(String.format("%-15s %-10.2f %-5s", "Brutopalk:", grossSalary, "100"))
//                .append("\n")
//                .append(String.format("%-15s %-10.2f %-5s", "Kogumispension:", pensionAmount(grossSalary),
//                        PENSION_RATE.multiply(new BigDecimal("100")).setScale(2, RoundingMode.HALF_UP)))
//                .append("\n")
//                .append(String.format("%-15s %-10.2f %-5s", "Töötuskindlustusmakse:", unemploymentPaymentAmount(grossSalary),
//                        UNEMPLOYMENT_RATE.multiply(new BigDecimal("100")).setScale(2, RoundingMode.HALF_UP)))
//                .append("\n")
//                .append(String.format("%-15s %-10.2f %-5s", "Tulumaks:", incomeTax,
//                        INCOME_TAX_RATE.multiply(new BigDecimal("100")).setScale(2, RoundingMode.HALF_UP)))
//                .append("\n")
//                .append(String.format("%-15s %-10.2f %-5s", "Netopalk:", netSalaryCalculation(grossSalary, taxFreeIncome),
//                        "77.1"));
//        return outputTable.toString();
//    }

}
