package com.example.tmdt.service;

import com.example.tmdt.model.Filter;
import com.example.tmdt.model.FilterForShop;
import com.example.tmdt.model.Product;
import org.springframework.security.core.parameters.P;

import java.util.List;

public interface IFilterService {
    List<Product> searchFilter(Filter filter);
    List<Product> searchFilterForShop(FilterForShop filter);
}
