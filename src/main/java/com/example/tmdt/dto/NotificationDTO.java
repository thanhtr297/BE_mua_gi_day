package com.example.tmdt.dto;

import com.example.tmdt.model.Product;
import com.example.tmdt.model.buyPrd.Bill;
import com.example.tmdt.model.fkProduct.Shop;
import com.example.tmdt.security.model.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationDTO {
    private Long id;
    private String content ;
    private Long status ;
    private String title ;
    private LocalDateTime createAt;
    private Account account ;
    private Product product;
    private Shop shop;
    private Bill bill;
    private String avatar ;
}
