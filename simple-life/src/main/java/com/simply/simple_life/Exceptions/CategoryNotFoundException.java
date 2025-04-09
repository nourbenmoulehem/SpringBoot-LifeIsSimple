package com.simply.simple_life.Exceptions;

public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException(int categoryId) {
        super("Category with id " + categoryId + " not found");
    }
}
