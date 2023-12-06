package com.example.tmdt.service.impl;

import com.example.tmdt.dto.NotificationDTO;
import com.example.tmdt.mapper.NotificationMapper;
import com.example.tmdt.model.Notification;
import com.example.tmdt.model.fkProduct.Image;
import com.example.tmdt.repository.NotificationRepository;
import com.example.tmdt.repository.ShopRepository;
import com.example.tmdt.service.INotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class NotificationService implements INotificationService {
    @Autowired
    private NotificationRepository notificationRepository ;
    @Autowired
    private NotificationMapper notificationMapper ;
    @Autowired
    private ShopRepository shopRepository ;
    @Override
    public void save(NotificationDTO dto) {
        Notification notification = notificationMapper.toEntity(dto);
        notificationRepository.save(notification);
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public NotificationDTO findOne(Long id) {
        Notification notification = notificationRepository.findById(id).get() ;
        return notificationMapper.toDto(notification);
    }

    @Override
    public List<NotificationDTO> findAll() {
        List<Notification> notifications = notificationRepository.findAll() ;
        return notificationMapper.toDto(notifications);
    }

    @Override
    public List<NotificationDTO> notificationShop(Long id) {
        try {
            return notificationMapper.toDto(notificationRepository.findNotificationByShop(shopRepository.findShopByIdAccount(id).getId()));
        }catch (Exception e) {
            return new ArrayList<>() ;
        }
    }

    @Override
    public List<NotificationDTO> notificationUser(Long id) {
        try {
            return notificationMapper.toDto(notificationRepository.findNotificationByAccount(id));
        }catch (Exception e) {
            return new ArrayList<>() ;
        }
    }

    @Override
    public List<NotificationDTO> notiByAcc(Long id) {
        try {
            return notificationMapper.toDto(notificationRepository.findNotiByAcc(id,shopRepository.findShopByIdAccount(id).getId()));
        }catch (Exception e) {
            return new ArrayList<>() ;
        }
    }

    @Override
    public void changeStatus(Long id) {
        Notification notification = notificationRepository.findById(id).get() ;
        notification.setStatus(1L);
        notificationRepository.save(notification) ;
    }
}
