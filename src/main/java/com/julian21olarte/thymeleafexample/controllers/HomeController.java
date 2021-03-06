package com.julian21olarte.thymeleafexample.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping(value = {"/", "/home"})
    public String home(Model model) {
        model.addAttribute("title", "Home");
        return "home/home";
    }
}
