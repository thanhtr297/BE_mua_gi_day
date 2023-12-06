package com.example.tmdt.controller;

import com.example.tmdt.dto.DistrictDTO;
import com.example.tmdt.service.IDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("api/districts")
public class DistrictController {
    @Autowired
    private IDistrictService districtService;

    @GetMapping
    ResponseEntity<List<DistrictDTO>> findAll() {
        List<DistrictDTO> districtDTOS = districtService.findAll();
        return new ResponseEntity<>(districtDTOS, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<DistrictDTO> findOne(@PathVariable("id") Long id) {
        DistrictDTO districtDTO = districtService.findOne(id);
        return new ResponseEntity<>(districtDTO, HttpStatus.OK);
    }

    @PostMapping("/city/{id}")
    ResponseEntity<List<DistrictDTO>> findAllByIdCity(@PathVariable("id") Long id) {
        List<DistrictDTO> districtDTOS = districtService.findALlByIdCity(id);
        return new ResponseEntity<>(districtDTOS, HttpStatus.OK);
    }

}
