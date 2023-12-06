package com.example.tmdt.repository;
import com.example.tmdt.model.fkProduct.Shop;
import com.example.tmdt.security.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ShopRepository extends JpaRepository<Shop, Long> {
    @Query(value = "select * from Shop as s where s.account_id = :id",nativeQuery = true)
    Shop findShopByIdAccount(@Param("id") Long id);


}
