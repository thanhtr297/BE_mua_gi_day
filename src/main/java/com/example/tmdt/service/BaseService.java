package com.example.tmdt.service;

import com.example.tmdt.mapper.EntityMapper;

import java.util.List;

public interface BaseService<D>  {
    void save(D dto);
    void delete(Long id);
    D findOne(Long id);
    List<D> findAll();
}
