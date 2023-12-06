package com.example.tmdt.controller;


import com.example.tmdt.dto.BillDTO;
import com.example.tmdt.dto.CartDetailDTO;
import com.example.tmdt.model.buyPrd.BillDetail;
import com.example.tmdt.dto.BillDetailDTO;
import com.example.tmdt.service.IBillDetailService;
import com.example.tmdt.service.IBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/bills")
public class BillController {
    @Autowired
    private IBillService billService;
    @Autowired
    private IBillDetailService billDetailService;

//    @PostMapping
//    ResponseEntity<?> save(@RequestBody BillDTO billDTO) {
//        billService.save(billDTO);
//        return new ResponseEntity<>(HttpStatus.CREATED);
//    }
//    @PostMapping
//    ResponseEntity<?> addToBill (@RequestBody List<Long> idCartDetail) {
//        billDetailService.addToBill(idCartDetail);
//        return new ResponseEntity<>(HttpStatus.CREATED);
//    }

    @GetMapping
    ResponseEntity<List<BillDetailDTO>> showBill(@Param("idAccount") Long idAccount,
                                                 @Param("status") String status) {

    List<BillDetailDTO> billDetailDTOS = billDetailService.showBillByAccountAndStatus(idAccount, status);
    return new ResponseEntity<>(billDetailDTOS, HttpStatus.OK);
    }
    @PostMapping("/save/bill")
    ResponseEntity<?> saveToBill(@RequestBody List<CartDetailDTO> cartDetailDTOS,
            @Param("idAccount") Long idAccount) {
        billDetailService.addToBill(cartDetailDTOS, idAccount);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PostMapping("/accept")
    public ResponseEntity<?> accept (@RequestBody List<BillDetail> billDetails) {
       Integer integer = billService.accept(billDetails);
       if(integer == 0 ) {
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PostMapping("/rejection")
    public ResponseEntity<?> rejection (@RequestBody List<BillDetail> billDetails , @RequestParam("reason") String reason) {
        billService.rejection(billDetails ,reason);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{idBill}")
    ResponseEntity<?> cancelBill(@PathVariable("idBill") Long idBill) {
        billService.delete(idBill);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/cancel")
    public ResponseEntity<?> cancelBillByReason (@RequestParam Long idBill, @RequestParam("reason") String reason) {
        billService.cancelBillByReason(idBill ,reason);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PostMapping("/receive")
    public ResponseEntity<?> rejection (@RequestParam Long idBill) {
        billService.receive(idBill);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
