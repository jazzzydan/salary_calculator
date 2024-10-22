package org.example;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.example.TaxParameters.pensionAmount;
import static org.example.TaxParameters.employeeUnemploymentPaymentAmount;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaxParametersTest {

    TaxParameters taxParameters = new TaxParameters();

    @Test
    public void pensionAmountTest() {
        assertEquals(new BigDecimal("20.00"), pensionAmount(1000));
    }

    @Test
    public void unemploymentPaymentTest() {
        assertEquals(new BigDecimal("16.00"), employeeUnemploymentPaymentAmount(1000));
    }
}
