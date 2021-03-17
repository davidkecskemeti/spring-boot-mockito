package com.dk.example.springbootmockito.service;

import org.springframework.stereotype.Service;

@Service
public class EmailService {

    public void sendEmail(String message){
        System.out.println("Email sent:"+message);
    }

}
