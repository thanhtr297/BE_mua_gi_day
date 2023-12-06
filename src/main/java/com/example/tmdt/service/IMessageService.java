package com.example.tmdt.service;
import com.example.tmdt.model.Message;


import java.util.List;

public interface IMessageService{
    List<Message> initialStateAllChatFriends(Long fromUserId, Long toUserId);
    List<Message> initialStateAllChatFriends(Long fromUserId);
    List<Message> getAllMessages(String loggedInUsername, Long chatUserId);
//    List<Message> getAllMessages(String loggedInUsername, Long chatUserId, Long toUserId1);
    Message save(Message message);
     void createNewMessage(Long fromUserId, Long toUserId);
}
