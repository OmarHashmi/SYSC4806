package com.app;

import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.HashMap;

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

    @PostMapping("/submitAnswers/{survey_id}")
    public RedirectView poll(
            @PathVariable("survey_id") long survey_id,
            @RequestParam HashMap<String,String> allValues,
            Model model) {

        Survey s = surveys.getById(survey_id);

        for (Question q : s.getQuestions()) {
            String questionId = q.getId().toString();
            if (allValues.containsKey(questionId)) {
                q.addResult(new Result(allValues.get(questionId)));
            }
        }

        surveys.save(s);
        return new RedirectView("/results/" + survey_id, false);
    }
}
