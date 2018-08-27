package com.julian21olarte.thymeleafexample.controllers;

import com.julian21olarte.thymeleafexample.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private ClientRepository clientRepository;

    @GetMapping(value = {"/", "/home"})
    public String home(Model model) {
        model.addAttribute("title", "Home");
        return "home";
    }
}
