package com.example.tmdt.mapper;

import com.example.tmdt.dto.BrandDTO;
import com.example.tmdt.model.fkProduct.Brand;
import org.mapstruct.Mapper;

@Mapper(componentModel = "Spring")
public interface BrandMapper extends EntityMapper<BrandDTO, Brand>{
}
