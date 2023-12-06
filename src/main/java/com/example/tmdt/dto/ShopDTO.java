package com.example.tmdt.dto;


import com.example.tmdt.security.model.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShopDTO {
    private Long id;
    private String name;
    private String avatar;
    private String phone;
    private Account account ;
    private String address ;
    private WardsDTO wards;
}
