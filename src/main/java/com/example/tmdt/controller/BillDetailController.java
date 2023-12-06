package com.example.tmdt.controller;

import com.example.tmdt.dto.BillDTO;
import com.example.tmdt.dto.BillDetailDTO;
import com.example.tmdt.dto.CartDetailDTO;
import com.example.tmdt.service.IBillDetailService;
import com.example.tmdt.service.IBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/billDetails")
public class BillDetailController {
    @Autowired
    private IBillDetailService billDetailService;
    @Autowired
    private IBillService billService;

    @GetMapping("/shop/{idAcc}")
    ResponseEntity<List<BillDetailDTO>> findBillDetail(@PathVariable Long idAcc) {
        List<BillDetailDTO> billDetailDTOS = billDetailService.findByShop(idAcc);
        return new ResponseEntity<>(billDetailDTOS, HttpStatus.OK);
    }

    @GetMapping("/shop/bill/{idAcc}")
    ResponseEntity<List<BillDTO>> findBill(@PathVariable Long idAcc) {
        List<BillDTO> billDTOS = billService.findByShop(idAcc);
        return new ResponseEntity<>(billDTOS, HttpStatus.OK);
    }

    @GetMapping("/total/{date}/{idShop}")
    ResponseEntity<List<BillDetailDTO>> totalByDate(@PathVariable String date,
                                                    @PathVariable Long idShop) {
        List<BillDetailDTO> billDetailDTOS = billDetailService.totalByDate(convertToLocalDate(date), idShop);
        return new ResponseEntity<>(billDetailDTOS, HttpStatus.OK);
    }

    @GetMapping("/totalWeek/{date1}/{date2}/{idShop}")
    ResponseEntity<List<BillDetailDTO>> totalByWeek(@PathVariable String date1,
                                                    @PathVariable String date2,
                                                    @PathVariable Long idShop) {
        List<BillDetailDTO> billDetailDTOS = billDetailService
                .totalByWeek(convertToLocalDate(date1), convertToLocalDate(date2), idShop);
        return new ResponseEntity<>(billDetailDTOS, HttpStatus.OK);
    }

    @GetMapping("/totalBill/{date1}/{date2}/{idShop}")
    ResponseEntity<List<BillDetailDTO>> totalBill(@PathVariable String date1,
                                                  @PathVariable String date2,
                                                  @PathVariable Long idShop) {
        List<BillDetailDTO> billDetailDTOS = billDetailService
                .totalBill(convertToLocalDate(date1), convertToLocalDate(date2), idShop);
        return new ResponseEntity<>(billDetailDTOS, HttpStatus.OK);
    }

    private LocalDate convertToLocalDate(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(dateString, formatter);
    }

}
