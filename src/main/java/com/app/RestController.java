package com.app;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@org.springframework.web.bind.annotation.RestController
public class RestController {
    private final Questions questions;

    RestController(Questions addressBook) {
        this.questions = addressBook;
    }

    @PostMapping(path ="/questions/add", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public RedirectView newQuestion(Question question) {
        questions.save(question);
        return new RedirectView("/buddies");
    }

    @PostMapping(path ="/questions/del", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public RedirectView deleteQuestion(Long id) {
        questions.deleteById(id);
        return new RedirectView("/questions");
    }
}
