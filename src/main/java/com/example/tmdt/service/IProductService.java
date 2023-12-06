package com.example.tmdt.service;

import com.example.tmdt.dto.ProductDTO;

import java.util.List;

public interface IProductService extends BaseService<ProductDTO>{
    List<ProductDTO> findAllByAccount_Id(Long id);
    List<ProductDTO> findAllByShop_Id(Long id);
    List<ProductDTO> findAllStatus () ;
    List<ProductDTO> searchByName (String name) ;
    List<ProductDTO> findByCount(Long id);
    List<ProductDTO> findByCategory(Long id);

}
