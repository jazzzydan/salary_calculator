package org.example;

public class InputConditions {
    private boolean usePension;
    private boolean useUnemployment;
    private boolean useTaxFreeIncome;

    public InputConditions(boolean usePension, boolean useUnemployment, boolean useTaxFreeIncome) {
        this.usePension = usePension;
        this.useUnemployment = useUnemployment;
        this.useTaxFreeIncome = useTaxFreeIncome;
    }

    public boolean isUsePension() {
        return usePension;
    }

    public boolean isUseUnemployment() {
        return useUnemployment;
    }

    public boolean isUseTaxFreeIncome() {
        return useTaxFreeIncome;
    }
}
