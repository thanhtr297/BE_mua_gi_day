package com.example.tmdt.controller;

import com.example.tmdt.dto.BrandDTO;
import com.example.tmdt.model.fkProduct.Brand;
import com.example.tmdt.service.IBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("api/brands")
public class BrandController {
    @Autowired
    private IBrandService brandService;
    @GetMapping
    ResponseEntity<Iterable<BrandDTO>> findAll() {
        return new ResponseEntity<>(brandService.findAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    ResponseEntity<BrandDTO> findOne(@PathVariable Long id) {
        BrandDTO brandDTO = brandService.findOne(id);
        return new ResponseEntity<>(brandDTO,HttpStatus.OK);
    }
    @PostMapping
    ResponseEntity<?> save(@RequestBody BrandDTO brandDTO) {
        brandService.save(brandDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
