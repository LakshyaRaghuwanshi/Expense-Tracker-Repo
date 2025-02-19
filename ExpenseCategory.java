package com.lakshya.ExpenseTracker.Expenses;

public enum ExpenseCategory {
    FOOD,
    FUEL,
    HEALTHCARE,
    ENTERTAINMENT,
    RENT,
    UTILITIES,
    OTHER;

    public static ExpenseCategory fromString(String category) {
        try {
            return ExpenseCategory.valueOf(category.toUpperCase());         // Convert string to enum
        } catch (IllegalArgumentException | NullPointerException e) {
            return ExpenseCategory.OTHER;           // Default to OTHER for invalid values
        }
    }
}
