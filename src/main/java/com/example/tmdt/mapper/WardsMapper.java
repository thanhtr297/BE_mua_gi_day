package com.example.tmdt.mapper;

import com.example.tmdt.dto.WardsDTO;
import com.example.tmdt.model.address.Wards;
import org.mapstruct.Mapper;

@Mapper(componentModel = "Spring")
public interface WardsMapper extends EntityMapper<WardsDTO, Wards> {
}
