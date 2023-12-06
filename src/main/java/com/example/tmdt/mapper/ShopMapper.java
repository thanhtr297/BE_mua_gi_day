package com.example.tmdt.mapper;

import com.example.tmdt.dto.ShopDTO;
import com.example.tmdt.model.fkProduct.Shop;
import org.mapstruct.Mapper;

@Mapper(componentModel = "Spring")
public interface ShopMapper extends EntityMapper<ShopDTO, Shop> {
}
