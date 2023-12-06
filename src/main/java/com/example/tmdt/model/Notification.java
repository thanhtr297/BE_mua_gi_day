package com.example.tmdt.model;

import com.example.tmdt.model.buyPrd.Bill;
import com.example.tmdt.model.fkProduct.Shop;
import com.example.tmdt.security.model.Account;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "Notification")
@EqualsAndHashCode(callSuper = false)
public class Notification {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title ;
    private String content ;
    private LocalDateTime createAt;
    private Long status ;
    private String avatar ;
    @ManyToOne
    private Account account ;
    @ManyToOne
    private Product product;
    @ManyToOne
    private Shop shop;
    @ManyToOne
    private Bill bill;
}
