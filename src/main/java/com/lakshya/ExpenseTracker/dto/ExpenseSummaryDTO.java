package com.lakshya.ExpenseTracker.dto;

import java.math.BigDecimal;

public class ExpenseSummaryDTO {
    private BigDecimal totalExpense;
    private BigDecimal expenseCount;


    public ExpenseSummaryDTO(BigDecimal totalExpense, BigDecimal expenseCount) {
        this.totalExpense = totalExpense;
        this.expenseCount = expenseCount;
    }

    public BigDecimal getTotalExpense() {
        return totalExpense;
    }

    public BigDecimal getExpenseCount() {
        return expenseCount;
    }
}
