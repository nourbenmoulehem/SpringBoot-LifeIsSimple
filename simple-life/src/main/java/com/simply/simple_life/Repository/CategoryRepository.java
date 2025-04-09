package com.simply.simple_life.Repository;

import com.simply.simple_life.Entity.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Categories, Integer> {
    Optional<Categories> getCategoriesById(int id);
}
