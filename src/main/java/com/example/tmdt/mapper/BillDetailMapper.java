package com.example.tmdt.mapper;

import com.example.tmdt.dto.BillDetailDTO;
import com.example.tmdt.model.buyPrd.BillDetail;
import org.mapstruct.Mapper;

@Mapper(componentModel = "Spring")
public interface BillDetailMapper extends EntityMapper<BillDetailDTO, BillDetail>{
}
