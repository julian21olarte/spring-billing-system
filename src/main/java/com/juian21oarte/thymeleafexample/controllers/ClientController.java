package com.juian21oarte.thymeleafexample.controllers;

import com.juian21oarte.thymeleafexample.models.Client;
import com.juian21oarte.thymeleafexample.repositories.ClientRepository;
import com.juian21oarte.thymeleafexample.services.ClientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@SessionAttributes("client")
public class ClientController {

    @Autowired
    private ClientServiceImpl clientService;

    @GetMapping(value = "/clients")
    public String clients(Model model) {
        model.addAttribute("title", "Clients");
        model.addAttribute("clients", this.clientService.findAll());
        return "clients";
    }

    @GetMapping(value = "/create")
    public String createClientForm(Model model) {
        model.addAttribute("title", "Create Client");
        model.addAttribute("client", new Client());
        return "formClient";
    }

    @PostMapping(value = "/create")
    public String createClientRequest(@Valid Client client, BindingResult result, Model model, RedirectAttributes flash, SessionStatus sessionStatus) {

        // if has errors return the same view and show errors
        if(result.hasErrors()) {
            model.addAttribute("title", "Create Client");
            return "formClient";
        }
        this.clientService.save(client);
        sessionStatus.setComplete(); // complete session and remove client object.
        flash.addFlashAttribute("success", "Client was created!");
        return "redirect:/clients";
    }

    @GetMapping(value = "/update/{id}")
    public String updateClientForm(@PathVariable("id") Long id, Model model, RedirectAttributes flash) {
        model.addAttribute("title", "Update Client");
        if(!this.clientService.existById(id)) {
            flash.addFlashAttribute("danger", "Not exist any client with id " + id);
            return "redirect:/clients";
        }
        model.addAttribute("client", this.clientService.findById(id));
        flash.addFlashAttribute("success", "Client was updated!");
        return "formClient";
    }

    @GetMapping(value = "/delete/{id}")
    public String deleteClient(@PathVariable("id") Long id, RedirectAttributes flash) {
        this.clientService.deleteById(id);
        flash.addFlashAttribute("success", "Client was deleted!");
        return "redirect:/clients";
    }
}
