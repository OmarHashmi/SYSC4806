package com.app;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class OptionsQuestion {
    @Id
    @GeneratedValue
    protected Long id;

    protected String question;

    @OneToMany
    protected List<Result> results;

    private ArrayList<String> answers;

    protected OptionsQuestion() {}

    public OptionsQuestion(Long id, String question, ArrayList<String> answers) {
        this.id = id;
        this.question = question;
        this.answers = answers;
    }

    public OptionsQuestion(String question, ArrayList<String> answers) {
        this.id = 0L;
        this.question = question;
        this.answers = answers;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<Result> getResutls() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public ArrayList<String> getAnswers() {
        return answers;
    }

    public void setAnswers(ArrayList<String> answers) {
        this.answers = answers;
    }

    @Override
    public String toString() {
        return "com.app.Question [question=" + this.question + ", answers=" + this.answers.toString() + "]";
    }
}
