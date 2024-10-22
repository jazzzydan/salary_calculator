package org.example;

import java.math.BigDecimal;

public class Salary {

        private BigDecimal salary;

        enum Type {
            NET,
            GROSS,
            TOTAL
        }

        public Salary() {
        }

        public BigDecimal getSalary() {
            return salary;
        }

        public void setSalary(BigDecimal salary) {
            this.salary = salary;
        }

        @Override
        public String toString() {
            return String.format("%-30s %-10s %s", "", salary, "per cent");
        }

}
