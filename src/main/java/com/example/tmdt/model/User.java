package com.example.tmdt.model;

import javax.persistence.*;

import com.example.tmdt.model.address.Wards;
import com.example.tmdt.security.model.Account;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "User")
@EqualsAndHashCode(callSuper = false)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    private String name ;
    private String avatar ;
    private String gender ;
    private LocalDate age ;
    private String phone ;
    private String email ;
    private String address;
    @ManyToOne
    private Wards wards;
    @ManyToOne
    private Discount discount ;
    @OneToOne
    private Account account ;
    @Column(columnDefinition = "integer default 0")
    private Integer changeRole ;

}
