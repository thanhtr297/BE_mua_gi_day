package com.example.tmdt.service.impl;

import com.example.tmdt.model.Message;
import com.example.tmdt.repository.MessageRepository;
import com.example.tmdt.security.model.Account;
import com.example.tmdt.security.repository.IAccountRepository;
import com.example.tmdt.service.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageService implements IMessageService {
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private IAccountRepository accountRepository;

    @Override
    public List<Message> initialStateAllChatFriends(Long fromUserId, Long toUserId) {
        createNewMessage(fromUserId, toUserId);
        return messageRepository.initialStateAllChatFriends(fromUserId);
    }

    @Override
    public List<Message> initialStateAllChatFriends(Long fromUserId) {
        return messageRepository.initialStateAllChatFriends(fromUserId);
    }

    @Override
    public List<Message> getAllMessages(String loggedInUsername, Long chatUserId) {
        Account loggedUser = accountRepository.findByUsername(loggedInUsername);
        Account chatUser = accountRepository.findById(chatUserId).get();
        return messageRepository.findAllMessagesBetweenTwoUsers(loggedUser.getId(), chatUser.getId());

    }

    @Override
    public Message save(Message message) {
        return messageRepository.save(message);
    }

    @Override
    public void createNewMessage(Long fromUserId, Long toUserId) {
        List<Message> messages = messageRepository.findListAccountInChat(fromUserId);
        boolean check = false;
        for (Message message : messages) {
            if (message.getToUser().getId().equals(toUserId)) {
                check = true;
                break;
            }
        }
        if (!check) {
            Account accountFrom = accountRepository.findById(fromUserId).get();
            Account accountTo = accountRepository.findById(toUserId).get();
            Message message = new Message();
            message.setFromUser(accountFrom);
            message.setToUser(accountTo);
            message.setTime(LocalDateTime.now());
            message.setContent("xin chào!");
            messageRepository.save(message);

//           }
//        }

        }
    }
}


//    private void createNewMessage(Account accountFrom, Account accountTo) {
//            Message message = new Message();
//            message.setFromUser(accountFrom);
//            message.setToUser(accountTo);
//            message.setTime(LocalDateTime.now());
//            message.setContent("xin chào!");
//            System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
//            messageRepository.save(message);
//
//
//        }



