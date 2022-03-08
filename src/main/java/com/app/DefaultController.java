package com.app;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DefaultController {
    private final Survey survey;

    DefaultController(Survey survey) {
        this.survey = survey;
    }

    @RequestMapping("/questions")
    public String greeting(Model model) {
        model.addAttribute("questions", survey.findAll());
        return "questions";
    }

    @RequestMapping("/poll")
    public String poll(Model model) {
        // To add variable to thymeleaf edit the command below
        // 1st is the variable name, 2nd is the value of the variable
        // model.addAttribute("questions", survey.findAll());
        return "poll";
    }

    @RequestMapping("/results")
    public String results(Model model) {
        // To add variable to thymeleaf edit the command below
        // 1st is the variable name, 2nd is the value of the variable
        // model.addAttribute("questions", survey.findAll());
        return "results";
    }
}
