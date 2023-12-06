package com.example.tmdt.model.buyPrd;
import com.example.tmdt.model.User;
import com.example.tmdt.security.model.Account;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.*;
@Data
@Entity
@Table(name = "Cart")
@EqualsAndHashCode(callSuper = false)
public class Cart{
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String confirm;
    private String reason;
    @ManyToOne
    private Account account ;
    @ManyToOne
    private User user ;

}

