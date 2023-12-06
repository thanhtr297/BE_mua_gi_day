package com.example.tmdt.service.impl;

import com.example.tmdt.dto.CartDTO;
import com.example.tmdt.dto.CartDetailDTO;
import com.example.tmdt.mapper.CartDetailMapper;
import com.example.tmdt.mapper.CartMapper;
import com.example.tmdt.model.buyPrd.Cart;
import com.example.tmdt.model.buyPrd.CartDetail;
import com.example.tmdt.repository.CartDetailRepository;
import com.example.tmdt.repository.CartRepository;
import com.example.tmdt.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService implements ICartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartMapper cartMapper;


    @Override
    public void save(CartDTO dto) {
        Cart cart = cartRepository.findById(dto.getId()).get();
        cart.setConfirm(dto.getConfirm());
        cart.setReason(dto.getReason());
        cartMapper.toDto(cartRepository.save(cart));
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public CartDTO findOne(Long id) {

        return null;
    }

    @Override
    public List<CartDTO> findAll() {
        return null;
    }


}
