package com.example.tmdt.controller;

import com.example.tmdt.dto.ShopDTO;
import com.example.tmdt.dto.UserDTO;
import com.example.tmdt.security.model.Account;
import com.example.tmdt.security.repository.IAccountRepository;
import com.example.tmdt.security.service.IAccountService;
import com.example.tmdt.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@CrossOrigin("*")
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private IUserService userService ;
    @Autowired
    private IAccountRepository account ;

    @Autowired
    private IAccountService accountService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @GetMapping("/email")
    public ResponseEntity<Iterable<String>> findListNameEmail() {
        return new ResponseEntity<>(userService.listNameEmail(), HttpStatus.OK);
    }
    @GetMapping("/acc/{id}")
    public ResponseEntity<?> findAccById(@PathVariable Long id) {
        return new ResponseEntity<>(account.findById(id), HttpStatus.OK);
    }
    @PostMapping("/change")
    public ResponseEntity<String> saveAccount(@RequestBody Account user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        account.save(user);
        return new ResponseEntity<>("Đổi mật khẩu thành công!", HttpStatus.OK);
    }
    @GetMapping("/email/{email}")
    public ResponseEntity<?> findAccountByEmail(@PathVariable String email){
        return  new ResponseEntity<>(account.findAccountByEmail(email),HttpStatus.OK);
    }
    @GetMapping("/user")
    public ResponseEntity<Iterable<String>> findListNameUser() {
        return new ResponseEntity<>(userService.listNameUser(), HttpStatus.OK);
    }
    @GetMapping("/")
    public ResponseEntity<?> findAllUser(){
        return new ResponseEntity<>(userService.findAll(),HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> findOne(@PathVariable Long id){
        Optional<UserDTO> userDTO = Optional.ofNullable(userService.findOne(id));
        if (userDTO.isPresent()){
         return new ResponseEntity<>(userDTO,HttpStatus.OK);
        } return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping
    public ResponseEntity<?> save(@RequestBody UserDTO userDTO){

            UserDTO userDTO1 = userService.findOne(userDTO.getId());
            if (userDTO.getWards().getId()==0){
                userDTO.setWards(userDTO1.getWards());
            }
            if (userDTO.getAvatar()==null){
                userDTO.setAvatar(userDTO1.getAvatar());
            }
            userService.save(userDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping("/account/{id}")
    public ResponseEntity<?> findUserById(@PathVariable Long id){
        UserDTO userDTO = userService.findUserByAccount(id);
        return new ResponseEntity<>(userDTO,HttpStatus.OK);
    }
}
