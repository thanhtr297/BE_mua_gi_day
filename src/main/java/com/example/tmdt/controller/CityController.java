package com.example.tmdt.controller;

import com.example.tmdt.dto.CityDTO;
import com.example.tmdt.service.ICityService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/cities")
public class CityController {
    @Autowired
    private ICityService cityService;

    @GetMapping("/{id}")
    ResponseEntity<CityDTO> findOne(@PathVariable("id") Long id) {
        CityDTO cityDTO = cityService.findOne(id);
        return new ResponseEntity<>(cityDTO, HttpStatus.OK);
    }

    @GetMapping
    ResponseEntity<List<CityDTO>> findAll() {
        List<CityDTO> cityDTOS=  cityService.findAll();
        return new ResponseEntity<>(cityDTOS, HttpStatus.OK);
    }
}
