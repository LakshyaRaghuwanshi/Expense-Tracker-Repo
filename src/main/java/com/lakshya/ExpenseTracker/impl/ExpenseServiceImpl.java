package com.lakshya.ExpenseTracker.impl;

import com.lakshya.ExpenseTracker.Expense;
import com.lakshya.ExpenseTracker.ExpenseCategory;
import com.lakshya.ExpenseTracker.ExpenseService;
import com.lakshya.ExpenseTracker.dto.ExpenseSummaryDTO;
import com.lakshya.ExpenseTracker.dto.MonthlyExpenseSummaryDTO;
import com.lakshya.ExpenseTracker.repository.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    ExpenseRepository expenseRepository;

    public ExpenseServiceImpl(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    @Override
    public String addExpense(Expense expense) {         //add expense
        if(expense.getCategory() == null) {
            expense.setCategory(ExpenseCategory.OTHER); // Default to OTHER
        }
        expenseRepository.save(expense);
        return "Expense added successfully";
    }

    @Override
    public List<Expense> findAll() {             //return all expenses
        return expenseRepository.findAll();
    }

    @Override
    public Expense findById(Long id) {
        return expenseRepository.findById(id).orElse(null);
    }

    @Override
    public boolean deleteById(Long id) {
        if(expenseRepository.existsById(id)) {
            expenseRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean updateExpense(Long id, Expense updatedExpense) {
        Optional<Expense> expenseOptional = expenseRepository.findById(id);

        if(expenseOptional.isPresent()) {
            Expense expense = expenseOptional.get();

            if(updatedExpense.getDescription() != null) {
                expense.setDescription(updatedExpense.getDescription());
            }
            if ( updatedExpense.getAmount() != null) {
                expense.setAmount(updatedExpense.getAmount());
            }
            if (updatedExpense.getDate() != null) {
                expense.setDate(updatedExpense.getDate());
            }

            expenseRepository.save(expense);
            return true;
        }
        return false;
    }

    @Override
    public ExpenseSummaryDTO getExpenseSummary() {

        List<Expense> expenses = expenseRepository.findAll();

        BigDecimal totalExpense = expenses.stream()
                .map(Expense::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalCount = BigDecimal.valueOf(expenses.size());

        return new ExpenseSummaryDTO(totalExpense, totalCount);
    }

    @Override
    public MonthlyExpenseSummaryDTO getMonthlyExpenseSummary(int month, int year) {


        List<Expense> expenses = expenseRepository.findByMonthAndYear(month, year);

        BigDecimal totalExpense = expenses.stream()
                .map(Expense::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalCount = BigDecimal.valueOf(expenses.size());

        return new MonthlyExpenseSummaryDTO(month, year, totalExpense, totalCount);

    }

    @Override
    public List<Expense> getExpensesByCategory(ExpenseCategory category) {
        return expenseRepository.findByCategory(category);
    }

}
