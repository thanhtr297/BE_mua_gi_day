package com.example.tmdt.dto;

import com.example.tmdt.model.fkProduct.Shop;
import com.example.tmdt.security.model.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BillDTO {
    private Long id;
    private String name;
    private Account account ;
    private String address;
    private WardsDTO wards;
    private String phone;
    private String total;
    private LocalDate date;
    private Shop shop;
    private String status ;
    private String reason;
}
