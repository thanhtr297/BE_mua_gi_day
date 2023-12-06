package com.example.tmdt.security.service.impl;
import com.example.tmdt.security.model.Role;
import com.example.tmdt.security.repository.IRoleRepository;
import com.example.tmdt.security.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private IRoleRepository iRoleRepository;

    @Override
    public Role findOne(Long id) {
        return iRoleRepository.findById(id).orElse(null);
    }

    @Override
    public Iterable<Role> findAll() {
        return iRoleRepository.findAll();
    }
}
