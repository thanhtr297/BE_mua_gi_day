package com.example.tmdt.controller;
import com.example.tmdt.dto.ImageDTO;
import com.example.tmdt.dto.ProductDTO;
import com.example.tmdt.model.Product;
import com.example.tmdt.repository.ProductRepository;
import com.example.tmdt.service.IImageService;
import com.example.tmdt.repository.ProductRepository;
import com.example.tmdt.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private IProductService productService;
    @Autowired
    private IImageService imageService;
    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    ResponseEntity<Iterable<ProductDTO>> findAll() {
        return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
    }
    @GetMapping("/status")
    ResponseEntity<Iterable<ProductDTO>> findAllStatus() {
        return new ResponseEntity<>(productService.findAllStatus(), HttpStatus.OK);
    }
    @GetMapping("/count/{id}")
    ResponseEntity<List<ProductDTO>> findByCount(@PathVariable Long id) {
        return new ResponseEntity<>(productService.findByCount(id),HttpStatus.OK);
    }
    @GetMapping("/search/{name}")
    ResponseEntity<Iterable<ProductDTO>> searchByName(@PathVariable String name) {
        return new ResponseEntity<>(productService.searchByName(name), HttpStatus.OK);
    }


    @GetMapping("/{id}")
    ResponseEntity<ProductDTO> findOne(@PathVariable("id") Long id) {
        ProductDTO productDTO = productService.findOne(id);
        return new ResponseEntity<>(productDTO, HttpStatus.OK);
    }
    @PostMapping
    ResponseEntity<?> save(@RequestBody ProductDTO productDTO) {
        if(productDTO.getId() != null) {
            ProductDTO productDTO1 = productService.findOne(productDTO.getId());
            List<ImageDTO> imageDTOS = productDTO1.getImage();
            if (productDTO.getImage().isEmpty()) {
                productDTO.setImage(imageDTOS);
            }

            productService.save(productDTO);


        }
        productDTO.setCount(0);
        productService.save(productDTO);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }
    @GetMapping("/acc/{id}")
    ResponseEntity<List<ProductDTO>> findByIdAcc(@PathVariable Long id) {
      List<ProductDTO> list = productService.findAllByAccount_Id(id);
      List<ProductDTO> productDTOList = new ArrayList<>();
        for (ProductDTO p: list
             ) {
            if (p.getStatus() == null) {
                productDTOList.add(p);
            }
        }
        return new ResponseEntity<>(productDTOList,HttpStatus.OK);
    }
    @GetMapping("/shop/{id}")
    ResponseEntity<List<ProductDTO>> findByIdShop(@PathVariable Long id) {
        List<ProductDTO> list = productService.findAllByShop_Id(id);
        List<ProductDTO> productDTOList = new ArrayList<>();
        for (ProductDTO p: list
        ) {
            if (p.getStatus() == null) {
                productDTOList.add(p);
            }
        }
        return new ResponseEntity<>(productDTOList,HttpStatus.OK);
    }
    @GetMapping("/category/{id}")
    ResponseEntity<List<ProductDTO>> findByCategory(@PathVariable Long id) {
        List<ProductDTO> list = productService.findByCategory(id);
        List<ProductDTO> productDTOList = new ArrayList<>();
        for (ProductDTO p: list
        ) {
            if (p.getStatus() == null) {
                productDTOList.add(p);
            }
        }
        return new ResponseEntity<>(productDTOList,HttpStatus.OK);
    }



}
