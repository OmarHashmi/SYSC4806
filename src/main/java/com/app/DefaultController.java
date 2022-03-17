package com.app;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DefaultController {
    private final Surveys surveys;

    DefaultController(Surveys surveys) {
        this.surveys = surveys;
    }

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("surveys", surveys.findAll());
        return "index";
    }

    @RequestMapping("/questions")
    public String greeting(Model model) {
        model.addAttribute("questions", surveys.findAll());
        return "questions";
    }

    @RequestMapping("/survey/{survey_id}")
    public String poll(Model model, @PathVariable("survey_id") long survey_id) {
        Survey s = surveys.getById(survey_id);

        model.addAttribute("survey", s);
        model.addAttribute("questions", s.getQuestions());

        return "survey";
    }

    @RequestMapping("/results")
    public String results(Model model) {
        // To add variable to thymeleaf edit the command below
        // 1st is the variable name, 2nd is the value of the variable
        // model.addAttribute("questions", survey.findAll());
        model.addAttribute("surveys", surveys.findAll());
        return "results";
    }
}