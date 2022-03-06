package com.app;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@org.springframework.web.bind.annotation.RestController
public class RestController {
    private final AddressBook addressBook;

    RestController(AddressBook addressBook) {
        this.addressBook = addressBook;
    }

    @PostMapping(path ="/buddies/add", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public RedirectView newBuddy(BuddyInfo buddyInfo) {
        addressBook.save(buddyInfo);
        return new RedirectView("/buddies");
    }

    @PostMapping(path ="/buddies/del", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public RedirectView deleteBuddy(Long id) {
        addressBook.deleteById(id);
        return new RedirectView("/buddies");
    }
}
