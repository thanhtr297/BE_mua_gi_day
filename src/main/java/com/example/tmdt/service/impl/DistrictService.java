package com.example.tmdt.service.impl;

import com.example.tmdt.dto.DistrictDTO;
import com.example.tmdt.mapper.DistrictMapper;
import com.example.tmdt.model.address.District;
import com.example.tmdt.model.address.Wards;
import com.example.tmdt.repository.DistrictRepository;
import com.example.tmdt.service.IDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DistrictService implements IDistrictService {
    @Autowired
    private DistrictMapper districtMapper;
    @Autowired
    private DistrictRepository districtRepository;
    @Override
    public void save(DistrictDTO dto) {
        District district = districtMapper.toEntity(dto);
        districtMapper.toDto(districtRepository.save(district));

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public DistrictDTO findOne(Long id) {
        Optional<District> districtOptional = districtRepository.findById(id);
        if (districtOptional.isPresent()) {
            District district = districtOptional.get();
             return districtMapper.toDto(district);
        } return null;
    }

    @Override
    public List<DistrictDTO> findAll() {
        List<District> districts = districtRepository.findAll();
        return districtMapper.toDto(districts);
    }

    @Override
    public List<DistrictDTO> findALlByIdCity(Long id) {
        List<District> districts = districtRepository.findAllByIdCity(id);
        return districtMapper.toDto(districts);
    }
}
