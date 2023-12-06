package com.example.tmdt.repository;
import com.example.tmdt.dto.ProductDTO;
import com.example.tmdt.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByAccount_Id(Long id);
    List<Product> findAllByShop_Id (Long id);
    @Query(value = "SELECT * FROM product  " +
            "WHERE product.status IS NULL ",nativeQuery = true)
    List<Product> findAllStatus();
    List<Product> findAllByNameContainingAndStatusIsNull (String name);
    @Query(value = "select * from product where product.shop_id = ? and product.status is null order by product.count desc limit 5", nativeQuery = true)
    List<Product> findByCount(Long id);
    @Query(value = "select * from product where product.category_id = ?",nativeQuery = true)
    List<Product> findByCategory(Long id);

}
