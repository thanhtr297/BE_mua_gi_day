package com.example.tmdt.service;

import com.example.tmdt.dto.BillDTO;
import com.example.tmdt.model.buyPrd.BillDetail;

import java.util.List;

public interface IBillService extends BaseService<BillDTO> {
    List<BillDTO> findByShop (Long id) ;
    Integer accept (List<BillDetail> billDetails) ;
    void rejection (List<BillDetail> billDetails , String reason) ;
    void cancelBillByReason(Long idBill, String reason);
    void receive(Long idBill);
}
