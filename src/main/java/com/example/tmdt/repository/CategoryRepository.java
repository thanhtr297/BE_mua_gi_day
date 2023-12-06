package com.example.tmdt.repository;

import com.example.tmdt.model.fkProduct.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
