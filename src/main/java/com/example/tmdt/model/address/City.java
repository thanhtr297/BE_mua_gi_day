package com.example.tmdt.model.address;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
@Data

@Entity
@Table(name = "City")
@EqualsAndHashCode(callSuper = false)
public class City  {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}
