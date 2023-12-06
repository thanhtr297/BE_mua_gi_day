package com.example.tmdt.repository;

import com.example.tmdt.model.fkProduct.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Long> {
}
