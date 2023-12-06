package com.example.tmdt.model;
import com.example.tmdt.model.fkProduct.*;
import com.example.tmdt.security.model.Account;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.List;
@Data
@Entity
@Table(name = "Product")
@EqualsAndHashCode(callSuper = false)
public class Product {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    @Min(value = 0)
    private Integer quantity;
    private String description;
    @Column(nullable = false)
    @Min(value = 1)
    private Double price;
    @Min(value = 0)
    private Double promotion;
    @Column(columnDefinition = "integer default 0")
    private Double count;
    @Column(nullable = true)
    private Integer status;
//    @OneToMany(cascade = CascadeType.ALL)
//    private List<Comment> comment;
    @ManyToOne
    private Brand brand;
    @ManyToOne
    private Shop shop;
    @ManyToOne
    private OptionProduct option;
    @ManyToOne
    private Category category;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Image> image;
    @ManyToOne
    private Account account;


}