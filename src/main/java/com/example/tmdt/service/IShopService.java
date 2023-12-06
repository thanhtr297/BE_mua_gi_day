package com.example.tmdt.service;

import com.example.tmdt.dto.ShopDTO;

public interface IShopService extends BaseService<ShopDTO>{
    ShopDTO findShopByIdAccount(Long id);

}
