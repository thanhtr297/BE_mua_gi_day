package com.example.tmdt.controller;

import com.example.tmdt.model.Message;
import com.example.tmdt.security.model.Account;
import com.example.tmdt.security.service.IAccountService;
import com.example.tmdt.service.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin("*")
public class MessageController {
    @Autowired
   private IMessageService messageService;
    @Autowired
    SimpMessagingTemplate template;
    @Autowired
   private IAccountService accountService;
    @GetMapping("/allFriend/{fromUserId}")
    public List<Message> getAllChatFriends(@PathVariable Long fromUserId) {
        return messageService.initialStateAllChatFriends(fromUserId);
    }
//    @PostMapping("/allFriend")
//    public List<Message> getAllChatFriends(@Param("fromUserId") Long fromUserId,
//                                           @Param("toUserId") Long toUserId){
//        return messageService.initialStateAllChatFriends(fromUserId, toUserId);
//    }

    @PostMapping( "/all/{id}")
    public List<Message> getAllMessages(@PathVariable Long id , @RequestBody Account account) {
        String username = account.getUsername();
        return messageService.getAllMessages(username, id);
    }




    @PostMapping("/chat")
    public void createPrivateChatMessages(@RequestBody Message message) throws Exception {
        if(message != null ) {
            Account account = accountService.findById(message.getFromUser().getId());
            message.setTime(LocalDateTime.now());
            message.setFromUser(account);
            messageService.save(message);
            template.convertAndSend("/chat/user/queue", message);

            return;
        }
        throw new Exception("Error Create Message !");
    }

    @PostMapping("/create")
    ResponseEntity<?> createNewMessage(@Param("fromUserId") Long fromUserId,
                                       @Param("toUserId") Long toUserId) {
        messageService.createNewMessage(fromUserId, toUserId);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }
}
