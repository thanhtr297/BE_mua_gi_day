package com.example.tmdt.service;

import com.example.tmdt.dto.WardsDTO;

import java.util.List;

public interface IWardsService extends BaseService<WardsDTO> {
List<WardsDTO> findAllByIdDistrict(Long id);
}
