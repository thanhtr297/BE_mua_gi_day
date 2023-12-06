package com.example.tmdt.model.fkProduct;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
@Data

@Entity
@Table(name = "OptionProduct")
@EqualsAndHashCode(callSuper = false)
public class OptionProduct  {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}
