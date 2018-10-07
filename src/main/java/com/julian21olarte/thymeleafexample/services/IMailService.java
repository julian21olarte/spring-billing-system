package com.julian21olarte.thymeleafexample.services;

public interface IMailService {

    void sendMessage(String to, String subject, String message);
}
