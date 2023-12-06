package com.example.tmdt.service.impl;

import com.example.tmdt.dto.WardsDTO;
import com.example.tmdt.mapper.WardsMapper;
import com.example.tmdt.model.Product;
import com.example.tmdt.model.address.Wards;
import com.example.tmdt.repository.WardsRepository;
import com.example.tmdt.service.IWardsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class WardsService implements IWardsService {
    @Autowired
    private WardsMapper wardsMapper;
    @Autowired
    private WardsRepository wardsRepository;
    @Override
    public void save(WardsDTO dto) {
        Wards wards = wardsMapper.toEntity(dto);
        wardsMapper.toDto(wardsRepository.save(wards));
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public WardsDTO findOne(Long id) {
        Optional<Wards> optionalWards = wardsRepository.findById(id);
        if (optionalWards.isPresent()) {
            Wards wards = optionalWards.get();
            wardsMapper.toDto(wards);
        } return null;
    }

    @Override
    public List<WardsDTO> findAll() {
        List<Wards> wards = wardsRepository.findAll();
       return wardsMapper.toDto(wards);
    }

    @Override
    public List<WardsDTO> findAllByIdDistrict(Long id) {
        List<Wards> wards = wardsRepository.findALlByIdDistrict(id);
        return wardsMapper.toDto(wards);
    }
}
