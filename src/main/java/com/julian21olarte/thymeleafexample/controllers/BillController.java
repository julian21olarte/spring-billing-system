package com.julian21olarte.thymeleafexample.controllers;

import com.julian21olarte.thymeleafexample.models.Bill;
import com.julian21olarte.thymeleafexample.models.Client;
import com.julian21olarte.thymeleafexample.services.ClientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/bill")
@SessionAttributes("bill")
public class BillController {

    @Autowired
    private ClientServiceImpl clientService;

    @GetMapping("/form/{client}")
    public String billForm(@PathVariable("client") Long client, Model model, RedirectAttributes flash) {
        Client myClient = this.clientService.findById(client).get();
        if(myClient == null) {
            flash.addFlashAttribute("error", "Not exist eny client with this id");
            return "redirect:/clients";
        }

        Bill bill = new Bill();
        bill.setClient(myClient);

        model.addAttribute("bill", bill);
        model.addAttribute("title", "new Bill");
        return "bill/billForm";
    }
}
