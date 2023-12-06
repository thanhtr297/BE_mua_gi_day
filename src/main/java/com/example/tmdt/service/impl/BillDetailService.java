package com.example.tmdt.service.impl;

import com.example.tmdt.dto.BillDetailDTO;
import com.example.tmdt.dto.CartDetailDTO;
import com.example.tmdt.mapper.BillDetailMapper;
import com.example.tmdt.mapper.BillMapper;
import com.example.tmdt.mapper.CartDetailMapper;
import com.example.tmdt.mapper.ProductMapper;
import com.example.tmdt.model.Notification;
import com.example.tmdt.model.Product;
import com.example.tmdt.model.User;
import com.example.tmdt.model.buyPrd.Bill;
import com.example.tmdt.model.buyPrd.BillDetail;
import com.example.tmdt.model.buyPrd.CartDetail;
import com.example.tmdt.model.fkProduct.Shop;
import com.example.tmdt.repository.*;
import com.example.tmdt.service.IBillDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class BillDetailService implements IBillDetailService {
    @Autowired
    private CartDetailRepository cartDetailRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private BillRepository billRepository;
    @Autowired
    private BillDetailRepository billDetailRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ShopRepository shopRepository;
    @Autowired
    private BillDetailMapper billDetailMapper;
    @Autowired
    private BillMapper billMapper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CartDetailMapper cartDetailMapper;
    @Autowired
    private NotificationRepository notificationRepository ;


    @Override
    public void save(BillDetailDTO dto) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public BillDetailDTO findOne(Long id) {
        return null;
    }

    @Override
    public List<BillDetailDTO> findAll() {
        return null;
    }


    @Override
    public void addToBill(List<CartDetailDTO> cartDetailDTOS, Long idAccount) {
        User user = userRepository.findUserByAccount_Id(idAccount);
        List<Long> ids = new ArrayList<>();
        ids.add(cartDetailDTOS.get(0).getProduct().getShop().getId());
        if (user.getAddress() != null && user.getPhone() != null) {
            List<CartDetail> cartDetails = cartDetailMapper.toEntity(cartDetailDTOS);
            for (CartDetail cartDetail : cartDetails) {
                for (Long id : ids) {
                    if (!cartDetail.getProduct().getShop().getId().equals(id)) {
                        ids.add(cartDetail.getProduct().getShop().getId());
                        break;
                    }
                }
            }
            for (Long id : ids)  {
                createBillDetail1(cartDetailDTOS, id, user);
            }
        }
    }

    private void createBillDetail1(List<CartDetailDTO> cartDetailDTOS, Long shopId , User user) {
        Shop shop = shopRepository.findById(shopId).get();
        Bill bill = new Bill();
        bill.setShop(shop);
        bill.setName(user.getName());
        bill.setPhone(user.getPhone());
        bill.setAddress(user.getAddress());
        bill.setWards(user.getWards());
        bill.setDate(LocalDate.now());
        bill.setStatus("Chờ xác nhận");
        bill.setAccount(cartDetailDTOS.get(0).getCart().getAccount());
        bill = billRepository.save(bill);
        User user1 = userRepository.findUserByAccount_Id(bill.getAccount().getId());
        Notification notification = new Notification() ;
        notification.setBill(bill);
        notification.setTitle("Thông báo shop");
        notification.setContent("Đơn hàng đã được đặt");
        notification.setAvatar(user1.getAvatar());
        notification.setCreateAt(LocalDateTime.now());
        notification.setShop(shop);
        notification.setAccount(bill.getAccount());
        notificationRepository.save(notification) ;
        for (CartDetailDTO cartDetailDTO : cartDetailDTOS) {
            if (cartDetailDTO.getProduct().getShop().getId().equals(bill.getShop().getId())) {
                CartDetail cartDetail = cartDetailMapper.toEntity(cartDetailDTO);
                BillDetail billDetail = new BillDetail();
                billDetail.setBill(bill);
                billDetail.setProduct(cartDetail.getProduct());
                billDetail.setQuantity(cartDetail.getQuantity());
                Double newPrice = cartDetail.getProduct().getPrice() - (cartDetail.getProduct().getPrice() * cartDetail.getProduct().getPromotion() / 100);
                billDetail.setPrice(newPrice);
                Double total = cartDetail.getQuantity() * newPrice;
                billDetail.setTotal(total);
                Double quantity = cartDetail.getQuantity();
                Product product = cartDetail.getProduct();
                if (quantity <= product.getQuantity() && quantity >= 1) {
                    productRepository.save(product);
                    billDetailRepository.save(billDetail);
                }
                cartDetailRepository.deleteCartDetailByProduct(product.getId());
            }
        }
    }


    @Override
    public List<BillDetailDTO> showBillByAccountAndStatus(Long idAccount, String status) {
        List<BillDetail> billDetails = billDetailRepository.showBillByAccountAndStatus(idAccount, status);
        return billDetailMapper.toDto(billDetails);

    }



    @Override
    public List<BillDetailDTO> displayListBuy(Long idShop, String status) {
        return null;
    }

    @Override
    public List<BillDetailDTO> findByShop(Long idShop) {
        Shop shop = shopRepository.findShopByIdAccount(idShop);
        try {
            return billDetailMapper.toDto(billDetailRepository.findAllByProduct_Shop_Id(shop.getId()));
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    @Override
    public List<CartDetailDTO> showProductUserSelect(List<Long> idCartDetails) {
        List<CartDetail> cartDetails = new ArrayList<>();
        for (Long idCartDetail : idCartDetails) {
            Optional<CartDetail> cartDetailOptional = cartDetailRepository.findById(idCartDetail);
            if (cartDetailOptional.isPresent()) {
                CartDetail cartDetail = cartDetailOptional.get();
                cartDetails.add(cartDetail);
            }
        }
        return cartDetailMapper.toDto(cartDetails);
    }

    @Override
    public List<BillDetailDTO> totalByDate(LocalDate date, Long idShop) {
        List<BillDetail> billDetails = billDetailRepository.totalByDate(date,idShop);
        return billDetailMapper.toDto(billDetails);
    }

    @Override
    public List<BillDetailDTO> totalByWeek(LocalDate date1, LocalDate date2, Long idShop) {
        List<BillDetail> billDetails = billDetailRepository.totalByWeek(date1,date2,idShop);
        return billDetailMapper.toDto(billDetails);
    }

    @Override
    public List<BillDetailDTO> totalBill(LocalDate date1, LocalDate date2, Long idShop) {
        List<BillDetail> billDetails = billDetailRepository.totalBill(date1,date2,idShop);
        return billDetailMapper.toDto(billDetails);
    }
}
