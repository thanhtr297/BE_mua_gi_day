package com.example.tmdt.service.impl;


import com.example.tmdt.dto.CartDetailDTO;
import com.example.tmdt.mapper.CartDetailMapper;
import com.example.tmdt.mapper.ProductMapper;
import com.example.tmdt.model.Product;
import com.example.tmdt.model.User;
import com.example.tmdt.model.buyPrd.Cart;
import com.example.tmdt.model.buyPrd.CartDetail;
import com.example.tmdt.model.fkProduct.Shop;
import com.example.tmdt.repository.CartDetailRepository;

import com.example.tmdt.repository.CartRepository;
import com.example.tmdt.repository.ShopRepository;
import com.example.tmdt.repository.UserRepository;
import com.example.tmdt.security.model.Account;
import com.example.tmdt.security.repository.IAccountRepository;
import com.example.tmdt.service.ICartDetailService;
import com.example.tmdt.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class  CartDetailService implements ICartDetailService {
    @Autowired
    private CartDetailRepository cartDetailRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartDetailMapper cartDetailMapper;
    @Autowired
    private IAccountRepository accountRepository;
    @Autowired
    private ShopRepository shopRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductMapper productMapper;



    @Override
    public void save(CartDetailDTO dto) {
        CartDetail cartDetail = cartDetailMapper.toEntity(dto);
        cartDetailMapper.toDto(cartDetailRepository.save(cartDetail));

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public CartDetailDTO findOne(Long id) {
        Optional<CartDetail> optionalCartDetail = cartDetailRepository.findById(id);
        if (optionalCartDetail.isPresent()) {
            CartDetail cartDetail = optionalCartDetail.get();
            return cartDetailMapper.toDto(cartDetail);
        }
        return null;
    }

    @Override
    public List<CartDetailDTO> findAll() {
        return null;
    }

    @Override
    public void addToCart(CartDetailDTO cartDetailDTO, Long idAccount) {
        Optional<Cart> cartOptional = cartRepository.findCartByIdAccount(idAccount);
        Cart cart;
        if (cartOptional.isPresent()) {
            cart = cartOptional.get();
        } else {
            cart = new Cart();
            Optional<Account> optionalAccount = accountRepository.findById(idAccount);
            if (optionalAccount.isPresent()) {
                User user = userRepository.findUserByAccount_Id(idAccount);
                Account account = optionalAccount.get();
                cart.setAccount(account);
                cart.setConfirm("0");
                cart.setUser(user);
            }
            cartRepository.save(cart);
        }
        Optional<CartDetail> cartDetailOptional = cartDetailRepository.findCartDetailByCartAndProduct(
                cart.getId(), cartDetailDTO.getProduct().getId());
        CartDetail cartDetail;
        if (cartDetailOptional.isPresent()) {
            cartDetail = cartDetailOptional.get();
            cartDetail.setQuantity(cartDetail.getQuantity() + cartDetailDTO.getQuantity());
        } else {
            cartDetail = new CartDetail();
            cartDetail.setQuantity(cartDetailDTO.getQuantity());
            Product product = productMapper.toEntity(cartDetailDTO.getProduct());
            cartDetail.setProduct(product);
            cartDetail.setCart(cart);
        }
        cartDetailRepository.save(cartDetail);
    }

    @Override
    public List<CartDetailDTO> showCart(Long idAccount) {
        List<CartDetail> cartDetails = cartDetailRepository.showCart(idAccount);
        return cartDetailMapper.toDto(cartDetails);
    }

    @Override
    public List<CartDetailDTO> displayListBuy(Long idShop, String confirm) {
        Shop shop = shopRepository.findShopByIdAccount(idShop) ;
        List<CartDetail> cartDetails = cartDetailRepository.displayCartOfShop(shop.getId(),confirm) ;
        return cartDetailMapper.toDto(cartDetailRepository.displayCartOfShop(shop.getId(),confirm));
    }

    @Override
    public List<CartDetailDTO> displayAllOrder(Long idShop) {
        List <CartDetail> cartDetail =  cartDetailRepository.findAllByProduct_Shop_Id(shopRepository.findShopByIdAccount(idShop).getId());
        List <CartDetail > dto = new ArrayList<>() ;
        for (CartDetail cart :
                cartDetail
             ) {
                if(cart.getCart().getConfirm().equals("1")
                        || cart.getCart().getConfirm().equals("2")
                        || cart.getCart().getConfirm().equals("3")
                        || cart.getCart().getConfirm().equals("5") ) {
                    dto.add(cart) ;
                }
        }
        return cartDetailMapper.toDto(dto);
    }

    @Override
    public CartDetailDTO findCartDetailByProduct(Long idProduct, Long idAccount) {
        Optional<CartDetail> cartDetailOptional = cartDetailRepository.findCartDetailByProduct(idProduct, idAccount);
        if (cartDetailOptional.isPresent()) {
            CartDetail cartDetail = cartDetailOptional.get();
            return cartDetailMapper.toDto(cartDetail);
        }
        return null;
    }


    @Override
    public void deleteProductFromCart(Long idCartDetail) {
        try {
            cartDetailRepository.deleteProductFromCart(idCartDetail);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    @Override
    public void deleteAllProductFromCart(Long idCart) {
        try {
            cartDetailRepository.deleteCart(idCart);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    @Override
    public void updateQuantityFromCart(Double quantity, Long idProduct, Long idCart) {
        cartDetailRepository.updateQuantityFromCart(quantity, idProduct, idCart);
    }

}


