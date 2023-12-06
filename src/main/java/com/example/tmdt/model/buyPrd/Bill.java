package com.example.tmdt.model.buyPrd;
import com.example.tmdt.model.address.Wards;
import com.example.tmdt.model.fkProduct.Shop;
import com.example.tmdt.security.model.Account;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.*;
import java.time.LocalDate;
@Data
@Entity
@Table(name = "Bill")
@EqualsAndHashCode(callSuper = false)
public class Bill {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne
    private Account account ;
    private String address;
    @ManyToOne
    private Wards wards;
    private String phone;
    private String total;
    private LocalDate date ;
    private String reason;
    @ManyToOne
    private Shop shop;
    @Column(columnDefinition = "varchar(255) default '0'")
    private String status;
}
