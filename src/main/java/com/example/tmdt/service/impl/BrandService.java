package com.example.tmdt.service.impl;

import com.example.tmdt.dto.BrandDTO;
import com.example.tmdt.mapper.BrandMapper;
import com.example.tmdt.model.fkProduct.Brand;
import com.example.tmdt.repository.BrandRepository;
import com.example.tmdt.service.IBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BrandService implements IBrandService {
    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private BrandMapper brandMapper;

    @Override
    public void save(BrandDTO dto) {
        Brand brand = brandMapper.toEntity(dto);
        brandMapper.toDto(brandRepository.save(brand));
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public BrandDTO findOne(Long id) {
        Optional<Brand> optionalBrand = brandRepository.findById(id);
        if (optionalBrand.isPresent()) {
            Brand brand = optionalBrand.get();
            return brandMapper.toDto(brand);
        }
        return null;
    }

    @Override
    public List<BrandDTO> findAll() {
        List<Brand> brands = brandRepository.findAll();
        return brandMapper.toDto(brands);
    }
}
