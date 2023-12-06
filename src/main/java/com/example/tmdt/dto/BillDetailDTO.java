package com.example.tmdt.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BillDetailDTO {
    private Long id;
    private Double quantity ;
    private Double price ;
    private Double total ;
    private ProductDTO product ;
    private BillDTO bill;
}
