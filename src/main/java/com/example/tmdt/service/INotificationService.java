package com.example.tmdt.service;

import com.example.tmdt.dto.NotificationDTO;

import java.util.List;

public interface INotificationService extends BaseService <NotificationDTO> {

    List<NotificationDTO> notificationShop(Long id);
    List<NotificationDTO> notificationUser(Long id);
    List<NotificationDTO> notiByAcc(Long id);
    void changeStatus (Long id) ;
}
