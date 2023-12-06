package com.example.tmdt.security.service;


import com.example.tmdt.security.model.Role;

public interface IRoleService {
    Role findOne(Long id);

    Iterable<Role> findAll();
}
