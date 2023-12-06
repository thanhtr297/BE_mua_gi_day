package com.example.tmdt.mapper;

import com.example.tmdt.dto.CityDTO;
import com.example.tmdt.model.address.City;
import org.mapstruct.Mapper;

@Mapper(componentModel = "Spring")
public interface CityMapper extends EntityMapper<CityDTO, City> {

}
