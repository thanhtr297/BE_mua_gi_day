package com.example.tmdt.service;

import com.example.tmdt.dto.DistrictDTO;

import java.util.List;

public interface IDistrictService extends BaseService<DistrictDTO> {
List<DistrictDTO> findALlByIdCity(Long id);
}
