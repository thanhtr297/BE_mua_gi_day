package com.example.tmdt.service;

import com.example.tmdt.dto.UserDTO;
import com.example.tmdt.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserService extends BaseService<UserDTO> {
    List<String> listNameEmail() ;
    List<String> listNameUser() ;
    UserDTO findUserByAccount(Long id);

}
