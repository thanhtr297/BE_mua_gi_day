package com.example.tmdt.mapper;

import com.example.tmdt.dto.NotificationDTO;
import com.example.tmdt.model.Notification;
import org.mapstruct.Mapper;

@Mapper(componentModel = "Spring")
public interface NotificationMapper extends EntityMapper<NotificationDTO , Notification>{
}
