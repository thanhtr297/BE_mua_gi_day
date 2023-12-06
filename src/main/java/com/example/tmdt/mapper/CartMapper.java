package com.example.tmdt.mapper;

import com.example.tmdt.dto.CartDTO;
import com.example.tmdt.model.buyPrd.Cart;
import org.mapstruct.Mapper;

@Mapper(componentModel = "Spring")
public interface CartMapper extends EntityMapper<CartDTO, Cart> {

}
