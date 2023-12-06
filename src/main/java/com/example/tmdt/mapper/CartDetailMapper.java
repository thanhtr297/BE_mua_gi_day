package com.example.tmdt.mapper;

import com.example.tmdt.dto.CartDetailDTO;
import com.example.tmdt.model.buyPrd.CartDetail;
import org.mapstruct.Mapper;

@Mapper(componentModel = "Spring")
public interface CartDetailMapper extends EntityMapper<CartDetailDTO, CartDetail> {

}
