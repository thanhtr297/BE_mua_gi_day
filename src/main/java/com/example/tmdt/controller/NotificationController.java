package com.example.tmdt.controller;

import com.example.tmdt.dto.BillDTO;
import com.example.tmdt.dto.NotificationDTO;
import com.example.tmdt.service.INotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/notification")
public class NotificationController {
    @Autowired
    private INotificationService notificationService ;
    @GetMapping("/shop/{id}")
    ResponseEntity<List<NotificationDTO>> findNotificationShop(@PathVariable Long id ) {
       return new ResponseEntity<>(notificationService.notificationShop(id), HttpStatus.OK);
    }
    @GetMapping("/user/{id}")
    ResponseEntity<List<NotificationDTO>> findNotificationUser(@PathVariable Long id ) {
        List<NotificationDTO> notificationDTOS = notificationService.notificationUser(id);
        return new ResponseEntity<>(notificationDTOS, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    ResponseEntity<List<NotificationDTO>> findNotificationAcc(@PathVariable Long id ) {
        List<NotificationDTO> notificationDTOS = notificationService.notiByAcc(id);
        return new ResponseEntity<>(notificationDTOS, HttpStatus.OK);
    }
    @PostMapping("/{id}")
    ResponseEntity<?> changeStatus(@PathVariable Long id ) {
        notificationService.changeStatus(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
