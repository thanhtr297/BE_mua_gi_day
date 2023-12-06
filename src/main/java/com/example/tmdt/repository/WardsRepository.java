package com.example.tmdt.repository;
import com.example.tmdt.model.address.Wards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WardsRepository extends JpaRepository<Wards, Long> {
    @Query(value = "SELECT * FROM wards WHERE district_id = :id", nativeQuery = true)
    List<Wards> findALlByIdDistrict(@Param("id") Long id);

}
