package com.example.demo.model.services;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class MailSenderService {
   private JavaMailSenderImpl javaMailSenderImpl;


   public MailSenderService(JavaMailSenderImpl javaMailSenderImpl){
       this.javaMailSenderImpl = javaMailSenderImpl;
   }

    @Async
    public void sendEmail(String from, String to, String subject, String body){
       MimeMessage mimeMessage = javaMailSenderImpl.createMimeMessage();
        MimeMessageHelper  message = new MimeMessageHelper(mimeMessage);
    try{
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
    }
    catch(MessagingException e){
        e.printStackTrace();
    }
       javaMailSenderImpl.send(mimeMessage);
    }

}
