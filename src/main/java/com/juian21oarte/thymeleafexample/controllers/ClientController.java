package com.juian21oarte.thymeleafexample.controllers;

import com.juian21oarte.thymeleafexample.models.Client;
import com.juian21oarte.thymeleafexample.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @GetMapping(value = "/clients")
    public String clients(Model model) {
        model.addAttribute("title", "Clients");
        model.addAttribute("clients", this.clientRepository.findAll());
        return "clients";
    }

    @GetMapping(value = "/create")
    public String createClientForm(Model model) {
        model.addAttribute("title", "Create Client");
        model.addAttribute("client", new Client());
        return "formClient";
    }

    @PostMapping(value = "/create")
    public String createClientRequest(@Valid Client client, BindingResult result, Model model) {

        // if has errors return the same view and show errors
        if(result.hasErrors()) {
            model.addAttribute("title", "Create Client");
            return "formClient";
        }
        this.clientRepository.save(client);
        return "redirect:clients";
    }

    @GetMapping(value = "/update/{id}")
    public String updateClientForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("title", "Update Client");
        model.addAttribute("client", this.clientRepository.findById(id));
        return "formClient";
    }
}
