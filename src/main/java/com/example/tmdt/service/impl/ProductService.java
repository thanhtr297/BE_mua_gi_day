package com.example.tmdt.service.impl;

import com.example.tmdt.dto.ProductDTO;
import com.example.tmdt.mapper.ProductMapper;
import com.example.tmdt.model.Product;
import com.example.tmdt.repository.ProductRepository;
import com.example.tmdt.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductMapper productMapper;

    @Override
    public void save(ProductDTO dto) {
        Product product = productMapper.toEntity(dto);
        productMapper.toDto(productRepository.save(product));
    }


    @Override
    public void delete(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setStatus(1);
            productRepository.save(product);
        }
    }

    @Override
    public ProductDTO findOne(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            return productMapper.toDto(product);
        }
        return null;
    }

    @Override
    public List<ProductDTO> findAll() {
        List<Product> products = productRepository.findAll();
        return productMapper.toDto(products);
    }

    @Override
    public List<ProductDTO> findAllByAccount_Id(Long id) {
        List<Product> products = productRepository.findAllByAccount_Id(id);
        return productMapper.toDto(products);
    }

    @Override
    public List<ProductDTO> findAllStatus() {
        return productMapper.toDto(productRepository.findAllStatus());
    }

    @Override
    public List<ProductDTO> findByCount(Long id) {
        return productMapper.toDto(productRepository.findByCount(id));
    }

    @Override
    public List<ProductDTO> searchByName(String name) {
        return productMapper.toDto(productRepository.findAllByNameContainingAndStatusIsNull(name));
    }

    @Override
    public List<ProductDTO> findAllByShop_Id(Long id) {
        List<Product> products = productRepository.findAllByShop_Id(id);
        return productMapper.toDto(products);
    }

    @Override
    public List<ProductDTO> findByCategory(Long id) {
        List<Product> products = productRepository.findByCategory(id);
        return productMapper.toDto(products);
    }
}
