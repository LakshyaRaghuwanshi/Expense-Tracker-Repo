package com.lakshya.ExpenseTracker.repository;

import com.lakshya.ExpenseTracker.Expense;
import com.lakshya.ExpenseTracker.ExpenseCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    @Query("SELECT e FROM Expense e WHERE EXTRACT(MONTH FROM e.date) = :year AND EXTRACT(YEAR FROM e.date) = :month")
    List<Expense> findByMonthAndYear(@Param("month") int month, @Param("year") int year);

    @Query("SELECT e FROM Expense e WHERE e.category = :category")
    List<Expense> findByCategory(@Param("category") ExpenseCategory category);

}
