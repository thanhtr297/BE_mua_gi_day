package com.example.tmdt.repository;

import com.example.tmdt.model.buyPrd.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    @Query(value = "SELECT * FROM cart WHERE account_id = :idAccount", nativeQuery = true)
    Optional<Cart> findCartByIdAccount(@Param("idAccount") Long idAccount);
}
