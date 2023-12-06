package com.example.tmdt.mapper;

import com.example.tmdt.dto.ImageDTO;
import com.example.tmdt.model.fkProduct.Image;
import org.mapstruct.Mapper;

@Mapper(componentModel = "Spring")
public interface ImageMapper extends EntityMapper<ImageDTO, Image> {
}
