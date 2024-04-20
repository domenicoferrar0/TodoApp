package com.ferraro.ToDoApp.enums;

public enum Category {
    WORK("Work"),
    PERSONAL("Personal"),
    SHOPPING("Shopping"),
    STUDY("Study"),
    OTHER("Other");

    private final String categoryName;

    Category(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }
}
