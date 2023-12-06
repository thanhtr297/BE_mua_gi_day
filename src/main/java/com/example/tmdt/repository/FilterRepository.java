package com.example.tmdt.repository;

import com.example.tmdt.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilterRepository extends JpaRepository<Product, Long> {
    @Query(value = "select * " +
            "from product p " +
            "join brand b on p.brand_id = b.id " +
            "join category c on p.category_id = c.id " +
            "join shop s on s.id = p.shop_id " +
            "join wards w on w.id = s.wards_id " +
            "join district d on w.district_id = d.id " +
            "join city ct on d.city_id = ct.id " +
            "where (:category_id is null or p.category_id = :category_id) " +
            "and (:brand_id is null or p.brand_id = :brand_id) " +
            "and (:maxPrice is null or (p.price - (p.price* (p.promotion/100))) <= :maxPrice) " +
            "and (:minPrice is null or (p.price - (p.price* (p.promotion/100))) >= :minPrice) " +
            "and (:wards_id is null or s.wards_id = :wards_id) " +
            "and (:district_id is null or w.district_id = :district_id) " +
            "and (:city_id is null or d.city_id = :city_id) " +
            "and (p.status is null) " +
            "order by count desc",
            nativeQuery = true)
    List<Product> searchFilter(@Param("category_id") Long category_id,
                               @Param("brand_id") Long brand_id,
                               @Param("maxPrice") Double maxPrice,
                               @Param("minPrice") Double minPrice,
                               @Param("wards_id") Long wards_id,
                               @Param("district_id") Long district_id,
                               @Param("city_id") Long city_id);

    @Query(value = "select * " +
            "from product p " +
            "join brand b on p.brand_id = b.id " +
            "join category c on p.category_id = c.id " +
            "join shop s on s.id = p.shop_id " +
            "where (p.shop_id = :shop_id) " +
            "and (:category_id is null or p.category_id = :category_id) " +
            "and (:brand_id is null or p.brand_id = :brand_id) " +
            "and (:maxPrice is null or (p.price - (p.price* (p.promotion/100))) <= :maxPrice) " +
            "and (:minPrice is null or (p.price - (p.price* (p.promotion/100))) >= :minPrice) " +
            "and (p.status is null) " +
            "order by count desc",
            nativeQuery = true)
    List<Product> searchFilterForShop(@Param("shop_id") Long shop_id,
                                      @Param("category_id") Long category_id,
                                      @Param("brand_id") Long brand_id,
                                      @Param("maxPrice") Double maxPrice,
                                      @Param("minPrice") Double minPrice);

}
