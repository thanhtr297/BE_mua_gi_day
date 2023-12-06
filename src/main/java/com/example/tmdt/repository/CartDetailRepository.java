package com.example.tmdt.repository;

import com.example.tmdt.model.buyPrd.Cart;
import com.example.tmdt.model.buyPrd.CartDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface CartDetailRepository extends JpaRepository<CartDetail, Long> {
    @Query(value = "SELECT * FROM cart_detail WHERE cart_id = :idCart AND product_id = :idProduct", nativeQuery = true)
    Optional<CartDetail> findCartDetailByCartAndProduct(@Param("idCart") Long idCart,
                                                       @Param("idProduct") Long idProduct);

    @Query(value = "SELECT * FROM cart_detail cd JOIN cart c ON cd.cart_id = c.id WHERE c.account_id = :idAccount", nativeQuery = true)
    List<CartDetail> showCart(@Param("idAccount") Long idAccount);

//    @Query(value = "SELECT * FROM cart WHERE account_id = :idAccount", nativeQuery = true)
//    Optional<Cart> findCartByIdAccount(@Param("idAccount") Long idAccount);

    @Query(value = "select * from cart_detail\n" +
            "inner join product on cart_detail.product_id = product.id\n" +
            "inner join cart on cart_detail.cart_id = cart.id where product.shop_id = :idShop and cart.confirm = :confirm ;\n" +
            ";", nativeQuery = true)
    List<CartDetail> displayCartOfShop(@Param("idShop") Long idShop ,@Param("confirm") String confirm);
    List<CartDetail> findAllByProduct_Shop_Id(Long idShop);


    @Modifying
    @Query(value = "DELETE FROM cart_detail WHERE cart_detail.id = :idCartDetail", nativeQuery = true)
    void deleteProductFromCart(@Param("idCartDetail") Long idCartDetail);
    @Modifying
    @Query(value = "DELETE FROM cart_detail WHERE cart_id = :idCart", nativeQuery = true)
    void deleteCart(@Param("idCart") Long idCart);
    @Modifying
    @Query(value = "UPDATE cart_detail SET quantity = :quantity WHERE product_id = :idProduct AND cart_id = :idCart", nativeQuery = true)
    void updateQuantityFromCart(@Param("quantity") Double quantity,
                                @Param("idProduct") Long idProduct,
                                @Param("idCart") Long idCart);

    @Modifying
    @Query(value = "DELETE FROM cart_detail WHERE product_id = :idProduct", nativeQuery = true)
    void deleteCartDetailByProduct(@Param("idProduct") Long idProduct);

    @Query(value = "SELECT * FROM cart_detail cd JOIN cart c ON cd.cart_id = c.id WHERE cd.product_id = :idProduct AND c.account_id = :idAccount order by cd.id DESC limit 1",nativeQuery = true)
    Optional<CartDetail> findCartDetailByProduct(@Param("idProduct") Long idProduct,
                                                 @Param("idAccount") Long idAccount);



 }
