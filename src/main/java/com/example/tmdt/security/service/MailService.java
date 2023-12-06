package com.example.tmdt.security.service;






import com.example.tmdt.security.DTO.DataMailDTO;

import javax.mail.MessagingException;

public interface MailService {
    void sendHtmlMail(DataMailDTO dataMail, String templateName) throws MessagingException;
}