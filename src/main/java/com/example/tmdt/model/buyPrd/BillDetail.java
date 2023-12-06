package com.example.tmdt.model.buyPrd;
import com.example.tmdt.model.Product;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.*;
@Data
@Entity
@Table(name = "BillDetail")
@EqualsAndHashCode(callSuper = false)
public class BillDetail {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double quantity ;
    private Double price ;
    private Double total ;
    @ManyToOne
    private Product product ;
    @ManyToOne
    private Bill bill ;
}
