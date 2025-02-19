package com.lakshya.ExpenseTracker.Expenses;

import com.lakshya.ExpenseTracker.Expenses.dto.ExpenseSummaryDTO;
import com.lakshya.ExpenseTracker.Expenses.dto.MonthlyExpenseSummaryDTO;

import java.util.List;

public interface ExpenseService {
    String addExpense(Expense expense);
    List<Expense> findAll();
    Expense findById(Long id);
    boolean deleteById(Long id);
    boolean updateExpense(Long id, Expense expense);
    ExpenseSummaryDTO getExpenseSummary();
    MonthlyExpenseSummaryDTO getMonthlyExpenseSummary(int year, int month);
    List<Expense> getExpensesByCategory(ExpenseCategory category);
}
