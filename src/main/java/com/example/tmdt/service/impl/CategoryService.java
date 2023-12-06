package com.example.tmdt.service.impl;

import com.example.tmdt.dto.CategoryDTO;
import com.example.tmdt.mapper.CategoryMapper;
import com.example.tmdt.model.fkProduct.Category;
import com.example.tmdt.repository.CategoryRepository;
import com.example.tmdt.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements ICategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryMapper categoryMapper;
    @Override
    public void save(CategoryDTO dto) {
        Category category = categoryMapper.toEntity(dto);
        categoryMapper.toDto(categoryRepository.save(category));
    }

    @Override
    public void delete(Long id) {
    }

    @Override
    public CategoryDTO findOne(Long id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()) {
            Category category = optionalCategory.get();
            return categoryMapper.toDto(category);
        }return null;
    }

    @Override
    public List<CategoryDTO> findAll() {
        List<Category> categories = categoryRepository.findAll();
        return categoryMapper.toDto(categories);
    }
}
