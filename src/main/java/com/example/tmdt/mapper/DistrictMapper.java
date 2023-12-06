package com.example.tmdt.mapper;

import com.example.tmdt.dto.DistrictDTO;
import com.example.tmdt.model.address.District;
import org.mapstruct.Mapper;

@Mapper(componentModel = "Spring")
public interface DistrictMapper extends EntityMapper<DistrictDTO, District> {
}
