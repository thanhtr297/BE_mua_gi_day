package com.example.tmdt.repository;

import com.example.tmdt.model.address.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DistrictRepository extends JpaRepository<District, Long> {
    @Query(value = "SELECT * FROM district WHERE city_id = :id", nativeQuery = true)
    List<District> findAllByIdCity(@Param("id") Long id);
}
