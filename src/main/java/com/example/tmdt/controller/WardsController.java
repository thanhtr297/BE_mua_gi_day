package com.example.tmdt.controller;

import com.example.tmdt.dto.WardsDTO;
import com.example.tmdt.service.IWardsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("api/wards")
public class WardsController {
    @Autowired
    private IWardsService wardsService;

    @GetMapping
    ResponseEntity<List<WardsDTO>> findAll() {
        List<WardsDTO> wardsDTOS = wardsService.findAll();
        return new ResponseEntity<>(wardsDTOS, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<WardsDTO> findOne(@PathVariable("id") Long id) {
        WardsDTO wardsDTO = wardsService.findOne(id);
        return new ResponseEntity<>(wardsDTO, HttpStatus.OK);
    }

    @PostMapping("/district/{id}")
    ResponseEntity<List<WardsDTO>> findAllByIdDistrict(@PathVariable("id") Long id) {
        List<WardsDTO> wardsDTOS = wardsService.findAllByIdDistrict(id);
        return new ResponseEntity<>(wardsDTOS, HttpStatus.OK);
    }


}
