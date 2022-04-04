package com.app;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.*;

@org.springframework.web.bind.annotation.RestController
public class RestController extends WebSecurityConfigurerAdapter {
    private final Surveys surveys;

    RestController(Surveys surveys) {
        this.surveys = surveys;
    }

	@PostMapping(path ="/createSurveyPost")
	public RedirectView newSurvey(@RequestParam HashMap<String,String> params,
                                  @AuthenticationPrincipal OAuth2User principal) {

        if (principal != null) {
            ArrayList<String> values = new ArrayList<String>(params.values());

            Survey survey = new Survey(((Integer)principal.getAttribute("id")).longValue(),values.get(1));

            for(int i=2;i<values.size();i+=2){
                String type   = values.get(i);
                String prompt = values.get(i+1);
                SingleQuestion q    = new SingleQuestion(type,prompt);

                survey.addQuestion(q);
            }

            surveys.save(survey);

        }
		return new RedirectView("/mySurveys");
	}

    @GetMapping("/user")
    public Map<String, Object> user(@AuthenticationPrincipal OAuth2User principal) {
        return Collections.singletonMap("name", principal.getAttribute("name"));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests(a -> a
                                .antMatchers(
                                        "/",
                                        "/*.css",
                                        "/*.js",
                                        "/createSurveyPost",
                                        "/questions/**",
                                        "/submitAnswers/**",
                                        "/survey/**",
                                        "/results/**",
                                        "/webjars/**").permitAll()
                                .anyRequest().authenticated()
                ).logout(l -> l
                                .logoutSuccessUrl("/").permitAll()
                ).csrf(c -> c
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                )
                .exceptionHandling(e -> e
                                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
                                .oauth2Login();
    }

    @PostMapping(path ="/questions/add", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public RedirectView newQuestion(SingleQuestion question) {
        return new RedirectView("/buddies");
    }

    @PostMapping(path ="/questions/del", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public RedirectView deleteQuestion(Long id) {
        surveys.deleteById(id);
        return new RedirectView("/questions");
    }

    @PostMapping(path = "/submitAnswers/{survey_id}", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public RedirectView poll(
            @PathVariable("survey_id") long survey_id,
            @RequestParam MultiValueMap<String, String> allValues,
            Model model) {

        Survey s = surveys.getById(survey_id);

        for (Question q : s.getQuestions()) {
            String questionId = q.getId().toString();
            if (allValues.get(questionId) == null) continue;
            for (String result : allValues.get(questionId)) {
                q.addResult(new Result(result));
            }
        }

        surveys.save(s);
        return new RedirectView("/results/" + survey_id, false);
    }

    @PostMapping("/closeSurveys")
    public RedirectView closeSurveys(@RequestParam HashMap<String,String> params,
                                  @AuthenticationPrincipal OAuth2User principal) {

        for (Map.Entry<String, String> e: params.entrySet()) {
            try {
                Survey s = surveys.getById(Long.valueOf(e.getKey()));
                if (e.getValue().compareTo("on") == 0) {
                    s.setClosed(false);
                } else {
                    s.setClosed(true);
                }
                surveys.save(s);
            } catch (NumberFormatException num) {
                System.out.println("No survey exists with id: " + e.getKey());
            }
        }

        return new RedirectView("/mySurveys");
    }
}
