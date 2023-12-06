package com.example.tmdt.dto;

import com.example.tmdt.model.Product;
import com.example.tmdt.security.model.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.User;

import java.time.LocalDateTime;


@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {
    private Long id;
    private String content;
    private String status;
    private LocalDateTime createAt;
    private String reply;
    private ProductDTO product;
    private Account account;
    private UserDTO user;
}



