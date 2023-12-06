package com.example.tmdt.dto;

import com.example.tmdt.model.fkProduct.*;
import com.example.tmdt.security.model.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.util.List;
@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Long id;
    private String name;
    private Integer quantity;
    private String description;
    private Double price;
    private Double promotion;
    private Integer count;
    private Integer status;
//    private List<Comment> comment;
    private Brand brand;
    private Shop shop;
    private OptionProduct option;
    private Category category;
    private List<ImageDTO> image;
    private Account account;
}
