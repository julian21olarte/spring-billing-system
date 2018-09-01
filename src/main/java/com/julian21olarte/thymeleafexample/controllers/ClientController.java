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
@RequestMapping("/client")
@SessionAttributes("client")
public class ClientController {

    @Autowired
    private ClientServiceImpl clientService;

    @Autowired
    private StoreServiceImpl storeService;

    @GetMapping(value = "/list")
    public String clients(Model model) {
        model.addAttribute("title", "Clients");
        model.addAttribute("clients", this.clientService.findAll());
        return "client/listClients";
    }

    @GetMapping(value = "/create")
    public String createClientForm(Model model) {
        model.addAttribute("title", "Create Client");
        model.addAttribute("client", new Client());
        return "client/formClient";
    }

    @PostMapping(value = "/create")
    public String createClientRequest(@Valid Client client, BindingResult result, Model model,
                  RedirectAttributes flash, SessionStatus sessionStatus, @RequestParam("file") MultipartFile photo) throws IOException {

        if(result.hasErrors()) {
            return "redirect:/client/create";
        }

        String pathName = UUID.randomUUID() + "_" + photo.getOriginalFilename();
        if(this.storeService.storeFileInUploadsFolder(photo, pathName)) {
            if(client.getPhoto() != null) {
                this.storeService.deleteFileFromUploadsFolder(client.getPhoto());
            }
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
        return "redirect:/client/list";
    }

    @GetMapping(value = "/update/{id}")
    public String updateClientForm(@PathVariable("id") Long id, Model model, RedirectAttributes flash) {
        model.addAttribute("title", "Update Client");
        if(!this.clientService.existById(id)) {
            flash.addFlashAttribute("danger", "Not exist any client with id " + id);
            return "redirect:/clients";
        }
        model.addAttribute("client", this.clientService.findById(id).get());
        return "client/formClient";
    }

    @GetMapping(value = "/delete/{id}")
    public String deleteClient(@PathVariable("id") Long id, RedirectAttributes flash) throws IOException {
        String oldPhoto = this.clientService.findById(id).get().getPhoto();
        this.clientService.deleteById(id);
        if(oldPhoto != null) {
            this.storeService.deleteFileFromUploadsFolder(oldPhoto);
        }
        flash.addFlashAttribute("success", "Client was deleted!");
        return "redirect:/client/list";
    }

    @GetMapping(value = "/view/{id}")
    public String viewClient(@PathVariable(value = "id") Long id, Model model, RedirectAttributes flash) {

        model.addAttribute("title", "Update Client");

        if(!this.clientService.existById(id)) {
            flash.addFlashAttribute("danger", "Not exist any client with id " + id);
            return "redirect:/client/list";
        }
        model.addAttribute("client", this.clientService.findById(id).get());
        return "client/viewClient";
    }
}
