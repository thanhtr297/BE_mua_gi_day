package com.example.tmdt.service.impl;

import com.example.tmdt.dto.BillDTO;
import com.example.tmdt.mapper.BillMapper;
import com.example.tmdt.model.Notification;
import com.example.tmdt.model.Product;
import com.example.tmdt.model.buyPrd.Bill;
import com.example.tmdt.model.buyPrd.BillDetail;
import com.example.tmdt.model.fkProduct.Shop;
import com.example.tmdt.repository.*;
import com.example.tmdt.service.IBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BillService implements IBillService {
    @Autowired
    private BillRepository billRepository;
    @Autowired
    private BillMapper billMapper;

    @Autowired
    private ShopRepository shopRepository;
    @Autowired
    private ProductRepository  productRepository ;
    @Autowired
    private BillDetailRepository billDetailRepository ;
    @Autowired
    private NotificationRepository notificationRepository ;
    @Autowired
    private UserRepository userRepository ;
    @Override
    public void save(BillDTO dto) {
        Bill bill = billMapper.toEntity(dto);
        billRepository.save(bill);

    }

    @Override
    public void delete(Long id) {
        Optional<Bill> billOptional = billRepository.findById(id);
        if (billOptional.isPresent()) {
            Bill bill = billOptional.get();
            bill.setStatus("Đơn hủy");
            billRepository.save(bill);
        }
    }

    @Override
    public BillDTO findOne(Long id) {

        return null;
    }

    @Override
    public List<BillDTO> findAll() {
        return null;
    }

    @Override
    public List<BillDTO> findByShop(Long id) {
        Shop shop = shopRepository.findShopByIdAccount(id);
        return billMapper.toDto(billRepository.findBillByShop(shop.getId()));
    }

    @Override
    public Integer accept(List<BillDetail> billDetails) {
        for (BillDetail id:
                billDetails ) {
            Product product = id.getProduct();
            if(product.getQuantity() - id.getQuantity() < 0){
                return 0;
            }
        }
        Bill dto = billRepository.findById(billDetails.get(0).getBill().getId()).get();
        dto.setStatus("Đang giao");
        billRepository.save(dto);
        Notification notification = new Notification() ;
        notification.setBill(dto);
        notification.setTitle("Thông báo user");
        notification.setContent("Đơn hàng được xác nhận");
        notification.setAvatar(dto.getShop().getAvatar());
        notification.setCreateAt(LocalDateTime.now());
        notification.setShop(billDetails.get(0).getProduct().getShop());
        notification.setAccount(dto.getAccount());
        notificationRepository.save(notification) ;

        for (BillDetail id:
             billDetails ) {
           Product product = id.getProduct();
           product.setQuantity((int) (product.getQuantity() - id.getQuantity()));
           productRepository.save(product);
        }
        return 1 ;
    }

    @Override
    public void rejection(List<BillDetail> billDetails , String reason) {
        Bill dto = billRepository.findById(billDetails.get(0).getBill().getId()).get();
        dto.setStatus("Đơn hủy");
        dto.setReason(reason);
        billRepository.save(dto);
        Notification notification = new Notification() ;
        notification.setBill(dto);
        notification.setTitle("Thông báo user");
        notification.setContent("Đơn hàng bị từ chối");
        notification.setAvatar(dto.getShop().getAvatar());
        notification.setCreateAt(LocalDateTime.now());
        notification.setShop(dto.getShop());
        notification.setAccount(dto.getAccount());
        notificationRepository.save(notification) ;
        for (BillDetail id:
                billDetails ) {
            Product product = id.getProduct();
            product.setQuantity((int) (product.getQuantity() + id.getQuantity()));
            productRepository.save(product);
        }
    }

    @Override
    public void cancelBillByReason(Long idBill, String reason) {
       Optional<Bill> billOptional = billRepository.findById(idBill);
       if (billOptional.isPresent()) {
           Bill bill = billOptional.get();
           bill.setStatus("Đơn bị hủy");
           bill.setDate(LocalDate.now());
           bill.setReason(reason);
           billRepository.save(bill);
           Notification notification = new Notification() ;
           notification.setBill(bill);
           notification.setTitle("Thông báo shop");
           notification.setContent("Đơn hàng bị hủy");
           notification.setAvatar(userRepository.findUserByAccount_Id(bill.getAccount().getId()).getAvatar());
           notification.setCreateAt(LocalDateTime.now());
           notification.setShop(bill.getShop());
           notification.setAccount(bill.getAccount());
           notificationRepository.save(notification) ;
           List<BillDetail> billDetails = billDetailRepository.listBillDetailByBill(idBill);
           for (BillDetail billDetail : billDetails) {
               billDetail.getProduct().setQuantity((int) (billDetail.getProduct().getQuantity() + billDetail.getQuantity()));
               productRepository.save(billDetail.getProduct());
           }

       }
    }

    @Override
    public void receive(Long idBill) {
        Optional<Bill> billOptional = billRepository.findById(idBill);
        if (billOptional.isPresent()) {
            Bill bill = billOptional.get();
            bill.setStatus("Đã giao");
            bill.setDate(LocalDate.now());
            billRepository.save(bill);
            Notification notification = new Notification() ;
            notification.setBill(bill);
            notification.setTitle("Thông báo shop");
            notification.setContent("Đơn hàng đã được giao");
            notification.setAvatar(userRepository.findUserByAccount_Id(bill.getAccount().getId()).getAvatar());
            notification.setCreateAt(LocalDateTime.now());
            notification.setShop(bill.getShop());
            notification.setAccount(bill.getAccount());
            notificationRepository.save(notification) ;
           List<BillDetail> billDetails = billDetailRepository.listBillDetailByBill(idBill);
           for (BillDetail billDetail : billDetails) {
               billDetail.getProduct().setCount(billDetail.getQuantity());
               productRepository.save(billDetail.getProduct());
           }

        }
    }

}
