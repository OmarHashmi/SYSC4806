package com.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class DefaultController {
    @Autowired
    private final Questions questions;

    DefaultController(Questions questions) {
        this.questions = questions;
    }
    @RequestMapping("/questions")
    public String greeting(Model model) {
        model.addAttribute("questions", questions.findAll());
        return "questions";
    }
    @RequestMapping("/poll")
    public String poll(Model model) {
        // To add variable to thymeleaf edit the command below
        // 1st is the variable name, 2nd is the value of the variable
        // model.addAttribute("questions", survey.findAll());
        return "poll";
    }
    @RequestMapping("/submitAnswers")
    public RedirectView poll(@RequestParam(value = "answer") String answer,
                       Model model) {
        // To add variable to thymeleaf edit the command below
        // 1st is the variable name, 2nd is the value of the variable
        Question temp = new Question();
        temp.setQuestion("What is your name?");
        questions.save(temp);
        model.addAttribute("poll", temp);

		return new RedirectView("results");
    }

    @RequestMapping("/results")
    public String results(Model model) {
        // To add variable to thymeleaf edit the command below
        // 1st is the variable name, 2nd is the value of the variable
        // model.addAttribute("questions", survey.findAll());
        model.addAttribute("results", questions.findAll());
        return "results";
    }
}