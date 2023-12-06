package com.example.tmdt.mapper;

import com.example.tmdt.dto.BillDTO;
import com.example.tmdt.model.buyPrd.Bill;
import org.mapstruct.Mapper;

@Mapper(componentModel = "Spring")
public interface BillMapper extends EntityMapper<BillDTO, Bill> {
}
