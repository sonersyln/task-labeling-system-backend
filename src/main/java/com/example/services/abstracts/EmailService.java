package com.example.services.abstracts;

import jakarta.mail.MessagingException;

import java.io.UnsupportedEncodingException;

public interface EmailService {

     void sendTaskAddEmail(String to, String username, String taskName, String labelNames) throws MessagingException, UnsupportedEncodingException ;
}
