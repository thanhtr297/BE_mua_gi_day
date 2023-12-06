package com.example.tmdt.dto;

import com.example.tmdt.security.model.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDTO {

    private Long id;
    private String confirm;
    private String reason;
    private Account account ;
    private UserDTO user ;
    private Double TotalPrice;
}

