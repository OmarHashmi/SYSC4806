package com.app;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DefaultController {
    private final Survey survey;

    DefaultController(Survey addressBook) {
        this.survey = addressBook;
    }

    @RequestMapping("/questions")
    public String greeting(Model model) {
        model.addAttribute("questions", survey.findAll());
        return "questions";
    }
}
