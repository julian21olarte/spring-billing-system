package com.juian21oarte.thymeleafexample.controllers;

import com.juian21oarte.thymeleafexample.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @Autowired
    private ClientRepository clientRepository;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("title", "Hola mundo");
        model.addAttribute("clients", clientRepository.findAll());
        return "index";
    }
}
