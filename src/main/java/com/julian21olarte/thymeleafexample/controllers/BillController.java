package com.julian21olarte.thymeleafexample.controllers;

import com.julian21olarte.thymeleafexample.models.Bill;
import com.julian21olarte.thymeleafexample.models.Client;
import com.julian21olarte.thymeleafexample.models.Product;
import com.julian21olarte.thymeleafexample.services.BillServiceImpl;
import com.julian21olarte.thymeleafexample.services.ClientServiceImpl;
import com.julian21olarte.thymeleafexample.services.ProductServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;

@Controller
@RequestMapping("/bill")
@SessionAttributes("bill")
public class BillController {

    @Autowired
    private ClientServiceImpl clientService;

    @Autowired
    private BillServiceImpl billService;

    @Autowired
    private ProductServiceImpl productService;

    @GetMapping("/form/{client}")
    public String billForm(@PathVariable("client") Long client, Model model, RedirectAttributes flash) {
        Client myClient = this.clientService.findById(client).get();
        if(myClient == null) {
            flash.addFlashAttribute("error", "Not exist eny client with this id");
            return "redirect:/client/list";
        }

        Bill bill = new Bill();
        bill.setClient(myClient);

        model.addAttribute("bill", bill);
        model.addAttribute("title", "New Bill");
        return "bill/formBill";
    }


    @PostMapping(value = "/create")
    public String createClientRequest(@Valid Bill bill, BindingResult result, Model model,
                                      RedirectAttributes flash, SessionStatus sessionStatus) throws IOException {

        if(result.hasErrors()) {
            return "redirect:/client/create";
        }

        this.billService.save(bill);
        sessionStatus.setComplete(); // complete session and remove bill object.
        return "redirect:/bill/form/" + bill.getClient().getId();
    }

    @GetMapping(value = "/search-product/{term}", produces = {"application/json"})
    public @ResponseBody Iterable<Product> getProducts(@PathVariable("term") String term) {
        return this.productService.findProductsByName(term);
    }
}
