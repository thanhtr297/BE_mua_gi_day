package com.example.tmdt.service.impl;

import com.example.tmdt.dto.CityDTO;
import com.example.tmdt.mapper.CityMapper;
import com.example.tmdt.model.address.City;
import com.example.tmdt.model.address.District;
import com.example.tmdt.repository.CityRepository;
import com.example.tmdt.service.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityService implements ICityService {
    @Autowired
    private CityMapper cityMapper;
    @Autowired
    private CityRepository cityRepository;
    @Override
    public void save(CityDTO dto) {
        City city = cityMapper.toEntity(dto);
        cityMapper.toDto(cityRepository.save(city));

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public CityDTO findOne(Long id) {
       Optional<City> optionalCity = cityRepository.findById(id);
       if (optionalCity.isPresent()) {
           City city = optionalCity.get();
           return cityMapper.toDto(city);
       }
       return null;
    }

    @Override
    public List<CityDTO> findAll() {
        List<City> cities = cityRepository.findAll();
        return cityMapper.toDto(cities);

    }
}
