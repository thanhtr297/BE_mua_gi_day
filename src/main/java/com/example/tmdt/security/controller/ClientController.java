package com.example.tmdt.security.controller;


import com.example.tmdt.model.User;
import com.example.tmdt.security.DTO.sdi.ClientSdi;
import com.example.tmdt.security.model.Account;
import com.example.tmdt.security.repository.IAccountRepository;
import com.example.tmdt.security.service.ClientService;
import com.example.tmdt.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/client")
public class ClientController {
    @Autowired
    private ClientService clientService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IAccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping(value = "create")
    public ResponseEntity<?> create(
            @RequestBody ClientSdi sdi
    ) {
        List<String> email = userService.listNameEmail();
        List<String> acc = userService.listNameUser();

        if (acc.contains(sdi.getUsername()) || email.contains(sdi.getEmail())) {
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        clientService.create(sdi);
        return ResponseEntity.ok(sdi);
    }

    @PostMapping(value = "sendMail")
    public ResponseEntity<?> sendMail(@RequestBody ClientSdi sdi) {
        Long idAcc = Long.parseLong(sdi.getUsername());
        Optional<Account> account = accountRepository.findById(idAcc);
        if (passwordEncoder.matches(sdi.getPassword(), account.get().getPassword())){
            clientService.create(sdi);
            return ResponseEntity.ok(sdi);

        } else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
    @PostMapping(value = "sendMailPass")
    public ResponseEntity<?> sendMailNewPass(@RequestBody ClientSdi sdi){
        clientService.create(sdi);
        return ResponseEntity.ok(sdi);
    }
}