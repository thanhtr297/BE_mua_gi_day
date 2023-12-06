package com.example.tmdt.repository;

import com.example.tmdt.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification,Long> {
    @Query(value = "select * from notification where notification.account_id = :id and notification.title = 'Thông báo user' ", nativeQuery = true)
    List<Notification> findNotificationByAccount(Long id);
    @Query(value = "select * from notification where notification.shop_id = :id and notification.title = 'Thông báo shop' ", nativeQuery = true)
    List<Notification> findNotificationByShop(Long id);
    @Query(value = "select * from notification where notification.account_id = :id OR notification.shop_id = :idShop ORDER BY id DESC " ,nativeQuery = true)
    List<Notification> findNotiByAcc (@Param("id") Long id ,@Param("idShop") Long idShop) ;

}
