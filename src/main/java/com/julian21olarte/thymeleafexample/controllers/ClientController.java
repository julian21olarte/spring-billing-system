package com.julian21olarte.thymeleafexample.controllers;

import com.julian21olarte.thymeleafexample.models.Client;
import com.julian21olarte.thymeleafexample.services.ClientServiceImpl;
import com.julian21olarte.thymeleafexample.services.StoreServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.util.UUID;

@Controller
@SessionAttributes("client")
public class ClientController {

    @Autowired
    private ClientServiceImpl clientService;

    @Autowired
    private StoreServiceImpl storeService;

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
    public String createClientRequest(@Valid Client client, BindingResult result, Model model,
                  RedirectAttributes flash, SessionStatus sessionStatus, @RequestParam("file") MultipartFile photo) throws IOException {

        if(result.hasErrors()) {
            model.addAttribute("title", "Create Client");
            return "formClient";
        }

        String pathName = UUID.randomUUID() + "_" + photo.getOriginalFilename();
        if(this.storeService.storeFileInUploadsFolder(photo, pathName)) {
            flash.addFlashAttribute("info", "Photo uploaded successful - " + pathName);
            client.setPhoto(pathName);
        }

        this.clientService.save(client);
        sessionStatus.setComplete(); // complete session and remove client object.

        if ((client.getId() == null)) {
            flash.addFlashAttribute("success", "Client was created!");
        } else {
            flash.addFlashAttribute("success", "Client was updated!");
        }
        return "redirect:/clients";
    }

    @GetMapping(value = "/update/{id}")
    public String updateClientForm(@PathVariable("id") Long id, Model model, RedirectAttributes flash) {
        model.addAttribute("title", "Update Client");
        if(!this.clientService.existById(id)) {
            flash.addFlashAttribute("danger", "Not exist any client with id " + id);
            return "redirect:/clients";
        }
        model.addAttribute("client", this.clientService.findById(id).get());
        return "formClient";
    }

    @GetMapping(value = "/delete/{id}")
    public String deleteClient(@PathVariable("id") Long id, RedirectAttributes flash) {
        this.clientService.deleteById(id);
        flash.addFlashAttribute("success", "Client was deleted!");
        return "redirect:/clients";
    }

    @GetMapping(value = "/view/{id}")
    public String viewClient(@PathVariable(value = "id") Long id, Model model, RedirectAttributes flash) {

        model.addAttribute("title", "Update Client");

        if(!this.clientService.existById(id)) {
            flash.addFlashAttribute("danger", "Not exist any client with id " + id);
            return "redirect:/clients";
        }
        model.addAttribute("client", this.clientService.findById(id).get());
        return "viewClient";
    }
}
