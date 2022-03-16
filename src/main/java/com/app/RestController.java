package com.app;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@org.springframework.web.bind.annotation.RestController
public class RestController {
    private final Surveys surveys;

    RestController(Surveys addressBook) {
        this.surveys = addressBook;
    }

    @PostMapping(path ="/questions/add", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public RedirectView newQuestion(Question question) {
//        Survey s = surveys.getById((long) 1);
//        s.getQuestions().add(question);
//        surveys.save(s);
        return new RedirectView("/buddies");
    }

    @PostMapping(path ="/questions/del", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public RedirectView deleteQuestion(Long id) {
        surveys.deleteById(id);
        return new RedirectView("/questions");
    }
}
