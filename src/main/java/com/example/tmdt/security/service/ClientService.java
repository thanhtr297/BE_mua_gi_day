package com.example.tmdt.security.service;


import com.example.tmdt.security.DTO.sdi.ClientSdi;

public interface ClientService {
    Boolean create(ClientSdi sdi);
}