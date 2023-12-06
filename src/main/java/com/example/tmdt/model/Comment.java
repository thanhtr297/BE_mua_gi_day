package com.example.tmdt.model;
import com.example.tmdt.model.Product;
import com.example.tmdt.security.model.Account;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "Comment")
@EqualsAndHashCode(callSuper = false)
public class Comment {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private String status;
    private LocalDateTime createAt;
    private String reply;
    @ManyToOne
    private Product product;
    @ManyToOne
    private Account account ;
    @ManyToOne
    private User user;

}
