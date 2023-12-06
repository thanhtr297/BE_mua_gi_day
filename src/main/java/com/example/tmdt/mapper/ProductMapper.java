package com.example.tmdt.mapper;

import com.example.tmdt.dto.ProductDTO;
import com.example.tmdt.model.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "Spring")
public interface ProductMapper extends EntityMapper<ProductDTO, Product>{

}
