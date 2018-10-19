package com.julian21olarte.thymeleafexample;

import com.julian21olarte.thymeleafexample.services.StoreServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ThymeleafexampleApplication implements CommandLineRunner {

    @Autowired
    private StoreServiceImpl serviceImpl;

    public static void main(String[] args) {
        SpringApplication.run(ThymeleafexampleApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //this.serviceImpl.init();
    }
}
