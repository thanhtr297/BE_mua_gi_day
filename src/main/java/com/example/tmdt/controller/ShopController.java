package com.example.tmdt.controller;

import com.example.tmdt.dto.ShopDTO;
import com.example.tmdt.service.impl.ShopServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/shops")
public class ShopController {
    @Autowired
    private ShopServiceService shopService;
    @GetMapping
    ResponseEntity<List<ShopDTO>> findAll() {
        List<ShopDTO> shopDTOS = shopService.findAll();
        return new ResponseEntity<>(shopDTOS, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<ShopDTO> findOne(@PathVariable("id") Long id) {
        ShopDTO shopDTO = shopService.findOne(id);
        return new ResponseEntity<>(shopDTO, HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<?> save(@RequestBody ShopDTO shopDTO) {
        if (shopDTO.getId()!=null){
        ShopDTO shopDTO1 = shopService.findOne(shopDTO.getId());
            if (shopDTO.getWards().getId()==0){
                shopDTO.setWards(shopDTO1.getWards());
            }
            if (shopDTO.getAvatar()==null){
                shopDTO.setAvatar(shopDTO1.getAvatar());
            }
            shopService.save(shopDTO);}
        else {
            shopService.save(shopDTO);
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping("/account/{id}")
    ResponseEntity<?> findShopByAccount(@PathVariable Long id){
       ShopDTO shopDTO = shopService.findShopByIdAccount(id);

        return new ResponseEntity<>(shopDTO, HttpStatus.OK);
    }
}
