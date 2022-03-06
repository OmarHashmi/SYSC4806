package com.app;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DefaultController {
    private final AddressBook addressBook;

    DefaultController(AddressBook addressBook) {
        this.addressBook = addressBook;
    }

    @RequestMapping("/buddies")
    public String greeting(Model model) {
        model.addAttribute("buddies", addressBook.findAll());
        return "buddies";
    }
}
