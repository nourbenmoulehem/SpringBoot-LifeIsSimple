package com.simply.simple_life.Service;

import com.simply.simple_life.Entity.Categories;
import com.simply.simple_life.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    Optional<Categories> getCategoryById(int id) {
        return categoryRepository.getCategoriesById(id);
    }
}
