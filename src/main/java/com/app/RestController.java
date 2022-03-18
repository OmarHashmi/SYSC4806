package com.app;

import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.*;

@org.springframework.web.bind.annotation.RestController
public class RestController {
    private final Surveys surveys;

    RestController(Surveys addressBook) {
        this.surveys = addressBook;
    }

	@PostMapping(path ="/createSurveyPost", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
	public RedirectView newSurvey(@RequestParam HashMap<String,String> params) {

		ArrayList<String> values = new ArrayList<String>(params.values());

		Survey survey = new Survey(values.get(0));

		for(int i=1;i<values.size();i+=2){
			String type   = values.get(i);
			String prompt = values.get(i+1);
			Question q    = new Question(type,prompt);

			survey.addQuestion(q);
		}

		surveys.save(survey);

		return new RedirectView("/");
	}

    @PostMapping(path ="/questions/add", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public RedirectView newQuestion(Question question) {
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
