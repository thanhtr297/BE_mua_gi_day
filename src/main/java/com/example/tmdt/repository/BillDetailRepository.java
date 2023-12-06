package com.example.tmdt.repository;

import com.example.tmdt.model.buyPrd.BillDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface BillDetailRepository extends JpaRepository<BillDetail, Long> {
    @Query(value = "SELECT * FROM bill_detail bd JOIN bill on bd.bill_id = bill.id WHERE account_id = :idAccount", nativeQuery = true)
    List<BillDetail> showBillByAccount(@Param("idAccount") Long idAccount);

    @Query(value = "SELECT * FROM bill WHERE  shop_id = :idShop", nativeQuery = true)
    List<BillDetail> findBillDetailByShop(@Param("idShop") Long idShop);

    List<BillDetail> findAllByProduct_Shop_Id(Long idShop);

    @Query(value = "select * from bill_detail\n" +
            "            inner join product on bill_detail.product_id = product.id\n" +
            "            inner join bill on bill_detail.bill_id = bill.id where product.shop_id = :idShop and bill.status = :status\n" +
            ";", nativeQuery = true)
    List<BillDetail> displayBillOfShop(@Param("idShop") Long idShop, @Param("status") String status);

    @Query(value = "SELECT * FROM bill_detail bd JOIN bill on bill_id = bill.id WHERE account_id = :idAccount AND bill.status = :status ORDER BY bd.id DESC", nativeQuery = true)
    List<BillDetail> showBillByAccountAndStatus(@Param("idAccount") Long idAccount,
                                                @Param("status") String status);

    @Query(value = "SELECT * FROM bill_detail WHERE bill_detail.bill_id = :idBill", nativeQuery = true)
    List<BillDetail> listBillDetailByBill(@Param("idBill") Long idBill);

    @Query(value = "select * from bill_detail bd join bill b on bd.bill_id = b.id " +
            " where b.status = 'Đã giao' and b.date = :date " +
            " and b.shop_id = :idShop ", nativeQuery = true)
    List<BillDetail> totalByDate(@Param("date") LocalDate date,
                                 @Param("idShop") Long idShop);

    @Query(value = "select * from bill_detail bd join bill b on bd.bill_id = b.id " +
            " where b.status = 'Đã giao' " +
            " and b.date between :date1 and :date2 " +
            " and b.shop_id = :idShop ", nativeQuery = true)
    List<BillDetail> totalByWeek(@Param("date1") LocalDate date1,
                                 @Param("date2") LocalDate date2,
                                 @Param("idShop") Long idShop);

    @Query(value = "select * from bill_detail bd join bill b on bd.bill_id = b.id " +
            " where b.date between :date1 and :date2 " +
            " and b.shop_id = :idShop ", nativeQuery = true)
    List<BillDetail> totalBill(@Param("date1") LocalDate date1,
                               @Param("date2") LocalDate date2,
                               @Param("idShop") Long idShop);
}
