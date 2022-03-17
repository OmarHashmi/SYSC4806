package com.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.Map;

@Controller
public class DefaultController {
    private final Surveys surveys;

    DefaultController(Surveys surveys) {
        this.surveys = surveys;
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
    @RequestMapping("/submitAnswers/{survey_id}")
    public RedirectView poll(
            @PathVariable("survey_id") long survey_id,
            @RequestParam Map<String,String> allValues,
            Model model) {

        Survey s = surveys.getById(survey_id);
        ArrayList<Question> qs = new ArrayList<>(s.getQuestions());

        for (Map.Entry<String, String> entry : allValues.entrySet()) {
            for (Question q : qs) {
                if (q.id == Long.parseLong(entry.getKey())) {
                    q.addResult(new Result(entry.getValue()));
                }
            }
        }

        surveys.save(s);
		return new RedirectView("/results/" + survey_id, false);
    }

    @RequestMapping("/results")
    public String results(Model model) {
        // To add variable to thymeleaf edit the command below
        // 1st is the variable name, 2nd is the value of the variable
        // model.addAttribute("questions", survey.findAll());
        model.addAttribute("questions", surveys.findById(1L).getQuestions());
        return "results";
    }
}