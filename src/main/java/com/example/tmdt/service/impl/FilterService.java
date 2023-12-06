package com.example.tmdt.service.impl;

import com.example.tmdt.model.Filter;
import com.example.tmdt.model.FilterForShop;
import com.example.tmdt.model.Product;
import com.example.tmdt.repository.FilterRepository;
import com.example.tmdt.service.IFilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FilterService implements IFilterService {
    @Autowired
    FilterRepository filterRepository;
    @Override
    public List<Product> searchFilter(Filter filter) {
        try {
            return filterRepository.searchFilter(
                    filter.getCategory().getId(),
                    filter.getBrand().getId(),
                    filter.getMaxPrice(),
                    filter.getMinPrice(),
                    filter.getWards().getId(),
                    filter.getDistrict().getId(),
                    filter.getCity().getId()
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Product> searchFilterForShop(FilterForShop filter) {
        try {
            return filterRepository.searchFilterForShop(
                    filter.getShop().getId(),
                    filter.getCategory().getId(),
                    filter.getBrand().getId(),
                    filter.getMaxPrice(),
                    filter.getMinPrice()
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
