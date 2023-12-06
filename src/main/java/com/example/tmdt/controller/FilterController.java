package com.example.tmdt.controller;

import com.example.tmdt.model.Filter;
import com.example.tmdt.model.FilterForShop;
import com.example.tmdt.model.Product;
import com.example.tmdt.repository.FilterRepository;
import com.example.tmdt.service.impl.FilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/filters")
public class FilterController {
    @Autowired
    FilterService filterService;
    @PostMapping
    ResponseEntity<List<Product>> filter(@RequestBody Filter filter) {
        return new ResponseEntity<>(filterService.searchFilter(filter), HttpStatus.OK);
    }
    @PostMapping("/for/shop")
    ResponseEntity<List<Product>> filterForShop(@RequestBody FilterForShop filter) {
        return new ResponseEntity<>(filterService.searchFilterForShop(filter), HttpStatus.OK);
    }
}
