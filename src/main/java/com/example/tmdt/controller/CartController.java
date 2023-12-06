package com.example.tmdt.controller;

import com.example.tmdt.dto.CartDetailDTO;
import com.example.tmdt.service.IBillDetailService;
import com.example.tmdt.service.ICartDetailService;
import com.example.tmdt.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/carts")
public class CartController {
    @Autowired
    private ICartDetailService cartDetailService;
    @Autowired
    private ICartService cartService;
    @Autowired
    private IBillDetailService billDetailService;

    @PostMapping("/add")
    ResponseEntity<?> addToCart(@RequestBody CartDetailDTO cartDetailDTO,
                                @RequestParam("idAccount") Long idAccount) {
        cartDetailService.addToCart(cartDetailDTO, idAccount);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    ResponseEntity<List<CartDetailDTO>> showCart(@PathVariable("id") Long idAccount) {
        List<CartDetailDTO> cartDetailDTOS = cartDetailService.showCart(idAccount);
        return new ResponseEntity<>(cartDetailDTOS, HttpStatus.OK);
    }

    @DeleteMapping("/{idCartDetail}")
    ResponseEntity<?>deleteProductFromCart(@PathVariable("idCartDetail") Long idCartDetail) {
        cartDetailService.deleteProductFromCart(idCartDetail);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("/deleteAll/{idCart}")
    ResponseEntity<?>deleteAllCart(@PathVariable("idCart") Long idCart) {
        cartDetailService.deleteAllProductFromCart(idCart);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/update")
    ResponseEntity<?>updateQuantityFromCart(@Param("quantity") Double quantity,
                                            @Param("idProduct") Long idProduct,
                                            @Param("idCart") Long idCart) {
        cartDetailService.updateQuantityFromCart(quantity, idProduct, idCart);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/showProductUserSelect")
    ResponseEntity<List<CartDetailDTO>> showProductUserSelect(@RequestBody List<Long> idCartDetail) {
        List<CartDetailDTO> cartDetailDTOS = billDetailService.showProductUserSelect(idCartDetail);
        return new ResponseEntity<>(cartDetailDTOS, HttpStatus.OK);
    }


}
