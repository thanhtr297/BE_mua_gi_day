package com.example.tmdt.controller;

import com.example.tmdt.dto.CategoryDTO;
import com.example.tmdt.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;
    @GetMapping
    ResponseEntity<Iterable<CategoryDTO>> findAll() {
        return new ResponseEntity<>(categoryService.findAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    ResponseEntity<CategoryDTO> findOne(@PathVariable Long id) {
        CategoryDTO categoryDTO = categoryService.findOne(id);
        return new ResponseEntity<>(categoryDTO,HttpStatus.OK);
    }
    @PostMapping
    ResponseEntity<?> save(@RequestBody CategoryDTO categoryDTO) {
        categoryService.save(categoryDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
