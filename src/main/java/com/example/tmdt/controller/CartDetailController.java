package com.example.tmdt.controller;

import com.example.tmdt.dto.CartDTO;
import com.example.tmdt.dto.CartDetailDTO;
import com.example.tmdt.service.ICartDetailService;

import com.example.tmdt.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/cartDetails")
public class CartDetailController {
    @Autowired
    private ICartDetailService cartDetailService;
    @Autowired
    private ICartService cartService;

    @PostMapping("/shop")
    ResponseEntity<List<CartDetailDTO>> showCart(@RequestBody CartDTO cartDTO ) {
        List<CartDetailDTO> cartDetailDTOS = cartDetailService.displayListBuy(cartDTO.getAccount().getId() , cartDTO.getConfirm());
        return new ResponseEntity<>(cartDetailDTOS, HttpStatus.OK);
    }
    @GetMapping("/allOrder/{idAcc}")
    ResponseEntity<List<CartDetailDTO>> showCartDetail(@PathVariable Long idAcc ) {
        List<CartDetailDTO> cartDetailDTOS = cartDetailService.displayAllOrder(idAcc);
        return new ResponseEntity<>(cartDetailDTOS, HttpStatus.OK);
    }
    @PostMapping("/changeOrder")
    ResponseEntity<?> changeCartDetails(@RequestBody CartDTO cartDTO) {
        cartService.save(cartDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping("/{idProduct}/{idAccount}")
    ResponseEntity<?> findCartDetailByProduct(@PathVariable("idProduct") Long idProduct,
                                              @PathVariable("idAccount") Long idAccount) {
        return new ResponseEntity<>(cartDetailService.findCartDetailByProduct(idProduct, idAccount), HttpStatus.OK);
    }

}