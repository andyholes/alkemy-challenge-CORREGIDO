package com.alkemy.challenge.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailServiceImpl{

    @Autowired
    private JavaMailSender emailSender;

    public void sendSimpleMessage(String to) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("andyholeschallenge@gmail.com");
        message.setTo(to);
        message.setSubject("ALKEMY CHALLENGE de andyholes");
        message.setText("Su registro fue satisfactorio!");
        emailSender.send(message);
    }
}