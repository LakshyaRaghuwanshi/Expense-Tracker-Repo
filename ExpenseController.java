package com.lakshya.ExpenseTracker.Expenses;

import com.lakshya.ExpenseTracker.Expenses.dto.ExpenseSummaryDTO;
import com.lakshya.ExpenseTracker.Expenses.dto.MonthlyExpenseSummaryDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {
    ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @PostMapping
    public ResponseEntity<String> addExpense(@RequestBody Expense expense) {
        if (expense.getCategory() == null) {
            expense.setCategory(ExpenseCategory.OTHER); // Assign default category
        }
        expenseService.addExpense(expense);
        return new ResponseEntity<>("Expense added successfully", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Expense>> findAll() {
        return new ResponseEntity<>(expenseService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Expense> findById(@PathVariable Long id) {
        if( expenseService.findById(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(expenseService.findById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        boolean isDeleted = expenseService.deleteById(id);
        if(isDeleted) {
            return new ResponseEntity<>("Expense deleted successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Expense not found", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateExpense(@PathVariable Long id, @RequestBody Expense expense) {
        boolean isUpdated = expenseService.updateExpense(id, expense);

        if(!isUpdated) {
            return new ResponseEntity<>("Expense not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Expense updated successfully", HttpStatus.OK);
    }

    @GetMapping("/summary")
    public ResponseEntity<ExpenseSummaryDTO> getTotalExpenses() {
        ExpenseSummaryDTO expenseSummaryDTO = expenseService.getExpenseSummary();
        return new ResponseEntity<>(expenseSummaryDTO, HttpStatus.OK);
    }

    @GetMapping("/summary/{year}/{month}")
    public ResponseEntity<MonthlyExpenseSummaryDTO> getMonthlySummary(@PathVariable Integer year, @PathVariable Integer month) {
        MonthlyExpenseSummaryDTO summary = expenseService.getMonthlyExpenseSummary(year, month);
        return new ResponseEntity<>(summary, HttpStatus.OK);
    }

    @GetMapping("category/{category}")
    public ResponseEntity<List<Expense>> getExpenseByCategory(@PathVariable ExpenseCategory category) {
        return new ResponseEntity<>(expenseService.getExpensesByCategory(category), HttpStatus.OK);
    }
}
