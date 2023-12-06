package com.example.tmdt.service;

import com.example.tmdt.dto.BillDetailDTO;
import com.example.tmdt.dto.CartDetailDTO;
import com.example.tmdt.model.buyPrd.BillDetail;

import java.time.LocalDate;
import java.util.List;

public interface IBillDetailService extends BaseService<BillDetailDTO> {
    void addToBill(List<CartDetailDTO> cartDetailDTOS, Long idAccount);
    List<BillDetailDTO> showBillByAccountAndStatus(Long idAccount, String status);

//    void saveToBill(List<BillDetailDTO> billDetailDTOS , Long idAccount);
    List<BillDetailDTO> displayListBuy(Long idShop , String status);
    List<BillDetailDTO> findByShop(Long idShop);

    List<CartDetailDTO> showProductUserSelect(List<Long> idCartDetails);
    List<BillDetailDTO> totalByDate(LocalDate date,Long idShop);
    List<BillDetailDTO> totalByWeek(LocalDate date1, LocalDate date2,Long idShop);
    List<BillDetailDTO> totalBill(LocalDate date1, LocalDate date2,Long idShop);


}
