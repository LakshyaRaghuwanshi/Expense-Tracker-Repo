package com.lakshya.ExpenseTracker.dto;

import java.math.BigDecimal;

public class MonthlyExpenseSummaryDTO {
    int month;
    int year;
    BigDecimal amount;
    BigDecimal count;

    public MonthlyExpenseSummaryDTO(int month, int year, BigDecimal amount, BigDecimal count) {
        this.month = month;
        this.year = year;
        this.amount = amount;
        this.count = count;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public BigDecimal getCount() {
        return count;
    }
}
